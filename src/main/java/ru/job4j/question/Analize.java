package ru.job4j.question;

import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int del = 0;
        Info res = new Info(add, change, del);
        for (User cur : current) {
            for (User prev : previous) {
                if (prev.getId() == cur.getId() && !Objects.equals(prev.getName(), cur.getName())) {
                    change++;
                    res.setChanged(change);
                }
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
