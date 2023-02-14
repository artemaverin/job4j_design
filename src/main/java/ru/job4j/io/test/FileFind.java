package ru.job4j.io.test;

import ru.job4j.io.ArgsName;
import ru.job4j.io.duplicates.FileProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFind {

    public static void start(ArgsName name) throws IOException {
        isValid(name);

        String path = name.get("d");
        String line = name.get("n");
        String type = name.get("t");

        Map<FileProperty, List<String>> temp = findBy(path, line, type);
        writeToFile(Path.of(name.get("o")), temp);
    }

    public static void writeToFile(Path path, Map<FileProperty, List<String>> map) {
        try (PrintWriter bf = new PrintWriter(new FileWriter(path.toFile()))) {
            map.entrySet().forEach(q -> {
                bf.write(q.getKey().getName() + System.lineSeparator());
                bf.write(q.getValue() + System.lineSeparator());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<FileProperty, List<String>> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFile searcher = new SearchFile(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getMap();
    }

    private static Map<FileProperty, List<String>> findBy(String path, String line, String type) throws IOException {
        Map<FileProperty, List<String>> temp;
        switch (type) {
            case "name":
                temp = search(Path.of(path), p -> p.toFile().getName().equals(line));
                break;
            case "mask":
                String str;
                if (!line.endsWith("*") || !line.endsWith("?")) {
                    str = line.replaceAll("\\*", "[\\\\w*]")
                            .replaceAll("\\?", "\\\\w") + "$";
                } else {
                    str = line.replaceAll("\\*", "[\\\\w*]")
                            .replaceAll("\\?", "\\\\w");
                }
                Pattern maskPattern = Pattern.compile(str);
                temp = search(Path.of(path), p -> maskPattern.asPredicate().test(p.toFile().getName()));
                break;
            case "regex":
                Pattern regPattern = Pattern.compile(line);
                temp = search(Path.of(path), p -> regPattern.asPredicate().test(p.toFile().getName()));
                break;
            default:
                throw new IllegalArgumentException("Введите верный шаблон");
        }
        return temp;
    }

    private static void isValid(ArgsName argsName) {
        File file = Path.of(argsName.get("d")).toFile();
        if (argsName.map().size() != 4) {
            throw new IllegalArgumentException("amount of initial parametrs is not 4");
        }
        if (!file.exists() && !file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", file));
        }
        if (!argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("%s - has incorrect file extension", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName name1 = ArgsName.of(new String[]{
                "-d=C:\\projects\\job4j_design\\data", "-n=*.?xt", "-t=regex", "-o=C:\\projects\\job4j_design\\cftdir\\Mylog.txt"});
        ArgsName name = ArgsName.of(args);
        start(name);
    }
}
