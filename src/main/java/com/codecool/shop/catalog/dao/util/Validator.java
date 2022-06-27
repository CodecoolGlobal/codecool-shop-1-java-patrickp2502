package com.codecool.shop.catalog.dao.util;

public class Validator {

    public static boolean isStringNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
