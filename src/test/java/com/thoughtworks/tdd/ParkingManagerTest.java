package com.thoughtworks.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParkingManagerTest {

    @Test
    void should_manage_multiple_parking_boy_to_park_and_fetch_a_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingBoy, smartParkingBoy, superSmartParkingBoy, new ParkingLot(1));
        Car car = new Car();
        // when
        Ticket ticket = parkingManager.park(car);
        Car fetchedCar = parkingManager.fetch(ticket);
        // then
        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_manage_parking_lot_by_parking_manager() {
        // given
        ParkingManager parkingManager = new ParkingManager();
        Car car = new Car();
        // when
        Ticket ticket = parkingManager.park(car);
        Car fetchedCar = parkingManager.fetch(ticket);
        // then
        Assertions.assertEquals(car, fetchedCar);
    }

//    @Test
//    void should_not_fetch_car_and_shows_error_when_give_a_wrong_ticket() {
//        // given
//        ParkingManager parkingManager = new ParkingManager();
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
//        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
//        Car car = new Car();
//        Ticket wrongTicket = new Ticket();
//        // when
//        parkingManager.manage(parkingBoy);
//        parkingManager.manage(smartParkingBoy);
//        parkingManager.manage(superSmartParkingBoy);
//        parkingManager.park(car);
//        Car fetchedCar = parkingManager.fetch(wrongTicket);
//        // then
//        Assertions.assertNull(fetchedCar);
//        Assertions.assertEquals("Unrecognized parking ticket.", errContent.toString());
//    }
//
//    @Test
//    void should_not_fetch_car_and_shows_error_when_give_no_ticket() {
//        // given
//        ParkingManager parkingManager = new ParkingManager();
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
//        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
//        Car car = new Car();
//        // when
//        parkingManager.manage(parkingBoy);
//        parkingManager.manage(smartParkingBoy);
//        parkingManager.manage(superSmartParkingBoy);
//        parkingManager.park(car);
//        Car fetchedCar = parkingManager.fetch(null);
//        // then
//        Assertions.assertNull(fetchedCar);
//        Assertions.assertEquals("Please provide your parking ticket.", errContent.toString());
//    }
//
//    @Test
//    void should_not_fetch_car_when_given_an_used_ticket() {
//        // given
//        ParkingManager parkingManager = new ParkingManager();
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
//        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
//        Car car = new Car();
//        // when
//        parkingManager.manage(parkingBoy);
//        parkingManager.manage(smartParkingBoy);
//        parkingManager.manage(superSmartParkingBoy);
//        Ticket ticket = parkingManager.park(car);
//        parkingManager.fetch(ticket);
//        Car fetchedCarSecondTime = parkingManager.fetch(ticket);
//        // then
//        Assertions.assertNull(fetchedCarSecondTime);
//        Assertions.assertEquals("Unrecognized parking ticket.", errContent.toString());
//    }
//
//    @Test
//    void should_not_park_car_when_parking_lot_full() {
//        // given
//        ParkingManager parkingManager = new ParkingManager();
//        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(2));
//        // when
//        parkingManager.manage(superSmartParkingBoy);
//        parkingManager.park(new Car());
//        parkingManager.park(new Car());
//        Ticket overflowCarTicket = parkingManager.park(new Car());
//        // then
//        Assertions.assertNull(overflowCarTicket);
//        Assertions.assertEquals("Not enough position.", errContent.toString());
//    }

}
