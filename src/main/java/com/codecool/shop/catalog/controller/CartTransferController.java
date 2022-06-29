package com.codecool.shop.catalog.controller;

import com.codecool.shop.catalog.dao.ProductCategoryDao;
import com.codecool.shop.catalog.dao.ProductDao;
import com.codecool.shop.catalog.dao.SupplierDao;
import com.codecool.shop.catalog.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.catalog.dao.implementation.ProductDaoMem;
import com.codecool.shop.catalog.dao.implementation.SupplierDaoMem;
import com.codecool.shop.catalog.dao.model.Product;
import com.codecool.shop.catalog.service.ProductService;
import com.codecool.shop.catalog.util.Validator;
import com.codecool.shop.config.RouteConfiguration;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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


    private void transmitToCart(JsonObject json) {
        System.out.println("TRANSMITTING");
        String payload = json.toString();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(RouteConfiguration.URI_CART_TRANSFER);
        HttpEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        try {

            client.execute(request);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




/*
    private void transmitToCart(JsonObject jsonObject) {
        try {
            URL url = new URL("http://0.0.0.0:8080/api/cart");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream();
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            out.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
*/

}
