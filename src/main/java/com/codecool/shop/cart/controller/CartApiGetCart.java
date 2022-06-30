package com.codecool.shop.cart.controller;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.model.Cart;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/cart/session"}, loadOnStartup = 2)
public class CartApiGetCart extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartDao cartDao = CartDaoMem.getInstance();
        String sessionId = req.getParameter("id");

        Cart cart = cartDao.getCart(sessionId);
        double totalPrice = cart.getTotalPrice();

        resp.setContentType("Application/json");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("totalPrice", totalPrice);

        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject.toString());
    }

}
