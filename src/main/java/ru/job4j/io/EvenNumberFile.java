package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {

        try(FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    sb.append(((char) read));
            }
            String[] numbers = sb.toString().split(System.lineSeparator());
            for (String num : numbers) {
                if (Integer.parseInt(num) % 2 == 0) {
                    System.out.println(num + " - even");
                } else {
                    System.out.println(num + " - odd");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
