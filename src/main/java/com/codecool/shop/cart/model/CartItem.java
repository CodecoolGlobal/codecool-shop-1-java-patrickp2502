package com.codecool.shop.cart.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CartItem {

    private final int id;

    private final double price;

    private final String name;

    private int count;

    private double totalPrice;


}
