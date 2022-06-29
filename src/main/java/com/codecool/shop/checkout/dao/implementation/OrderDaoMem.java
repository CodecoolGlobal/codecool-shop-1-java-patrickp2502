package com.codecool.shop.checkout.dao.implementation;

import com.codecool.shop.checkout.dao.OrderDao;
import com.codecool.shop.checkout.model.Order;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderDaoMem implements OrderDao {
    private final List<Order> orders = new ArrayList<>();


    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public Optional<Order> find(int id) {
        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .findFirst();
    }

    @Override
    public void remove(Order order) {
        orders.remove(order);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}
