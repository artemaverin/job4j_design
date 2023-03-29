package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final InMemoryCurrencyConverter converter;
    private final Currency source;
    private final Currency target;

    public AccountReport(Store store, DateTimeParser<Calendar> dateTimeParser, InMemoryCurrencyConverter converter, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee: store.findBy(filter)) {
            sb.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
