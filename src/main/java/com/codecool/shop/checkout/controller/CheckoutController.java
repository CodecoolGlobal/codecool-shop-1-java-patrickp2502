package com.codecool.shop.checkout.controller;

import com.codecool.shop.config.RouteConfiguration;
import com.codecool.shop.config.TemplateEngineUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/checkout"}, loadOnStartup = 5)
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String sessionId = req.getRequestedSessionId();
        double cartValue = fetchCartValue(sessionId);
        context.setVariable("value", cartValue);
        templateEngine.process("checkout/index.html", context, resp.getWriter());

    }


    private double fetchCartValue(String sessionId) {
        System.out.println("Receiving");
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder(RouteConfiguration.URI_API_GET_CART_VALUE);
        builder.setParameter("id", sessionId);

        try {
            HttpGet request = new HttpGet(builder.build());
            HttpResponse response = client.execute(request);

            InputStream reader = response.getEntity().getContent();
            String jsonString = new String(reader.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.getDouble("totalPrice");

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
