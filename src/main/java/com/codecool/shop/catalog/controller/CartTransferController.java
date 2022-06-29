package com.codecool.shop.catalog.controller;

import com.codecool.shop.catalog.ProductCategoryDao;
import com.codecool.shop.catalog.ProductDao;
import com.codecool.shop.catalog.SupplierDao;
import com.codecool.shop.catalog.implementation.ProductCategoryDaoMem;
import com.codecool.shop.catalog.implementation.ProductDaoMem;
import com.codecool.shop.catalog.implementation.SupplierDaoMem;
import com.codecool.shop.catalog.model.Product;
import com.codecool.shop.catalog.service.ProductService;
import com.codecool.shop.catalog.util.Validator;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/cart/add"}, loadOnStartup = 2)
public class CartTransferController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        String sessionId = req.getRequestedSessionId();
        System.out.println("sessionId = " + sessionId);
        String parameterProductId = req.getParameter("id");
        if (!Validator.isStringNumber(parameterProductId)) {
            return;
        }
        int productId = Integer.parseInt(parameterProductId);
        Product product = productService.getProductById(productId);
        JsonObject json = new JsonObject();
        json.addProperty("session", sessionId);
        json.addProperty("id", parameterProductId);
        json.addProperty("name", product.getName());
        json.addProperty("price-value", product.getDefaultPrice());
        json.addProperty("currency", product.getDefaultCurrency().toString());
        System.out.println("json = " + json);
        transmitToCart(json);
        resp.sendRedirect("/");
    }


    /*private void transmitToCart(JsonObject jsonObject) {
        try {
            URL url = new URL("http://0.0.0.0:8080/api/cart");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream();
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            out.write(input, 0, input.length);
            out.flush();
            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/

    private  void transmitToCart(JsonObject jsonObject) {
        HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://0.0.0.0:8080/api/cart")).build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of transmitToCart");
    }
}