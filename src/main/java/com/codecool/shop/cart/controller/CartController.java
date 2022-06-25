package com.codecool.shop.cart.controller;

import com.codecool.shop.cart.CartDao;
import com.codecool.shop.cart.ProductCategoryDao;
import com.codecool.shop.cart.ProductDao;
import com.codecool.shop.cart.config.TemplateEngineUtil;
import com.codecool.shop.cart.implementation.CartDaoMem;
import com.codecool.shop.cart.implementation.ProductCategoryDaoMem;
import com.codecool.shop.cart.implementation.ProductDaoMem;
import com.codecool.shop.cart.service.CartService;
import com.codecool.shop.cart.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"}, loadOnStartup = 1)
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = (CartDao) CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartItems", cartService.getAllProducts());
        context.setVariable("occurrence", CartService::getCount);

        engine.process("cart/cart.html", context, resp.getWriter());
    }

}
