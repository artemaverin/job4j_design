package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name; Salary;").append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());
            }
        });
        for (Employee employee: list) {
            sb.append(employee.getName()).append(" ")
                    .append(employee.getSalary()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
