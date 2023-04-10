package ru.job4j.ood.lsp.parking;

public class Car extends AbstractAuto {
    private static final double SIZE = 1;

    public Car(String model, int number) {
        super(model, number);
    }

    @Override
    public double getSize() {
        return SIZE;
    }

}
