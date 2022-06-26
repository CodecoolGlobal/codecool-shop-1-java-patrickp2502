package com.codecool.shop.catalog.implementation;

import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.model.Cart;
import com.codecool.shop.catalog.model.Product;

import java.util.List;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;
    Cart cart = new Cart();
    ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

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
        cart.add(productDaoMem.find(id));
    }

    @Override
    public Product find(int id) {
        return cart.findProductBy(id);
    }

    @Override
    public void remove(int id) {
        cart.removeSingleProduct(id);
    }

    @Override
    public List<Product> getAll() {
        return cart.getProducts();
    }

    @Override
    public int getProductCount(int id) {
        return cart.getCountOfSingleProduct(id);
    }

    @Override
    public Cart getCart() {
        return this.cart;
    }
}