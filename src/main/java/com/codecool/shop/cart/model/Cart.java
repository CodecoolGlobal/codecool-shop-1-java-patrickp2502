package com.codecool.shop.cart.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Cart {
    private final HashMap<Product, HashMap> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    public double getSum() {
        final double[] sum = {0};
                cart
                .forEach(product -> sum[0] += Double.parseDouble(product.getPrice()) * cart.get(product))

                .stream().mapToDouble(product -> product.getDefaultPrice().doubleValue()).sum();
        return sum[0];
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

    public double getPriceOfAllProductsWithType(Product product) {
        return cart
                .stream()
                .filter(item -> item.equals(product))
                .mapToDouble(foundItem -> Double.parseDouble(foundItem.getPrice()))
                .sum();
    }

}
