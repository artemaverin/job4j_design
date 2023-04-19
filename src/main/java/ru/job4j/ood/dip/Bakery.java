package ru.job4j.ood.dip;

public class Bakery {
    public Pancake bakePancake() {
        return new Pancake();
    }
}

class Pancake {
    String name;
}
