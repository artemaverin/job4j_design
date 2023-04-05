package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenShop() {
        Food milk = new MilkProduct("Milk",
                LocalDate.of(2023,  3, 31),
                LocalDate.of(2023,  4, 10), 100, 0.5);
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
                        new Trash(x -> x >= 0.25 && x < 1)
                ));
        controlQuality.func(food);
        assertThat(controlQuality.getStores().get(0).get().size()).isEqualTo(1);
    }


    @Test
    public void whenShopWithDiscount() {
        Food milk = new MilkProduct("Milk",
                LocalDate.of(2023,  3, 31),
                LocalDate.of(2023,  4, 10), 100, 0.5);
        Food fruit = new Fruit("Apple",
                LocalDate.of(2022,  10, 10),
                LocalDate.of(2023,  2, 10), 202, 0.4);
        Food meat = new Meat("Chicken",
                LocalDate.of(2023,  2, 5),
                LocalDate.of(2023,  4, 11), 400, 0.5);
        List<Food> food = new ArrayList<>();
        food.add(milk);
        food.add(fruit);
        food.add(meat);
        ControlQuality controlQuality = new ControlQuality(
                List.of(
                        new Shop(x -> x >= 0.25 && x < 1)
                ));
        controlQuality.func(food);
        assertThat(controlQuality.getStores().get(0).get().get(1).getPrice()).isEqualTo(200);
    }
}