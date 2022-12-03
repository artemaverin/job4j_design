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
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
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
                        sj.add(argsName.get("delimiter"));
                    }
                }
                sj.add(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(sj);
        } else {
            try (FileOutputStream out = new FileOutputStream(argsName.get("out"))) {
                out.write(sj.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void isValid(ArgsName argsName) {
        File path = Paths.get(argsName.get("path")).toFile();
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        if (!path.exists() && !path.isFile()) {
            throw new IllegalArgumentException(String.format("%s - does not exist or is not a directory", path));
        }
        if (!(";".equals(delimiter)
                || ",".equals(delimiter))) {
            throw new IllegalArgumentException(String.format("%s - is not correct CSV format", delimiter));
        }
        if (!"stdout".equals(out)
                && !out.endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("%s - is not a CSV file", out));
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
