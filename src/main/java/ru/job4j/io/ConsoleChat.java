package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answersList = readPhrases();
        Scanner scanner = new Scanner(System.in);
        String text, text2, answer;
        String continuePhrase = "Glad you back, mister";
        text = scanner.nextLine();
        while (!OUT.equals(text)) {
            log.add(text);
            if (STOP.equals(text)) {
                text2 = scanner.nextLine();
                while (!CONTINUE.equals(text2)) {
                    log.add(text2);
                    text2 = scanner.nextLine();
                }
                log.add(CONTINUE);
                System.out.println(continuePhrase);
                log.add(continuePhrase);
            } else {
                answer = answersList.get((int) (Math.random() * answersList.size()));
                System.out.println(answer);
                log.add(answer);
            }
            text = scanner.nextLine();
        }
        log.add(OUT);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(botAnswers))) {
            list = bf.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\console_chat\\Log.txt", "C:\\projects\\job4j_design\\console_chat\\BotAnswers.txt");
        cc.run();
    }
}
