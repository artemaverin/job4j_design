package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> T compareBy(List<T> value, Comparator<T> comparator) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("список пуст");
        }
        T res = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(value.get(i), res) > 0) {
                res = value.get(i);
            }
        }
        return res;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compareBy(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compareBy(value, comparator.reversed());
    }
}
