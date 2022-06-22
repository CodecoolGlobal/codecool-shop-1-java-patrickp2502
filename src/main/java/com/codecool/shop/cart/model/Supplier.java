package com.codecool.shop.cart.model;

import com.codecool.shop.catalog.model.BaseModel;
import com.codecool.shop.catalog.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<com.codecool.shop.catalog.model.Product> products;

    public Supplier(String name, String description) {
        super(name);
        this.products = new ArrayList<>();
    }

    public void setProducts(ArrayList<com.codecool.shop.catalog.model.Product> products) {
        this.products = products;
    }

    public List<com.codecool.shop.catalog.model.Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}