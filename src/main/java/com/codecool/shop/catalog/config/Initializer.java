package com.codecool.shop.catalog.config;

import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.implementation.CartDaoMem;
import com.codecool.shop.catalog.model.CartItem;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CartDao cartDataStore = CartDaoMem.getInstance();

        //setting up cart with test data
        /*cartDataStore.add(new CartItem(1, 10.30, "TV"));
        cartDataStore.add(new CartItem(2, 500.30, "USB-Cable"));*/
    }
}
