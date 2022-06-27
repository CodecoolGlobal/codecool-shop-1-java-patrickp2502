package com.codecool.shop.config;

import com.codecool.shop.catalog.dao.ProductCategoryDao;
import com.codecool.shop.catalog.dao.ProductDao;
import com.codecool.shop.catalog.dao.SupplierDao;
import com.codecool.shop.catalog.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.catalog.dao.implementation.ProductDaoMem;
import com.codecool.shop.catalog.dao.implementation.SupplierDaoMem;
import com.codecool.shop.catalog.dao.model.Product;
import com.codecool.shop.catalog.dao.model.ProductCategory;
import com.codecool.shop.catalog.dao.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier intel = new Supplier("Intel", "Hardware and Infrastructure");
        supplierDataStore.add(intel);
        Supplier apple = new Supplier("apple", "Overpriced electronic and entertainment systems");
        supplierDataStore.add(apple);
        Supplier lg = new Supplier("LG", "Mostly tvs and stuff");
        supplierDataStore.add(lg);
        Supplier msi = new Supplier("MSI", "graphic card woow");
        supplierDataStore.add(msi);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory tv = new ProductCategory("TV", "Hardware", "An entertainment system for watching movies and documentaries.");
        productCategoryDataStore.add(tv);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "A handheld communicatin device. Used for telecommunication and messaging service. Also watching cat movies 24/7");
        productCategoryDataStore.add(smartphone);
        ProductCategory graphicCard = new ProductCategory("Graphic-Card", "Hardware", "Graphiccards");
        productCategoryDataStore.add(graphicCard);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("LG OLED 65C17LB", new BigDecimal("1487"), "USD", "AI Picture Pro und AI Sound Pro, Dolby Atmos-fähigem 2.2-Soundsyste", tv, lg));
        productDataStore.add(new Product("MSI GeForce RTX 3070 Gaming Z Trio", new BigDecimal("691.80"), "USD", "WOOOWI Grahpic card brrruuuuum", graphicCard, msi));
    }
}
