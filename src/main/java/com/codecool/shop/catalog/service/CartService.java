package com.codecool.shop.catalog.service;

import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.model.Cart;
import com.codecool.shop.catalog.model.Product;

import java.util.List;

public class CartService {
    private final CartDao cartDAO;

    public CartService(CartDao cartDAO) {
        this.cartDAO = cartDAO;
    }

    public List<Product> getAllProducts() {
        return cartDAO.getAll();
    }

    public int getCount(int id) {
        return cartDAO.getProductCount(id);
    }

    public Cart getCart() {
        return cartDAO.getCart();
    }
}
