package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Parkable{

    private final Map<Ticket, Car> cars = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        this.capacity = 10;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (isFull() || containsCar(car) || car == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        cars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return cars.remove(ticket);
    }

    public boolean isFull() {
        return cars.size() >= capacity;
    }

    @Override
    public boolean contains(Ticket ticket) {
        return cars.containsKey(ticket);
    }

    int getRemains() {
        return cars.size() - capacity;
    }

    public boolean containsCar(Car car) {
        return cars.containsValue(car);
    }

    boolean containsTicket(Ticket ticket) {
        return cars.containsKey(ticket);
    }

    public boolean isEmpty() {
        return cars.isEmpty();
    }

    int getCapacity() {
        return capacity;
    }
}
