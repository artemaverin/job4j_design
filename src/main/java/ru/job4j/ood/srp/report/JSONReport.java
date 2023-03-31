package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final GsonBuilder builder;

    public JSONReport(Store store, GsonBuilder builder) {
        this.store = store;
        this.builder = builder;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var list = builder.create();
        return list.toJson(store.findBy(filter));
    }
}
