package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.MissingParkingTicketException;
import com.thoughtworks.tdd.exception.NotEnoughPositionException;
import com.thoughtworks.tdd.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingManagerTest {

    @Test
    void should_park_and_fetch_a_car() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingBoy, smartParkingBoy, superSmartParkingBoy, new ParkingLot(1));
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);
        Car fetchedCar = parkingManager.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_not_fetch_car_and_shows_error_when_give_a_wrong_ticket() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingBoy, smartParkingBoy, superSmartParkingBoy, new ParkingLot(1));
        Car car = new Car();
        Ticket wrongTicket = new Ticket();

        parkingManager.park(car);

        Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingManager.fetch(wrongTicket));
    }

    @Test
    void should_not_fetch_car_and_shows_error_when_give_no_ticket() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingBoy, smartParkingBoy, superSmartParkingBoy, new ParkingLot(1));
        Car car = new Car();

        parkingManager.park(car);

        Assertions.assertThrows(MissingParkingTicketException.class, () -> parkingManager.fetch(null));
    }

    @Test
    void should_not_park_car_when_parking_lot_full() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingBoy);

        Assertions.assertThrows(NotEnoughPositionException.class, () -> {
            parkingManager.park(new Car());
            parkingManager.park(new Car());
        });
    }

}
