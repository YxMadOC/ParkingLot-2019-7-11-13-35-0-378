package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.MissingParkingTicketException;
import com.thoughtworks.tdd.exception.NotEnoughPositionException;
import com.thoughtworks.tdd.exception.UnrecognizedParkingTicketException;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingManager {

    private List<Parkable> parkers = new ArrayList<>();

    public ParkingManager(Parkable... parkables) {
        this.parkers.addAll(Arrays.asList(parkables));
    }

    public Ticket park(Car car) {
        for (Parkable parkable : parkers) {
            if (!parkable.isFull()) {
                return parkable.park(car);
            }
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            throw new MissingParkingTicketException();
        }
        for (Parkable parkable : parkers) {
            if (parkable.contains(ticket)) {
                return parkable.fetch(ticket);
            }
        }
        throw new UnrecognizedParkingTicketException();
    }

}
