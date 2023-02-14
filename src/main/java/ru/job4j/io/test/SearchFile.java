package ru.job4j.io.test;

import ru.job4j.io.duplicates.FileProperty;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SearchFile extends SimpleFileVisitor<Path> {

    private final Predicate<Path> condition;

    private final Map<FileProperty, List<String>> map = new HashMap<>();

    public SearchFile(Predicate<Path> condition) {
        this.condition = condition;
    }

    public Map<FileProperty, List<String>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
            map.putIfAbsent(fp, new ArrayList<>());
            map.get(fp).add(file.toFile().getAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

}
