package com.codecool.shop.catalog.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Cart {
    private final HashMap<Product, HashMap<String, String>> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    public double getSumOfOneProduct(int id) {
        return Double.parseDouble(cart.get(findProductBy(id)).get("total price"));
    }

    public int getCountOfSingleProduct(int id) {
        return Integer.parseInt(cart.get(findProductBy(id)).get("count"));
    }

    public Product findProductBy(int id) {
        final List<Product> products = getProducts();
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProducts() {
        final List<Product> products = new ArrayList<>();
        cart.forEach((product, map) -> {
            products.add(product);
        });
        return products;
    }

    public void add(Product product) {
        String countKey = "count";
        String totalValueKey = "total price";
        if (isInCart(product)) {
            int currentCount = Integer.parseInt(cart.get(product).get(countKey));
            cart.get(product).put(countKey, String.valueOf(currentCount + 1));
            double currentTotalValue = Double.parseDouble(cart.get(product).get(totalValueKey));
            cart.get(product).put(totalValueKey,
                    String.valueOf(currentTotalValue + Double.parseDouble(product.getPrice())));
        } else {
            HashMap<String, String> productInfo = new HashMap<>();
            productInfo.put(countKey, "1");
            productInfo.put(totalValueKey, String.valueOf(product.getDefaultPrice()));
            cart.put(product, productInfo);
        }
    }

    public void removeSingleProduct(int id) {
        String countKey = "count";
        String totalPriceKey = "total price";
        if (cart.get(findProductBy(id)).get(countKey).equals("1")) {
            removeWholeProduct(id);
        } else {
            int currentCount = Integer.parseInt(cart.get(findProductBy(id)).get(countKey));
            cart.get(findProductBy(id)).put(countKey, String.valueOf(currentCount - 1));
            double currentTotalValue = Double.parseDouble(cart.get(findProductBy(id)).get(totalPriceKey));
            cart.get(findProductBy(id)).put(totalPriceKey,
                    String.valueOf(currentTotalValue + Double.parseDouble(findProductBy(id).getPrice())));
        }
    }

    public void removeWholeProduct(int id) {
        cart.remove(findProductBy(id));
    }

    public boolean isInCart(Product product) {
        return cart.containsKey(product);
    }

    public boolean IsEmpty() {
        return cart.size() == 0;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        String totalPriceKey = "total price";
        for (HashMap<String, String> value : cart.values()) {
            totalPrice += Double.parseDouble(value.get(totalPriceKey));
        }
        return totalPrice;
    }

}