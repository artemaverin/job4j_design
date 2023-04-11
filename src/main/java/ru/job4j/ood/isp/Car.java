package ru.job4j.ood.isp;

public class Car implements Vehicle {
    @Override
    public void startEngine() {
        System.out.println("завести двигатель машины");
    }

    @Override
    public void brake() {
        System.out.println("нажать тормоз");
    }

    @Override
    public void accelerate() {
        System.out.println("повысить передачу");
    }

    @Override
    public void reverse() {
        System.out.println("дать задний ход");
    }

    @Override
    public void autoParking() {
        System.out.println("включить режим автопарковки");
    }
}
