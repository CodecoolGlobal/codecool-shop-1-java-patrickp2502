package com.codecool.shop.cart.service;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.model.Cart;

public class CartService {
    private final CartDao cartDAO;

    public CartService(CartDao cartDAO) {
        this.cartDAO = cartDAO;
    }

    public Cart getCart(String sessionId) {
        return cartDAO.getCart(sessionId);
    }
}
