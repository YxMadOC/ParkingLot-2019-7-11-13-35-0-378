package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuperSmartParkingBoyTest {

    @Test
    void should_park_cars_into_the_parking_lot_contains_more_empty_rate_positions() {
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(50);
        Car car = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot, parkingLot1, parkingLot2);

        for (int i = 0; i < 45; i++) {
            parkingLot2.park(new Car());
        }
        for (int i = 0; i < 2; i++) {
            parkingLot1.park(new Car());
        }
        for (int i = 0; i < 5; i++) {
            parkingLot.park(new Car());
        }
        superSmartParkingBoy.park(car);

        assertFalse(parkingLot2.containsCar(car));
        assertFalse(parkingLot1.containsCar(car));
        assertTrue(parkingLot.containsCar(car));
    }

}