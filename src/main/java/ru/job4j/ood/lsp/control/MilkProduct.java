package ru.job4j.ood.lsp.control;

import java.time.LocalDate;

public class MilkProduct extends Food {
    public MilkProduct(String name, LocalDate createDate, LocalDate expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }

}
