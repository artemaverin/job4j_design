package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        isValid(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void isValid(String[] args) {
        File file = Path.of(args[0]).toFile();
        if (args.length < 2) {
            throw new IllegalArgumentException("not enough initial parameters");
        }
        if (!file.exists() && !file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", file));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("%s - has incorrect file extension", args[1]));
        }

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
