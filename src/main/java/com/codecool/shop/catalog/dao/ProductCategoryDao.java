package com.codecool.shop.catalog.dao;

import com.codecool.shop.catalog.dao.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);

    List<ProductCategory> getAll();

}
