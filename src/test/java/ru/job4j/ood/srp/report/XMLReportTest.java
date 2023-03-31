package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    public void whenXMLReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new DateTimeParser<Calendar>() {
            private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            @Override
            public String parse(Calendar calendar) {
                return DATE_FORMAT.format(calendar.getTime());
            }
        };
        store.add(worker);
        XMLReport report = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                        .append("<employee>").append(System.lineSeparator()).append("    ")
                        .append("<fired>").append(parser.parse(worker.getHired())).append("</fired>").append("\n").append("    ")
                        .append("<hired>").append(parser.parse(worker.getFired())).append("</hired>").append("\n").append("    ")
                        .append("<name>").append(worker.getName()).append("</name>").append("\n").append("    ")
                        .append("<salary>").append(worker.getSalary()).append("</salary>").append("\n")
                        .append("</employee>").append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualToNormalizingNewlines(expect.toString());
    }

}