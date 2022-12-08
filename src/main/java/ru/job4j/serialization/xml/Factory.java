package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Factory {

    private final boolean isSubsidized;
    private final int personalQuantity;
    private final String name;
    private final Product product;
    private final String[] units;


    public Factory(boolean isSubsidized, int personalQuantity, String name, Product product, String[] units) {
        this.isSubsidized = isSubsidized;
        this.personalQuantity = personalQuantity;
        this.name = name;
        this.product = product;
        this.units = units;
    }

    @Override
    public String toString() {
        return "Factory{"
                + "isSubsidized=" + isSubsidized
                + ", quantity=" + personalQuantity
                + ", name=" + name
                + ", product=" + product
                + ", units=" + Arrays.toString(units)
                + '}';
    }
}
