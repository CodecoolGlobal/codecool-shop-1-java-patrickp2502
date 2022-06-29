package com.codecool.shop.checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;


}
