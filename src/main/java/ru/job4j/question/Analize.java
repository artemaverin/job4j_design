package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int del = 0;
        Info res = new Info(add, change, del);
        Map<Integer, String> map = new HashMap<>();
        for (User user : current) {
            map.put(user.getId(), user.getName());
        }

        for (User user: previous) {
            if (map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                change++;
                res.setChanged(change);
            }
        }

        for (User cur : current) {
            if (!previous.contains(cur)) {
                add++;
                res.setAdded(add);
            }
        }

        for (User prev : previous) {
            if (!current.contains(prev)) {
                del++;
                res.setDeleted(del);
            }
        }
        return res;
    }
}
