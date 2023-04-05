package ru.job4j.ood.lsp.control;

import java.time.LocalDate;

public class Meat extends Food {
    public Meat(String name, LocalDate createDate, LocalDate expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }

}
