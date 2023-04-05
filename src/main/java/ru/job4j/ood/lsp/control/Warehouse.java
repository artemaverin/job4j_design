package ru.job4j.ood.lsp.control;

import java.util.function.Predicate;

public class Warehouse extends AbstractStore {
    Predicate<Float> predicate;

    public Warehouse(Predicate<Float> predicate) {
        this.predicate = predicate;
    }

    public Warehouse() {
    }

    @Override
    public boolean check(float num, Food food) {
        if (predicate == null) {
            return false;
        }
        return predicate.test(num);
    }
}
