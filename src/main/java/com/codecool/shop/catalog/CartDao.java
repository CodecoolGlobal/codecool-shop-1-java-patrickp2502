package com.codecool.shop.catalog;

import com.codecool.shop.catalog.model.Cart;
import com.codecool.shop.catalog.model.CartItem;

import java.util.List;

public interface CartDao {

    void add(CartItem cartItem, String sessionId);

    CartItem find(int id, String sessionId);

    void remove(int id, String sessionId);

    Cart getCart(String sessionId);
}