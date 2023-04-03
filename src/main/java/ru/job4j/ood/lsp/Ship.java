package ru.job4j.ood.lsp;

public class Ship {

    public void load(int capcity) {
        if (capcity < 100) {
            throw new IllegalArgumentException("недогруз");
        }
        System.out.println("other logic");
    }
}
