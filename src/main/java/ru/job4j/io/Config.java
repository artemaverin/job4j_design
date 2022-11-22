package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
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
                if (!s.contains("#") && !s.isEmpty()) {
                    String[] arr = s.split("=");
                    if (arr.length < 2 || "".equals(arr[0])) {
                        throw new IllegalArgumentException("incorrect line : " + s);
                    } else if (arr.length > 2 && !("".equals(arr[2]))) {
                        values.put(arr[0], arr[2]);
                    } else {
                        values.put(arr[0], arr[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return key != null ? values.get(key) : null;
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
