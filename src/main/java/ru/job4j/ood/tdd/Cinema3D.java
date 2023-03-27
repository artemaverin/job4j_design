package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {
    private final List<Session> sessions = new ArrayList<>();

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return getSessions().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (account == null || row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
        if (session == null) {
            throw new IllegalArgumentException();
        }
        sessions.add(session);
    }
}
