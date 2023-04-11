package ru.job4j.ood.lsp.parking;

public class Truck extends AbstractAuto {

    private double size;

    public Truck(String model, int number, double size) {
        super(model, number);
        if (size <= 1) {
            throw new IllegalArgumentException("указанный размер не соответствует грузовому авто");
        }
        this.size = size;
    }

    @Override
    public double getSize() {
        return size;
    }

}
