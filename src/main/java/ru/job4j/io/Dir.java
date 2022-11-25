package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.stream.Stream;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Dir {

    public static void main(String[] args) {
        File file = new File("C:\\projects\\job4j_design");
        long size = 0;
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isDirectory()) {
                try (Stream<Path> walk = Files.walk(subfile.toPath())) {
                    size = walk
                            .filter(Files::isRegularFile)
                            .mapToLong(p -> {
                                try {
                                    return Files.size(p);
                                } catch (IOException e) {
                                    System.out.printf("Невозможно получить размер файла %s%n%s", p, e);
                                    return 0L;
                                }
                            })
                            .sum();
                } catch (IOException e) {
                    System.out.printf("Ошибка при подсчёте размера директории %s", e);
                }

                System.out.printf("Имя директории : %15s , размер : %10s bytes %n", subfile.getName()
                        , size);
            } else {
                System.out.printf("Имя файла : %20s , размер : %10s bytes %n", subfile.getName(), subfile.length());
            }

        }
    }
}
