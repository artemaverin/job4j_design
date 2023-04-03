package ru.job4j.ood.lsp;

public class Passport {
    protected int series;
    protected int number;

    public Passport(int series, int number) {
        this.series = series;
        this.number = number;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        validateSeries(series);
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    protected void validateSeries(int series) {
        if (series < 1000 || series > 9999) {
            throw new IllegalArgumentException("Неверная серия пасспорта.");
        }
    }

    protected void validateNumber(int number) {
        if (number < 100000 || number > 999999) {
            throw new IllegalArgumentException("Неверный номер пасспорта.");
        }
    }
}
