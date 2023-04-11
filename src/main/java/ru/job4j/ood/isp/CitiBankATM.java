package ru.job4j.ood.isp;

public class CitiBankATM implements ATM {

    @Override
    public void in() {
        System.out.println("внести денежные средства");
    }

    @Override
    public void out() {
        System.out.println("снять денежные средства");
    }
}
