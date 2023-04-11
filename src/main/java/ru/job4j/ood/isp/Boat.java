package ru.job4j.ood.isp;

import javax.naming.OperationNotSupportedException;

public class Boat implements Vehicle {
    @Override
    public void startEngine() {
        System.out.println("завести мотор катера");
    }

    @Override
    public void brake() {
        System.out.println("нажать на тормоз");
    }

    @Override
    public void accelerate() {
        System.out.println("произвести ускорение катера");
    }

    @Override
    public void reverse() {
        try {
            throw new OperationNotSupportedException("задний ход отсутствует");
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void autoParking() {
        try {
            throw new OperationNotSupportedException("автопарковка не предумотрена");
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
