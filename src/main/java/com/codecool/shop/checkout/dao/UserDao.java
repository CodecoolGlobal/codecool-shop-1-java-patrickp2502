package com.codecool.shop.checkout.dao;

import com.codecool.shop.checkout.model.User;

import java.util.List;

public interface UserDao {

    User find(int id);
    void add(User user);
    void remove(User user);
    List<User> getAll();


}
