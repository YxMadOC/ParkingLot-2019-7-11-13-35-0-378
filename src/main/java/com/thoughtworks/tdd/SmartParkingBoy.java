package com.thoughtworks.tdd;

import java.util.Comparator;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy() {
        super();
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }


    @Override
    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.containsCar(car)) {
                return null;
            }
        }
        ParkingLot targetParkingLot = parkingLots.stream().sorted(Comparator.comparingInt(ParkingLot::getRemains)).collect(Collectors.toList()).get(0);
        if (targetParkingLot.isFull()) {
            System.err.println("Not enough position.");
            return null;
        }
        return targetParkingLot.park(car);

    }

}
