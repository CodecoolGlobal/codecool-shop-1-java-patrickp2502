package com.codecool.shop.checkout.controller;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/cart"}, loadOnStartup = 6)
public class postController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("worked post");
        String data = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        System.out.println("Here is the received data");
        System.out.println("data = " + data);
        resp.sendRedirect("/");


    }
}
