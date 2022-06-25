package com.codecool.shop.cart.implementation;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartDaoMem implements CartDao {

    List<Product> data = new ArrayList<>();

    ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(int id) {
        data.add(productDaoMem.find(id));
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public int getProductCount(int id) {
        return data
                .stream()
                .filter(product -> product.getId() == id)
                .collect(Collectors.toList())
                .size();
    }
}
