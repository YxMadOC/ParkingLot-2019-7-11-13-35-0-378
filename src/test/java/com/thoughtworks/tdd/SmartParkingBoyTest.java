package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SmartParkingBoyTest {

    @Test
    void should_park_cars_into_the_parking_lot_contains_more_empty_positions() {
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(30);
        Car car = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot, parkingLot1, parkingLot2);
        smartParkingBoy.park(car);

        Assertions.assertTrue(parkingLot2.containsCar(car));
        Assertions.assertFalse(parkingLot1.containsCar(car));
        Assertions.assertFalse(parkingLot.containsCar(car));
    }

}
