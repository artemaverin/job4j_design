package ru.job4j.ood.lsp.parking;

public abstract class AbstractAuto implements Auto {

    private String model;
    private int number;

    public AbstractAuto(String model, int number) {
        this.model = model;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "model='" + model + '\''
                + ", number=" + number
                + '}';
    }
}
