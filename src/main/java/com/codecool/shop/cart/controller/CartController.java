package com.codecool.shop.cart.controller;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.config.TemplateEngineUtil;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.service.CartService;
import com.google.gson.JsonObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/cart/show"}, loadOnStartup = 1)
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDao);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String sessionId = req.getRequestedSessionId();
        context.setVariable("cart", cartService.getCart(sessionId));
        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String sessionId = req.getRequestedSessionId();
        double totalPrice = CartDaoMem.getInstance().getCart(sessionId).getTotalPrice();
        JsonObject json = new JsonObject();
        json.addProperty("session", sessionId);
        json.addProperty("totalPrice", totalPrice);
        transmitToCheckOut(json);
    }

    private void transmitToCheckOut(JsonObject jsonObject) {
        try {
            URL url = new URL("/api/checkout");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStream out = connection.getOutputStream();
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            out.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
