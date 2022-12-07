package ru.job4j.serialization.json;

import java.util.Arrays;

public class PlayerSerializable {
    private final boolean hasArmor;
    private final int health;
    private final String[] inventory;
    private final Pet pet;

    public PlayerSerializable(boolean hasArmor, int health, String[] inventory, Pet pet) {
        this.hasArmor = hasArmor;
        this.health = health;
        this.inventory = inventory;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "PlayerSerializable{"
                + "hasArmor=" + hasArmor
                + ", health=" + health
                + ", inventory=" + Arrays.toString(inventory)
                + ", pet=" + pet
                + '}';
    }
}
