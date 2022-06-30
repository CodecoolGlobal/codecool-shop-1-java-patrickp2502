package com.codecool.shop.config;

import java.net.URI;
import java.net.URISyntaxException;

public class RouteConfiguration {

    public final static URI URI_CART_TRANSFER;
    public final static URI URI_CHECKOUT_TRANSFER;
    public final static URI URI_CHECKOUT;


    static {
        try {
            URI_CART_TRANSFER = new URI("http://0.0.0.0:8080/api/cart");
            URI_CHECKOUT_TRANSFER = new URI("http://0.0.0.0:8080/api/checkout");
            URI_CHECKOUT = new URI("http://0.0.0.0:8080/checkout");

        } catch (URISyntaxException e) {
            throw new RuntimeException("bad uri");
        }
    }
}
