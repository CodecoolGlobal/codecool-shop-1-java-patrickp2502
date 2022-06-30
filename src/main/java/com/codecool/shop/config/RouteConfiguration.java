package com.codecool.shop.config;

import java.net.URI;
import java.net.URISyntaxException;

public class RouteConfiguration {

    public final static URI URI_CART_TRANSFER;
    public final static URI URI_API_GET_CART_VALUE;

    static {
        try {
            URI_CART_TRANSFER = new URI("http://0.0.0.0:8080/api/cart");
            URI_API_GET_CART_VALUE = new URI("http://0.0.0.0:8080/api/cart/session");


        } catch (URISyntaxException e) {
            throw new RuntimeException("bad uri");
        }
    }
}
