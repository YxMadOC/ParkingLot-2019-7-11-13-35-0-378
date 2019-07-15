package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.MissingParkingTicketException;
import com.thoughtworks.tdd.exception.NotEnoughPositionException;
import com.thoughtworks.tdd.exception.UnrecognizedParkingTicketException;

import java.util.Arrays;

public class ParkingBoy extends Parker {

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsCar(car)) {
                return null;
            }
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            throw new MissingParkingTicketException();
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new UnrecognizedParkingTicketException();
    }

}
