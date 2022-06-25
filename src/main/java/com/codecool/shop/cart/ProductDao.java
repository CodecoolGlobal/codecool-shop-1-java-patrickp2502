package com.codecool.shop.cart;

import com.codecool.shop.cart.model.Product;
import com.codecool.shop.cart.model.ProductCategory;
import com.codecool.shop.cart.model.Supplier;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);

    List<Product> getAll();
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);

}
