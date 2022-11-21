package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> res = new ArrayList<>();
        if (file != null) {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                res = in.lines()
                        .filter(e -> e.contains("\" 404 "))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file, true)))) {
            String[] numbers = log.toString().substring(1, log.toString().length() - 1).split(", ");
            for (String num : numbers) {
                out.printf("%s%n", num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
