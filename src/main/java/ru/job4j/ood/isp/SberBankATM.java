package ru.job4j.ood.isp;

import javax.naming.OperationNotSupportedException;

public class SberBankATM implements ATM {
    @Override
    public void in() {
        System.out.println("внести денежные средства");
    }

    @Override
    public void out() {
        try {
            throw new OperationNotSupportedException();
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
