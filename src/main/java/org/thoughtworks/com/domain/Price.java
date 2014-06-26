package org.thoughtworks.com.domain;

public class Price {
    private final double price;
    private int id;

    public Price(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
