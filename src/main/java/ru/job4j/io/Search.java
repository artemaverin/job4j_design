package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (!isValid(Path.of(args[0]), args[1])) {
            throw new IllegalArgumentException("Enter correct arguments");
        }
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static boolean isValid(Path path, String format) {
        return path.toFile().exists() && format.startsWith(".");
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
