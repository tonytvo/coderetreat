package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Itinerary {
    @Override
    public String toString() {
        return "Itinerary{" +
                "flights=" + flights +
                '}';
    }

    private List<Flight> flights = new ArrayList<>();

    public Itinerary(List<Flight> flights) {
        this.flights = flights;
    }

    public static Itinerary of(Flight... flights) {
        return new Itinerary(Arrays.asList(flights));
    }

    public int getNumFlights() {
        return this.flights.size();
    }

    public Airport getFirstDepartureAirport() {
        return this.flights.get(0).getSourceAirport();
    }

    public Airport getFinalArrivalAirport() {
        return this.flights.get(this.flights.size() - 1).getDestinationAirport();
    }

    public LocalDateTime getFinalArrivalTime() {
        return this.flights.get(this.flights.size() - 1).getArrivalTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Itinerary itinerary = (Itinerary) o;

        return flights != null ? flights.equals(itinerary.flights) : itinerary.flights == null;
    }

    @Override
    public int hashCode() {
        return flights != null ? flights.hashCode() : 0;
    }
}
