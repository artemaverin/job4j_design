package ru.job4j.ood.lsp.control;

import java.time.LocalDate;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {
    List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public ControlQuality() {
    }

    public List<Store> getStores() {
        return stores;
    }

    public void foodSort(List<Food> products) {
        for (Food product: products) {
            filter(product);
        }
    }

    private void filter(Food food) {
        float plannedDays = (float) DAYS.between(food.getExpireDate(), food.getCreateDate());
        float factDays = (float) DAYS.between(LocalDate.now(), food.getCreateDate());
        float res = (factDays / plannedDays);
        for (Store store: stores) {
            if (store.check(res, food)) {
                store.put(food);
                break;
            }
        }
    }
}
