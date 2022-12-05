package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 7;
        short b = 1354;
        long c = 1000000000;
        double d = 23345897362.336533;
        boolean e = true;
        char f = 'A';
        float g = 1234546871.45678f;
        int k = 3654987;
        String h = "Fannie Mae";
        LOG.debug("\n Company name: {},\n number of employees : {}, \n has a bank license: {}, \n number of branches: {}, \n capital : {}, \n capitalization amount: {}, \n value amount: {}, \n rating: {}, \n number of clients: {}", h, b, e, a, c, d, g, f, k);
    }
}
