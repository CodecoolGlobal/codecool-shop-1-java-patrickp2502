package com.codecool.shop.catalog.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CartItem {

    private final int id;

    private final double price;

    private final String name;

    private int count;

    private double totalPrice;


}
