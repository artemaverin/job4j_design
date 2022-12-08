package ru.job4j.serialization.xml;

public class Product {
    private final String product;

    public Product(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Product{"
                + "product='" + product + '\''
                + '}';
    }
}
