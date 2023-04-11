package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private double carSlotCount;
    private double truckSlotCount;
    private List<Auto> autosPark = new ArrayList<>();

    public ParkingLot(double carSlotCount, double truckSlotCount) {
        if (carSlotCount <= 0 || truckSlotCount <= 0) {
            throw new IllegalArgumentException();
        }
        this.carSlotCount = carSlotCount;
        this.truckSlotCount = truckSlotCount;
    }

    public List<Auto> getAutosPark() {
        return autosPark;
    }

    public void park(List<Auto> autos) {
        if (autos == null) {
            throw new IllegalArgumentException("список пуст");
        }
        for (Auto auto: autos) {
            if (auto.getSize() == 1 && auto.getSize() <= carSlotCount * auto.getSize()) {
                carSlotCount = carSlotCount - 1;
                autosPark.add(auto);
            }
            if (auto.getSize() > 1 && auto.getSize() <= truckSlotCount * auto.getSize()) {
                truckSlotCount = truckSlotCount - 1;
                autosPark.add(auto);
            } else if (auto.getSize() > 1 && auto.getSize() > truckSlotCount && auto.getSize() <= carSlotCount) {
                carSlotCount = carSlotCount - auto.getSize();
                autosPark.add(auto);
            }
        }
    }
}
