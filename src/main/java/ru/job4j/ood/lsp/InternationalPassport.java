package ru.job4j.ood.lsp;

public class InternationalPassport extends Passport {
    public InternationalPassport(int series, int number) {
        super(series, number);
    }

    @Override
    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }
}
