package ru.job4j.serialization.json;

public class Pet {

    private final String petType;

    public Pet(String petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "Pet{"
                + "petType='" + petType + '\''
                + '}';
    }
}
