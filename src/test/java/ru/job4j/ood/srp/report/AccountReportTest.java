package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class AccountReportTest {

    @Test
    public void whenAccountReportGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee first = new Employee("Ivan", now, now, 350);
        Employee second = new Employee("Petr", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(first);
        store.add(second);
        AccountReport accountReport = new AccountReport(store, parser, converter, Currency.USD, Currency.RUB);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(first.getName()).append(" ")
                .append(parser.parse(first.getHired())).append(" ")
                .append(parser.parse(first.getFired())).append(" ")
                .append(first.getSalary() * 65).append(System.lineSeparator())
                .append(second.getName()).append(" ")
                .append(parser.parse(second.getHired())).append(" ")
                .append(parser.parse(second.getFired())).append(" ")
                .append(second.getSalary() * 65).append(System.lineSeparator());
        assertThat(accountReport.generate(em -> true)).isEqualTo(expected.toString());
    }

}