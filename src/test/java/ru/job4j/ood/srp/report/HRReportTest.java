package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HRReportTest {

    @Test
    public void whenHRReportGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        HRReport report = new HRReport(store);
        Employee first = new Employee("Ivan", now, now, 350);
        Employee second = new Employee("Petr", now, now, 100);
        Employee third = new Employee("Anna", now, now, 450);
        store.add(first);
        store.add(second);
        store.add(third);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(third.getName())
                .append(" ")
                .append(third.getSalary())
                .append(System.lineSeparator())
                .append(first.getName())
                .append(" ")
                .append(first.getSalary())
                .append(System.lineSeparator())
                .append(second.getName())
                .append(" ")
                .append(second.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expected.toString());

    }

}