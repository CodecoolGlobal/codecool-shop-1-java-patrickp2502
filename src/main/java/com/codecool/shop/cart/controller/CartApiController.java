package com.codecool.shop.cart.controller;


import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.model.Cart;
import com.codecool.shop.cart.model.CartItem;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(urlPatterns = {"/api/cart"}, loadOnStartup = 2)
public class CartApiController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        //parses Json string to Json Object, so you can access like a hashmap
        JSONObject jsonObject = new JSONObject(data);

        CartDao cartDaoDataStorage = CartDaoMem.getInstance();
        int id = jsonObject.getInt("id");
        double price = jsonObject.getDouble("price-value");
        String name = jsonObject.getString("name");
        String sessionId = jsonObject.getString("session");

//        get Cart of this Session
//        ?does a cartDaoMem hold a List of all active carts?
        CartItem cartItem = new CartItem(id, price, name);
//        add CartItem into the cart of the Session
        Cart cart = cartDaoDataStorage.getCart(sessionId);
        cart.add(cartItem);

        resp.sendRedirect("/");

    }

}
