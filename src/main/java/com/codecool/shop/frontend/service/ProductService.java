package com.codecool.shop.frontend.service;

import com.codecool.shop.frontend.dao.ProductCategoryDao;
import com.codecool.shop.frontend.dao.ProductDao;
import com.codecool.shop.frontend.model.Product;
import com.codecool.shop.frontend.model.ProductCategory;

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
