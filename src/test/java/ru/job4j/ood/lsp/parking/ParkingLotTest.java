package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParkingLotTest {
    Auto car = new Car("Tesla", 4556);
    Auto car1 = new Car("Audi", 2468);
    Auto truck = new Truck("Kamaz", 6556, 1.5);
    Auto truck1 = new Truck("Western Star", 2222, 1.5);
    Auto car2 = new Car("Lada", 8492);
    List<Auto> autos = new ArrayList<>();

    @Test
    public void whenCarAdd() {
        ParkingLot parkingLot = new ParkingLot(2, 1);
        autos.add(car);
        autos.add(car1);
        parkingLot.park(autos);
        assertThat(parkingLot.getAutosPark().size()).isEqualTo(2);
    }

    @Test
    public void whenTruckAdd() {
        ParkingLot parkingLot = new ParkingLot(1, 1);
        autos.add(truck);
        autos.add(truck1);
        parkingLot.park(autos);
        assertThat(parkingLot.getAutosPark().size()).isEqualTo(1);
    }

    @Test
    public void whenTruckAdd2() {
        ParkingLot parkingLot = new ParkingLot(2, 1);
        autos.add(truck);
        autos.add(truck1);
        parkingLot.park(autos);
        assertThat(parkingLot.getAutosPark().size()).isEqualTo(2);
    }

    @Test
    public void whenTruckAndCar() {
        ParkingLot parkingLot = new ParkingLot(3, 1);
        autos.add(car);
        autos.add(car1);
        autos.add(truck);
        autos.add(truck1);
        autos.add(car2);
        parkingLot.park(autos);
        assertThat(parkingLot.getAutosPark().containsAll(List.of(car,car1,truck, car2))).isTrue();
    }

    @Test
    public void whenTruckAndCar2() {
        ParkingLot parkingLot = new ParkingLot(4, 1);
        autos.add(car);
        autos.add(car1);
        autos.add(truck);
        autos.add(truck1);
        autos.add(car2);
        parkingLot.park(autos);
        assertThat(parkingLot.getAutosPark().containsAll(List.of(car,car1,truck, truck1))).isTrue();
    }

    @Test
    public void whenParkingLotException() {
        assertThatThrownBy(() -> new ParkingLot(0, 1)).isInstanceOf(IllegalArgumentException.class);
    }

}