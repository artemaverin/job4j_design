package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {

    @XmlAttribute
    private String product;

    public Product() { };

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
