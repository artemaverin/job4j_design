package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class ControlQualityTest {

    @Test
    public void whenControlQuality() {
        Food milk = new MilkProduct("Milk",
                LocalDate.of(2023,  3, 31),
                LocalDate.of(2023,  4, 10), 100, 0.5);
        Food fruit = new Fruit("Apple",
                LocalDate.of(2022,  10, 10),
                LocalDate.of(2023,  2, 10), 202, 0.4);
        Food meat = new Meat("Chicken",
                LocalDate.of(2023,  2, 5),
                LocalDate.of(2023,  4, 11), 400, 0.5);
        Food orange = new Fruit("Orange",
                LocalDate.of(2023,  4, 4),
                LocalDate.of(2023,  4, 13), 100, 0.5);
        List<Food> food = new ArrayList<>();
        food.add(milk);
        food.add(fruit);
        food.add(meat);
        food.add(orange);
        ControlQuality controlQuality = new ControlQuality(
                List.of(
                        new Warehouse(x -> x >= 0 && x < 0.25),
                        new Shop(x -> x >= 0.25 && x < 1),
                        new Trash(x -> x > 1)
                ));
        controlQuality.foodSort(food);
        assertThat(controlQuality.getStores().get(0).get().get(0).getName()).isEqualTo("Orange");
        assertThat(controlQuality.getStores().get(1).get().get(0).getName()).isEqualTo("Milk");
        assertThat(controlQuality.getStores().get(1).get().get(1).getName()).isEqualTo("Chicken");
        assertThat(controlQuality.getStores().get(2).get().get(0).getName()).isEqualTo("Apple");
    }

}