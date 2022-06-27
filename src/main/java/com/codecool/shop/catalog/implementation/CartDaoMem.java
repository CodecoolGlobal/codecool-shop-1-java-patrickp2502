package com.codecool.shop.catalog.implementation;

import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.model.Cart;
import com.codecool.shop.catalog.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;

    List<Cart> activeCarts = new ArrayList<>();
    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(CartItem cartItem, String sessionId) {
        cartOfSession(sessionId).add(cartItem);
    }

    @Override
    public CartItem find(int id, String sessionId) {
        return cartOfSession(sessionId).findProductBy(id);
    }

    @Override
    public void remove(int id, String sessionId) {
        cartOfSession(sessionId).removeSingleProduct(id);
    }

    @Override
    public Cart getCart(String sessionId) {
        Cart requestedCart;
        if(sessionHasACart(sessionId)) {
            requestedCart = cartOfSession(sessionId);
        } else {
            requestedCart = createAndGetCartForSession(sessionId);
        }
        return requestedCart;
    }

    private Cart createAndGetCartForSession(String sessionId) {
        Cart requestedCart = new Cart();
        requestedCart.setSessionId(sessionId);
        return requestedCart;
    }

    private Cart cartOfSession(String sessionId) {
        return activeCarts
                .stream()
                .filter(cart -> cart.getSessionId().equals(sessionId))
                .findFirst()
                .orElse(null);
    }

    private boolean sessionHasACart(String sessionId) {
        return activeCarts
                .stream()
                .anyMatch(cart -> cart.getSessionId().equals(sessionId));
    }
}