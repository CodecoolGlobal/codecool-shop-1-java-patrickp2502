package com.codecool.shop.cart;

import com.codecool.shop.cart.model.Cart;
import com.codecool.shop.cart.model.CartItem;

public interface CartDao {

    void add(CartItem cartItem, String sessionId);

    CartItem find(int id, String sessionId);

    void remove(int id, String sessionId);

    Cart getCart(String sessionId);
}