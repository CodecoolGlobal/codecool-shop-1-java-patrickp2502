package com.codecool.shop.cart.model;

import com.codecool.shop.catalog.dao.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Cart {
    private final List<CartItem> cart;

    private String sessionId;

    public Cart() {
        cart = new ArrayList<>();
    }

    public int getCountOfSingleItem(int id) {
        return cart.get(cart.indexOf(findProductBy(id))).getCount();
    }

    public CartItem findProductBy(int id) {
        return cart
                .stream()
                .filter(cartItem -> cartItem.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(CartItem cartItem) {
        if (hasProductWithSameId(cartItem.getId())) {
            System.out.println("In cart.contains(cartItem)");
            updateCount(cartItem.getId(), 1);
            updateTotalPrice(cartItem.getId(), 1);
        } else {
            System.out.println("In not cart.contains(cartItem)");
            cartItem.setCount(1);
            cartItem.setTotalPrice(cartItem.getPrice());
            cart.add(cartItem);
        }
    }

    public boolean hasProductWithSameId(int id) {
        return cart.stream()
                .anyMatch(item -> item.getId() == id);
    }

    private void updateTotalPrice(int id, int howOften) {
        CartItem cartItem = findProductBy(id);
        double currentTotalPrice = cartItem.getTotalPrice();
        double priceToCalculateWith = cartItem.getPrice() * howOften;
        cartItem.setTotalPrice(currentTotalPrice + priceToCalculateWith);
    }

    private void updateCount(int id, int i) {
        CartItem cartItem = findProductBy(id);
        int currentCount = cartItem.getCount();
        cartItem.setCount(currentCount + i);
        if (cartItem.getCount() == 0) {
            cart.remove(cartItem);
        }
    }

    public void removeSingleProduct(int id) {
        if (cart.get(cart.indexOf(findProductBy(id))).getCount() == 1) {
            cart.remove(findProductBy(id));
        } else {
            updateCount(id, -1);
            updateTotalPrice(id, -1);
        }
    }

    public double getTotalPrice() {
        return cart
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void setCount(int productId, int newCount) {
        CartItem cartItem = findProductBy(productId);
        if (newCount <= 0) {
            cart.remove(cartItem);
        } else {
            cartItem.setCount(newCount);
            cartItem.setTotalPrice(cartItem.getPrice() * newCount);
        }
    }
}