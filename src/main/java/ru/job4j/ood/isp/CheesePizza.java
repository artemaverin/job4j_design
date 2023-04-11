package ru.job4j.ood.isp;

import javax.naming.OperationNotSupportedException;

public class CheesePizza implements CookPizza {
    @Override
    public void addSauce() {
        System.out.println("add sauce");
    }

    @Override
    public void addMushrooms() {
        try {
            throw new OperationNotSupportedException("ингридиент не соответствует рецепту");
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
