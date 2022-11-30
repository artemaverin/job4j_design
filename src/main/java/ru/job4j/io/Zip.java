package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(inputStream.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void isValid(ArgsName argsName) {
        File directory = Paths.get(argsName.get("d")).toFile();
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (!directory.exists() && !directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", directory));
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException(String.format("%s - has incorrect file extension", exclude));
        }
        if (!output.contains(".")
                || (output.indexOf(".") == output.length() - 1)
                || !List.of(".zip", ".rar").contains(output.substring(output.indexOf(".")))) {
            throw new IllegalArgumentException(String.format("%s - is not archive extension", output.substring(output.indexOf("."))));
        }

    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("not enough initial parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        isValid(argsName);
        Zip zip = new Zip();
        List<Path> list = Search.search(Path.of(argsName.get("d")),
                e -> !e.toFile().getName().endsWith(argsName.get("e")));
        File file = Path.of(argsName.get("o")).toFile();
        zip.packFiles(list, file);
    }
}
