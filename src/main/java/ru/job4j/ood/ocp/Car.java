package ru.job4j.ood.ocp;

import java.util.Objects;

public class Car {
    String model;
    String carID;

    public Car(String model, String carID) {
        this.model = model;
        this.carID = carID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(carID, car.carID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID);
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", carID='" + carID + '\''
                + '}';
    }
}
