package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String s;
            while (((s = read.readLine()) != null)) {
                if (!s.startsWith("#") && !s.isBlank()) {
                    String[] arr = s.split("=", 2);
                    if (!s.contains("=")
                            || s.startsWith("=")
                            || Objects.equals("=", s)
                            || arr[1].equals("")) {
                        throw new IllegalArgumentException("incorrect line : " + s);
                    }
                    values.put(arr[0], arr[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    public static void main(String[] args) {
        Config config = new Config("./data/pair_with_expected_inaccuracies.properties");
        System.out.println(config);
        System.out.println("________________________");
        config.load();
    }
}
