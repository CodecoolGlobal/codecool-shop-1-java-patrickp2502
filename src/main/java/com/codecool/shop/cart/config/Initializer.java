package com.codecool.shop.cart.config;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.implementation.CartDaoMem;

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
