package com.codecool.shop.cart.controller;

import com.codecool.shop.cart.ProductCategoryDao;
import com.codecool.shop.cart.ProductDao;
import com.codecool.shop.cart.config.TemplateEngineUtil;
import com.codecool.shop.cart.implementation.ProductCategoryDaoMem;
import com.codecool.shop.cart.implementation.ProductDaoMem;
import com.codecool.shop.cart.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"}, loadOnStartup = 1)
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = (ProductDao) ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = (ProductCategoryDao) ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productService.getProductsForCategory(1));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
