package ru.job4j.ood.ocp;

import java.util.List;

public interface Search {
    List<Car> find(List<Car> cars, String name);
}
