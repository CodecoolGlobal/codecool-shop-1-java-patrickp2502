package com.codecool.shop.checkout.dao;

import com.codecool.shop.checkout.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void add(Order order);
    Optional<Order> find(int id);
    void remove(Order order);
    List<Order> getAll();

}
