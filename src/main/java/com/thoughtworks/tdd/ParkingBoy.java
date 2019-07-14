package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy implements Parkable {

    List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this();
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
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

    public void addParkingLot(ParkingLot parkingLot) {
        if (parkingLot == null) {
            System.err.print("Can not add a empty parking lot.");
            return;
        }
        parkingLots.add(parkingLot);
    }

    public boolean isParkingLotsFull() {
        if (parkingLots.size() == 0) {
            return true;
        }
        return parkingLots.stream().sorted(Comparator.comparingInt(ParkingLot::getRemains)).collect(Collectors.toList()).get(0).isFull();
    }

}
