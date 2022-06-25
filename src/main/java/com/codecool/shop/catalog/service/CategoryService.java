package com.codecool.shop.catalog.service;

import com.codecool.shop.catalog.ProductCategoryDao;
import com.codecool.shop.catalog.model.ProductCategory;

import java.util.List;

public class CategoryService {

    private final ProductCategoryDao productCategoryDao;

    public CategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory findProductCategory(int productCategoryId) {
        return productCategoryDao.find(productCategoryId);
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryDao.getAll();
    }



}
