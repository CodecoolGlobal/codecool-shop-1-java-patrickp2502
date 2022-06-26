package com.codecool.shop.catalog;

import com.codecool.shop.catalog.model.Cart;
import com.codecool.shop.catalog.model.Product;

import java.util.List;

public interface CartDao {

    void add(int id);

    Product find(int id);

    void remove(int id);

    List<Product> getAll();

    int getProductCount(int id);

    Cart getCart();
}