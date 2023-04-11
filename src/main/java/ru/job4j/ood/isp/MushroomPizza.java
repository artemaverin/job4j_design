package ru.job4j.ood.isp;

public class MushroomPizza implements CookPizza {
    @Override
    public void addSauce() {
        System.out.println("add sauce");
    }

    @Override
    public void addMushrooms() {
        System.out.println("add mushrooms");
    }
}
