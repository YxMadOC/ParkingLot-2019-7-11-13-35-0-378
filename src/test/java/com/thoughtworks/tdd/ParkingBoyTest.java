package com.thoughtworks.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParkingBoyTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    public void should_park_a_car_and_fetch_a_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        // when
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        // then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_fetch_car_with_correspond_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car1 = new Car();
        Car car2 = new Car();
        // when
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        // then
        Assertions.assertEquals(car1, parkingBoy.fetch(ticket1));
        Assertions.assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    public void should_return_err_with_wrong_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        // when
        parkingBoy.park(new Car());
        parkingBoy.fetch(new Ticket());
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", errContent.toString());
    }

    @Test
    public void should_return_err_with_no_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        // when
        parkingBoy.park(new Car());
        parkingBoy.fetch(null);
        // then
        Assertions.assertEquals("Please provide your parking ticket.", errContent.toString());
    }

    @Test
    public void should_return_err_with_used_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Ticket ticket = parkingBoy.park(new Car());
        // when
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", errContent.toString());
    }

    @Test
    void should_not_allow_park_a_parked_car() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        // when
        parkingBoy.park(car);
        Ticket ticket = parkingBoy.park(car);
        // then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_not_allow_park_null() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        // when
        Ticket ticket = parkingBoy.park(null);
        // then
        Assertions.assertNull(ticket);
    }

    @Test
    public void should_return_err_and_null_ticket_when_parking_lot_full() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2));
        // when
        for (int i = 0; i < 2; i++) {
            parkingBoy.park(new Car());
        }
        Ticket ticket = parkingBoy.park(new Car());
        // then
        Assertions.assertEquals("Not enough position.", errContent.toString());
        Assertions.assertNull(ticket);

    }

    @Test
    void should_manage_multiple_parking_lots_sequentially() {
        // given
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy();
        // when
        parkingBoy.addParkingLot(parkingLot);
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        // then
        Assertions.assertTrue(parkingLot.isFull());
        Assertions.assertTrue(parkingLot1.isEmpty());
    }

}
