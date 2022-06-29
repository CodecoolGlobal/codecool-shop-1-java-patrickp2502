package com.codecool.shop.checkout.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@AllArgsConstructor
public class Order {
    private final int orderId;
    private final User user;
    private final BigDecimal value;
    private final Address billingAddress;
    private final Address deliveryAddress;
    private final Currency currency;

}
