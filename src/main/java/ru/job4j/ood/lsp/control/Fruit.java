package ru.job4j.ood.lsp.control;

import java.time.LocalDate;

public class Fruit extends Food {
    public Fruit(String name, LocalDate createDate, LocalDate expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
