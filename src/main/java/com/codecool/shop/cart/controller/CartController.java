package com.codecool.shop.cart.controller;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.service.CartService;
import com.codecool.shop.config.RouteConfiguration;
import com.codecool.shop.config.TemplateEngineUtil;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sessionId = req.getRequestedSessionId();
        double totalPrice = CartDaoMem.getInstance().getCart(sessionId).getTotalPrice();
        JsonObject json = new JsonObject();
        json.addProperty("session", sessionId);
        json.addProperty("totalPrice", totalPrice);
        // forward totalPrice to the getMethod of /checkout
        req.setAttribute("totalPrice", totalPrice);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(RouteConfiguration.URI_CHECKOUT.toString());
        try {
            rd.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        //
//        transmitToCheckOut(json);
//        resp.sendRedirect("/checkout");
    }

    private void transmitToCheckOut(JsonObject jsonObject) {
        System.out.println("TRANSMITTING");
        String payload = jsonObject.toString();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(RouteConfiguration.URI_CHECKOUT_TRANSFER);
        HttpEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        try {

            client.execute(request);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
