package com.codecool.shop.catalog.service;

import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.model.Cart;

public class CartService {
    private final CartDao cartDAO;

    public CartService(CartDao cartDAO) {
        this.cartDAO = cartDAO;
    }

    public Cart getCart(String sessionId) {
        return cartDAO.getCart(sessionId);
    }
}
