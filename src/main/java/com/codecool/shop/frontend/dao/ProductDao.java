package com.codecool.shop.frontend.dao;

import com.codecool.shop.frontend.model.Product;
import com.codecool.shop.frontend.model.ProductCategory;
import com.codecool.shop.frontend.model.Supplier;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);

    List<Product> getAll();
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);

}
