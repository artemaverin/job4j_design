package ru.job4j.ood.lsp.control;

import java.util.List;

public interface Store {
    List<Food> get();
    boolean check(float num, Food food);
    void put(Food food);
}
