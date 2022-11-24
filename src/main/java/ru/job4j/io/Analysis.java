package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target, true)))) {
            boolean condition = false;
            String s;
            while ((s = in.readLine()) != null) {
                if (!condition && (s.contains("400") || s.contains("500"))) {
                    condition = true;
                    out.print(s.substring(4) + ";");
                } else if (condition && (s.contains("200") || s.contains("300"))) {
                    condition = false;
                    out.println(s.substring(4) + ";");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analysis().unavailable("server data 2.txt", "result.txt");
    }
}
