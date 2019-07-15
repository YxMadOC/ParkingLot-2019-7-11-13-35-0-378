package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.MissingParkingTicketException;
import com.thoughtworks.tdd.exception.NotEnoughPositionException;
import com.thoughtworks.tdd.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {

    @Test
    void should_park_a_car_and_fetch_a_car() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();

        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_fetch_car_with_correspond_ticket() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        Assertions.assertEquals(car1, parkingBoy.fetch(ticket1));
        Assertions.assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    void should_return_err_with_wrong_ticket() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        parkingBoy.park(new Car());

        Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(new Ticket()));
    }

    @Test
    void should_return_err_with_no_ticket() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        parkingBoy.park(new Car());

        Assertions.assertThrows(MissingParkingTicketException.class, () -> parkingBoy.fetch(null));
    }

    @Test
    void should_not_allow_park_a_parked_car() {

        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);
        Ticket ticket = parkingBoy.park(car);

        Assertions.assertNull(ticket);
    }

    @Test
    void should_not_allow_park_null() {

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(null);

        Assertions.assertNull(ticket);
    }

    @Test
    void should_return_err_and_null_ticket_when_parking_lot_full() {

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2));

        for (int i = 0; i < 2; i++) {
            parkingBoy.park(new Car());
        }

        Assertions.assertThrows(NotEnoughPositionException.class, () -> parkingBoy.park(new Car()));
    }

    @Test
    void should_manage_multiple_parking_lots_sequentially() {

        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        Assertions.assertTrue(parkingLot2.isFull());
    }

}
