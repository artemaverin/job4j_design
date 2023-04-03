package ru.job4j.ood.lsp;

public class TransportRent {

    protected double fix;

    public TransportRent(double fix) {
        this.fix = fix;
    }

    public double getPrice(int category, String condition) {
        if (category < 0 || condition.isBlank()) {
            throw new IllegalArgumentException("некорректные данные");
        }

        double price = switch (category) {
            case 1 -> fix * 1.5;
            case 2 -> fix * 1.3;
            case 3 -> fix * 1.1;
            default -> 1000;
        };

        if (condition.equals("prepayment")) {
            price = price * 0.4;
        }
        return price;
    }

}
