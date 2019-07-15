package com.thoughtworks.tdd;

import java.util.Arrays;

public class ParkingBoy extends Parker {

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public Ticket park(Car car) {
        if (parkingLots == null) {
            System.err.print("Parking boy has no parking lot.");
            return null;
        }
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsCar(car)) {
                return null;
            }
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        System.err.print("Not enough position.");
        return null;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.err.print("Please provide your parking ticket.");
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        System.err.print("Unrecognized parking ticket.");
        return null;
    }

}
