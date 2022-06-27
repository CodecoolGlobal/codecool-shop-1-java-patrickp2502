package com.codecool.shop.checkout.model;

import java.math.BigDecimal;

public record Order(int id, Customer customer, Address billingAddress, Address deliveryAddress, BigDecimal value) {
}