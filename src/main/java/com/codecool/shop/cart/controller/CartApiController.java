package com.codecool.shop.cart.controller;


import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.model.Cart;
import com.codecool.shop.cart.model.CartItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@WebServlet(urlPatterns = {"/api/cart"}, loadOnStartup = 2)
public class CartApiController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TEST before cartDao declaring");
        CartDao cartDaoDataStorage = CartDaoMem.getInstance();
        System.out.println("TEST after cartDao declaring");
        int id = Integer.parseInt(Arrays.toString(req.getParameterValues("id")));
        System.out.println("id = " + id);
        double price = Double.parseDouble(Arrays.toString(req.getParameterValues("price")));
        System.out.println("price = " + price);
        String name = Arrays.toString(req.getParameterValues("name"));
        System.out.println("name = " + name);
        String sessionId = Arrays.toString(req.getParameterValues("session"));
        System.out.println("sessionId = " + sessionId);

//        get Cart of this Session
//        ?does a cartDaoMem hold a List of all active carts?
        CartItem cartItem = new CartItem(id, price, name);
//        add CartItem into the cart of the Session
        cartDaoDataStorage.getCart(sessionId).add(cartItem);
//        cartDaoDataStorage.getCart().add(cartItem);
    }

}
