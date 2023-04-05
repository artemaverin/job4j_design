package ru.job4j.ood.lsp.control;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> warehouse = new ArrayList<>();

    @Override
    public List<Food> get() {
        return new ArrayList<>(warehouse);
    }

    @Override
    public void put(Food food) {
        if (food == null) {
            throw  new IllegalArgumentException();
        }
        warehouse.add(food);
    }
}
