package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.MissingParkingTicketException;
import com.thoughtworks.tdd.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;

public abstract class Parker implements Parkable {

    protected List<ParkingLot> parkingLots = new ArrayList<>();

    public abstract Ticket park(Car car);

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

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }
}
