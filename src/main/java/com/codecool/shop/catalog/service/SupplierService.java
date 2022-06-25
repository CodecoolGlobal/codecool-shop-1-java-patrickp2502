package com.codecool.shop.catalog.service;

import com.codecool.shop.catalog.ProductCategoryDao;
import com.codecool.shop.catalog.SupplierDao;
import com.codecool.shop.catalog.model.ProductCategory;
import com.codecool.shop.catalog.model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao;

    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public List<Supplier> getSuppliers() {
        return supplierDao.getAll();
    }



}
