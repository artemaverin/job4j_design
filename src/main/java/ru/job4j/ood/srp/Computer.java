package ru.job4j.ood.srp;

public class Computer {
    String name;
    int memory;

    public Computer(String name, int memory) {
        this.name = name;
        this.memory = memory;
    }

    public void save() {
        System.out.println("Сохранение объекта класса");
    }

    public void load() {
        System.out.println("Загрузка объекта класса");
    }
}
