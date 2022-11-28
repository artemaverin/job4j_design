package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
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
        List<String> list = new ArrayList<String>();
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (map.containsKey(fp)) {
            list = map.get(fp);
        }
        list.add(file.toFile().getAbsolutePath());
        map.putIfAbsent(fp, list);
        return super.visitFile(file, attrs);
    }
}
