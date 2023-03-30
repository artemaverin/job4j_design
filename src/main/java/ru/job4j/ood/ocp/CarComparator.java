package ru.job4j.ood.ocp;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.carID.compareToIgnoreCase(o2.carID);
    }
}
