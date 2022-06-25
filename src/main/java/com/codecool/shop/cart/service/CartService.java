package com.codecool.shop.cart.service;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.model.Product;

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
        return cartDAO.
    }
}
