package ru.job4j.ood.isp;

import javax.naming.OperationNotSupportedException;

public interface Vehicle {
    void startEngine();
    void brake();
    void accelerate();
    void reverse();
    void autoParking();

}
