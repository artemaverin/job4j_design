package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("%s - no such key exists", key));
        }
        return values.get(key);
    }

    public Map<String, String> map() {
        return values;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("not enough initial parameters");
        }
        for (int i = 0; i < args.length; i++) {
            String[] arr = validArray(args[i]);
            values.put(arr[0], arr[1]);
        }
    }

    private String[] validArray(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException(String.format("current line: %s does not contain '=' symbol", line));
        }
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException(String.format("current line: %s does not contain '-' symbol", line));
        }

        if (line.substring(1).startsWith("=")) {
            throw new IllegalArgumentException(String.format("current line: %s does not contain Key", line));
        }

        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException(String.format("current line: %s does not contain Value", line));
        }
        String[] arr = line.substring(1).split("=", 2);
        return arr;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
