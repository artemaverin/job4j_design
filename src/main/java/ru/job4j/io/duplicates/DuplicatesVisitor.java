package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<String>> map = new HashMap<>();

    public Map<FileProperty, List<String>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        map.putIfAbsent(fp, new ArrayList<String>());
        map.get(fp).add(file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getDuplicate(Path path) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(path, dv);
        dv.getMap().entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(q -> {
            System.out.println(q.getKey().getName() + " " + q.getKey().getSize());
            q.getValue().forEach(System.out::println);
        });
    }
}
