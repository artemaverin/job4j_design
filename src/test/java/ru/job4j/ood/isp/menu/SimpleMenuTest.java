package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = () -> System.out.println("");

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
    }

    @Test
    public void whenAddThenCheckSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Доктор Хаус", STUB_ACTION);
        menu.add(Menu.ROOT, "Скорая помощь", STUB_ACTION);
        menu.add(Menu.ROOT, "Декстер", STUB_ACTION);
        menu.add("Доктор Хаус", "ДХ_Сезон 1", STUB_ACTION);
        menu.add("Доктор Хаус", "ДХ_Сезон 2", STUB_ACTION);
        menu.add("ДХ_Сезон 1", "Pilot/Everybody lies", STUB_ACTION);
        menu.add("ДХ_Сезон 1", "Paternity", STUB_ACTION);
        menu.add("Paternity", "Paternity Part 1", STUB_ACTION);
        menu.add("Paternity", "Paternity Part 2", STUB_ACTION);
        menu.add("ДХ_Сезон 2", "Acceptance", STUB_ACTION);
        menu.add("Скорая помощь", "СП_Сезон 1", STUB_ACTION);
        menu.add("СП_Сезон 1", "24 Hours", STUB_ACTION);
        menu.add("СП_Сезон 1", "Day One", STUB_ACTION);
        menu.add("Декстер", "ДКС_Сезон 1", STUB_ACTION);
        menu.add("ДКС_Сезон 1", "Dexter", STUB_ACTION);
        menu.add("ДКС_Сезон 1", "Crocodile", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Paternity",
                List.of("Paternity Part 1", "Paternity Part 2"), STUB_ACTION, "1.1.2."))
                .isEqualTo(menu.select("Paternity").get());
        assertThat(new Menu.MenuItemInfo("СП_Сезон 1", List.of("24 Hours", "Day One"), STUB_ACTION, "2.1."))
                .isEqualTo(menu.select("СП_Сезон 1").get());
        assertThat(new Menu.MenuItemInfo("Декстер",
                List.of("ДКС_Сезон 1"), STUB_ACTION, "3."))
                .isEqualTo(menu.select("Декстер").get());
    }
}