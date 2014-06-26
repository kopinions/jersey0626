package org.thoughtworks.com.domain;

public class Product {
    private int id;
    private String name;
    private Price currentPrice;

    public Product() {
    }

    public Product(int id, String productName) {

        this.id = id;
        name = productName;
    }

    public Product(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
