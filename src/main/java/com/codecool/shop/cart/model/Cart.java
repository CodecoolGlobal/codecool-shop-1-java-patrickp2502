package com.codecool.shop.cart.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {
    private final List<Product> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    public double getSum() {
        double sum = cart
                .stream().mapToDouble(product -> product.getDefaultPrice().doubleValue()).sum();
        return sum;
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void removeProduct(Product product) {
        cart.remove(product);
    }

    public boolean isInCart(Product product) {
        return cart.contains(product);
    }

    public boolean IsEmpty() {
        return cart.size() == 0;
    }

}
