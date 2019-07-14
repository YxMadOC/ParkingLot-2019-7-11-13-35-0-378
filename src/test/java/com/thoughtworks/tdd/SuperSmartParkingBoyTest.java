package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuperSmartParkingBoyTest {

    @Test
    void should_park_cars_into_the_parking_lot_contains_more_empty_rate_positions() {
        // given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(100);
        Car car = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        // when
        superSmartParkingBoy.addParkingLot(parkingLot);
        superSmartParkingBoy.addParkingLot(parkingLot1);
        superSmartParkingBoy.addParkingLot(parkingLot2);
        for (int i = 0; i < 90; i++) {
            parkingLot2.park(new Car());
        }
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        for (int i = 0; i < 11; i++) {
            parkingLot.park(new Car());
        }
        superSmartParkingBoy.park(car);
        // then
        assertFalse(parkingLot2.containsCar(car));
        assertFalse(parkingLot1.containsCar(car));
        assertTrue(parkingLot.containsCar(car));
    }

}