package com.codecool.shop.catalog.dao.service;

import com.codecool.shop.catalog.dao.ProductCategoryDao;
import com.codecool.shop.catalog.dao.ProductDao;
import com.codecool.shop.catalog.dao.SupplierDao;
import com.codecool.shop.catalog.dao.model.Product;
import com.codecool.shop.catalog.dao.model.ProductCategory;
import com.codecool.shop.catalog.dao.model.Supplier;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSupplier(int supplierId) {
        Supplier supplier = supplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public Product getProductById(int id) {
        return productDao.find(id);
    }

}
