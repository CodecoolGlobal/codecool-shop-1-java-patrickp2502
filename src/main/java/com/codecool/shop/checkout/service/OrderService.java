package com.codecool.shop.checkout.service;

import com.codecool.shop.checkout.dao.AddressDao;
import com.codecool.shop.checkout.dao.OrderDao;
import com.codecool.shop.checkout.dao.UserDao;
import com.codecool.shop.checkout.model.Address;
import com.codecool.shop.checkout.model.Order;
import com.codecool.shop.checkout.model.User;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final AddressDao addressDao;
    private final UserDao userDao;


    public Order placeOrder(User user, Address deliveryAddress, Address billingAddress) {


        return null;

    }


}
