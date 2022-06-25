package com.codecool.shop.cart;

import com.codecool.shop.cart.model.Product;

import java.util.List;

public interface CartDao {

    void add(int id);

    Product find(int id);

    void remove(int id);

    List<Product> getAll();

    int getProductCount(int id);
}
