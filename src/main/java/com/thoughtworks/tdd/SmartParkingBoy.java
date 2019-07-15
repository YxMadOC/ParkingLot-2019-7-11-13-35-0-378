package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NotEnoughPositionException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SmartParkingBoy extends Parker {

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
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
            throw new NotEnoughPositionException();
        }
        return targetParkingLot.park(car);
    }

}
