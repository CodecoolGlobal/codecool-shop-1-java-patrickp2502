package com.codecool.shop.catalog.controller;


import com.codecool.shop.catalog.CartDao;
import com.codecool.shop.catalog.implementation.CartDaoMem;
import com.codecool.shop.catalog.model.CartItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@WebServlet(urlPatterns = {"/api/cart"}, loadOnStartup = 1)
public class CartApiController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CartDao cartDaoDataStorage = CartDaoMem.getInstance();
        int id = Integer.parseInt(Arrays.toString(req.getParameterValues("id")));
        double price = Double.parseDouble(Arrays.toString(req.getParameterValues("price")));
        String name = Arrays.toString(req.getParameterValues("name"));
        String sessionId = Arrays.toString(req.getParameterValues("session"));
//        get Cart of this Session
//        ?does a cartDaoMem hold a List of all active carts?
        CartItem cartItem = new CartItem(id, price, name);
//        add CartItem into the cart of the Session
//        cartDaoDataStorage.getCart().add(cartItem);
    }

}
