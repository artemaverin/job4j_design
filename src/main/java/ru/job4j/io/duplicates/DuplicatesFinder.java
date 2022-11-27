package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./data/"), dv);
        dv.getMap().entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(q -> {
            System.out.println(q.getKey().getName() + " " + q.getKey().getSize());
            q.getValue().forEach(System.out::println);
        });
    }
}
