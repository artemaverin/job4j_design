package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    public void whenTrash() {
        Food milk = new MilkProduct("Milk",
                LocalDate.of(2023,  3, 4),
                LocalDate.of(2023,  4, 4), 100, 0.5);
        Food fruit = new Fruit("Apple",
                LocalDate.of(2022,  10, 10),
                LocalDate.of(2023,  2, 10), 202, 0.4);
        Food meat = new Meat("Chicken",
                LocalDate.of(2023,  4, 5),
                LocalDate.of(2023,  4, 11), 100, 0.5);
        List<Food> food = new ArrayList<>();
        food.add(milk);
        food.add(fruit);
        food.add(meat);
        ControlQuality controlQuality = new ControlQuality(
                List.of(
                        new Trash(x -> x > 1)

                ));
        controlQuality.func(food);
        assertThat(controlQuality.getStores().get(0).get().size()).isEqualTo(2);
    }

}