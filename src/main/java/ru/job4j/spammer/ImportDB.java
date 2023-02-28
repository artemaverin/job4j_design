package ru.job4j.spammer;

import ru.job4j.App;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private static void isValid(String[] arr) {
        if (arr.length != 2) {
            throw new IllegalArgumentException("количество аргументов не равно 2");
        }
        if (arr[0].equals("") || arr[1].equals("")) {
            throw new IllegalArgumentException("не заполнено имя или емэйл");
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        String str = null;
        String[] arr;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            while ((str = rd.readLine()) != null) {
                arr = str.split(";", 2);
                isValid(arr);
                users.add(new User(arr[0], arr[1].replace(";", "")));
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app2.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
