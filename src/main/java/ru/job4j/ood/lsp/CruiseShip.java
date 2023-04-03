package ru.job4j.ood.lsp;

public class CruiseShip extends Ship {
    @Override
    public void load(int capcity) {
        if (capcity > 200) {
            throw new IllegalArgumentException("перегрузка");
        }
        super.load(capcity);
    }

}
