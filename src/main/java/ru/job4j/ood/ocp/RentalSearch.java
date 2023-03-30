package ru.job4j.ood.ocp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RentalSearch implements Search {

    String filter;

    public RentalSearch(String filter) {
        this.filter = filter;
    }

    @Override
    public List<Car> find(List<Car> cars, String name) {
        Collections.sort(cars, new CarComparator());
        return cars.stream()
                .filter(x -> x.model.equals(name))
                .collect(Collectors.toList());
    }
}
