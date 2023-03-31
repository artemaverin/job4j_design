package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
    @Test
    public void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        GsonBuilder builder = new GsonBuilder();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        JSONReport report = new JSONReport(store, builder);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker.getName()).append("\"")
                        .append(",\"hired\":{\"year\":").append(worker.getHired().getTime().getYear() + 1900)
                        .append(",\"month\":").append(worker.getHired().getTime().getMonth())
                        .append(",\"dayOfMonth\":").append(worker.getHired().getTime().getDate())
                        .append(",\"hourOfDay\":").append(worker.getHired().getTime().getHours())
                        .append(",\"minute\":").append(worker.getHired().getTime().getMinutes())
                        .append(",\"second\":").append(worker.getHired().getTime().getSeconds())
                        .append("},\"fired\":{\"year\":").append(worker.getFired().getTime().getYear() + 1900)
                        .append(",\"month\":").append(worker.getFired().getTime().getMonth())
                        .append(",\"dayOfMonth\":").append(worker.getFired().getTime().getDate())
                        .append(",\"hourOfDay\":").append(worker.getFired().getTime().getHours())
                        .append(",\"minute\":").append(worker.getFired().getTime().getMinutes())
                        .append(",\"second\":").append(worker.getFired().getTime().getSeconds())
                        .append("},\"salary\":").append(worker.getSalary()).append("}]");
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }

}