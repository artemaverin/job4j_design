package ru.job4j.io;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try (FileOutputStream out = new FileOutputStream("result.txt", true)) {
                    out.write((i + " * " + j + " = " + i * j).getBytes());
                    out.write(System.lineSeparator().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
