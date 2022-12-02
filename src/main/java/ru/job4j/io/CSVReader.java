package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String[]> list = new ArrayList<>();
        StringJoiner sj = new StringJoiner("");
        List<Integer> indexList = new ArrayList<>();
        String[] filters = argsName.get("filter").split(",");
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path"))).useDelimiter(",");
                PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine().split(argsName.get("delimiter")));
            }
            for (int i = 0; i < filters.length; i++) {
                for (int j = 0; j < list.get(0).length; j++) {
                    if (list.get(0)[j].equals(filters[i])) {
                        indexList.add(j);
                    }
                }
            }

            for (int i = 0; i < list.get(0).length; i++) {
                for (Integer n : indexList) {
                    if (Objects.equals(n, indexList.get(indexList.size() - 1))) {
                        sj.add(list.get(i)[n]);
                    } else {
                        sj.add(list.get(i)[n]);
                        sj.add(";");
                    }
                }
                sj.add(System.lineSeparator());
            }
            out.write(sj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void isValid(ArgsName argsName) {
        File path = Paths.get(argsName.get("path")).toFile();
        String delimiter = argsName.get("delimiter");
        File out = Paths.get(argsName.get("out")).toFile();
        String filter = argsName.get("filter");
        if (!path.exists() && !path.isFile()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", path));
        }
        if (!delimiter.matches("^\\W")) {
            throw new IllegalArgumentException(String.format("%s - is not correct delimiter", delimiter));
        }
        if (!out.exists() && !out.isFile()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", out));
        }

    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("not enough initial parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        isValid(argsName);
        handle(argsName);
    }
}
