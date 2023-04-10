package ru.job4j.ood.lsp.parking;

public class Truck extends AbstractAuto {

    private double size;

    public Truck(String model, int number, double size) {
        super(model, number);
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public double getSize() {
        return size;
    }

}
