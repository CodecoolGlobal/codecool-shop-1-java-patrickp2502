package com.codecool.shop.catalog.controller;

import com.codecool.shop.catalog.dao.ProductCategoryDao;
import com.codecool.shop.catalog.dao.ProductDao;
import com.codecool.shop.catalog.dao.SupplierDao;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.catalog.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.catalog.dao.implementation.ProductDaoMem;
import com.codecool.shop.catalog.dao.implementation.SupplierDaoMem;
import com.codecool.shop.catalog.dao.model.Product;
import com.codecool.shop.catalog.service.CategoryService;
import com.codecool.shop.catalog.service.ProductService;
import com.codecool.shop.catalog.service.SupplierService;
import com.codecool.shop.catalog.util.Validator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"}, loadOnStartup = 1)
public class CatalogController extends HttpServlet {

    private static final int DEFAULT_CATEGORY_ID = 1;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO ? @ Mentor this is weird
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CategoryService categoryService = new CategoryService(productCategoryDataStore);
        ProductService productService = new ProductService(
                productDataStore,
                productCategoryDataStore,
                supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession(true);
        System.out.println(session.getId());


        String parameterCategoryId = req.getParameter("category");
        int categoryId = DEFAULT_CATEGORY_ID;
        String selection = categoryService.findProductCategory(categoryId).getName();
        if (Validator.isStringNumber(parameterCategoryId)) {
            categoryId = Integer.parseInt(parameterCategoryId);
            selection = categoryService.findProductCategory(categoryId).getName();
        }

        String parameterSupplierId = req.getParameter("supplier");
        int supplierId = -1;
        if (Validator.isStringNumber(parameterSupplierId)) {
            supplierId = Integer.parseInt(parameterSupplierId);
            selection = supplierService.findSupplier(supplierId).getName();
        }


        //setting up products to present
        List<Product> products = supplierId > -1 ?
                productService.getProductsForSupplier(supplierId) : productService.getProductsForCategory(categoryId);

        //setting up context
        context.setVariable("categories", categoryService.getProductCategories());
        context.setVariable("suppliers", supplierService.getSuppliers());
        context.setVariable("selection", selection);
        context.setVariable("products", products);

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("catalog/index.html", context, resp.getWriter());
    }

}
