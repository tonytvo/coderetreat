package com.sigfig.itineraryfinder;

import java.util.List;

public class Itinerary {
    private List<Flight> flights;

    public Itinerary(List<Flight> flights) {
        this.flights = flights;
    }

    public int getNumFlights() {
        return flights.size();
    }

    public Airport getFirstDepartureAirport() {
        return flights.get(0).sourceAirport;
    }

    public Airport getFinalDestinationAirport() {
        return flights.get(flights.size() - 1).destinationAirport;
    }
}
