package org.thoughtworks.com.domain;

public class Product {
    private int id;
    private String name;

    public Product(int id) {

        this.id = id;
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
