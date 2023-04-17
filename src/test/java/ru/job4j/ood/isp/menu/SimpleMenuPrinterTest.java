package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void Test() {
        Menu menu = new SimpleMenu();
        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        printer.print(menu);
        Assertions.assertEquals("Сходить в магазин 1." + System.lineSeparator()
                + "---Купить продукты 1.1." + System.lineSeparator()
                + "------Купить хлеб 1.1.1." + System.lineSeparator()
                + "------Купить молоко 1.1.2." + System.lineSeparator()
                + "Покормить собаку 2." + System.lineSeparator(), output.toString());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

}