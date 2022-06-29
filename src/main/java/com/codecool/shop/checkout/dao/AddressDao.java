package com.codecool.shop.checkout.dao;

import com.codecool.shop.checkout.model.Address;

import java.util.List;

public interface AddressDao {
    void add(Address address);
    void remove(Address address);
    List<Address> getAll();




}
