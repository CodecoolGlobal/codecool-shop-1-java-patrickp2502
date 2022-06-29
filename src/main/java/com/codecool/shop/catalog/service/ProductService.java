package com.codecool.shop.catalog.service;

import com.codecool.shop.catalog.ProductCategoryDao;
import com.codecool.shop.catalog.ProductDao;
import com.codecool.shop.catalog.SupplierDao;
import com.codecool.shop.catalog.model.Product;
import com.codecool.shop.catalog.model.ProductCategory;
import com.codecool.shop.catalog.model.Supplier;

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