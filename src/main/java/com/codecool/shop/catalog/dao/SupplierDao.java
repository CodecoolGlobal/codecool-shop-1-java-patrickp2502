package com.codecool.shop.catalog.dao;

import com.codecool.shop.catalog.dao.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);

    List<Supplier> getAll();
}