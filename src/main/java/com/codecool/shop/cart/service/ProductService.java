package com.codecool.shop.cart.service;

import com.codecool.shop.cart.ProductCategoryDao;
import com.codecool.shop.cart.ProductDao;
import com.codecool.shop.cart.model.Product;
import com.codecool.shop.cart.model.ProductCategory;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }


}
