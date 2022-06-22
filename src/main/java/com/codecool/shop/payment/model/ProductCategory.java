package com.codecool.shop.payment.model;

import com.codecool.shop.catalog.model.BaseModel;
import com.codecool.shop.catalog.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;
    private List<com.codecool.shop.catalog.model.Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}