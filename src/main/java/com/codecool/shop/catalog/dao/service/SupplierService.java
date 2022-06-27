package com.codecool.shop.catalog.dao.service;

import com.codecool.shop.catalog.dao.SupplierDao;
import com.codecool.shop.catalog.dao.model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao;

    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public List<Supplier> getSuppliers() {
        return supplierDao.getAll();
    }

    public Supplier findSupplier(int supplierId) {
        return supplierDao.find(supplierId);
    }



}
