package ru.job4j.ood.lsp.control;

import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private Predicate<Float> predicate;

    public Shop(Predicate<Float> predicate) {
        this.predicate = predicate;
    }

    public Shop() {
    }

    @Override
    public boolean check(float num, Food food) {
        if (predicate == null) {
            return false;
        }
        if (num > 0.75 && num <= 1) {
            food.setPrice(food.getPrice() * food.getDiscount());
        }
        return predicate.test(num);
    }
}
