package com.codecool.shop.checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    private final int zip;
    private final String street;
    private final String country;
    private final String city;

}
