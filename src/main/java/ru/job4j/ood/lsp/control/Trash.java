package ru.job4j.ood.lsp.control;

import java.util.function.Predicate;

public class Trash extends AbstractStore {
    private Predicate<Float> predicate;

    public Trash(Predicate<Float> predicate) {
        this.predicate = predicate;
    }

    public Trash() {
    }

    @Override
    public boolean check(float num, Food food) {
        if (predicate == null) {
            return false;
        }
        return predicate.test(num);
    }
}
