package com.sigfig.itineraryfinder.domain;

import java.util.ArrayList;
import java.util.List;

class Itinerary {
    private List<Flight> flights = new ArrayList<>();

    public Itinerary(List<Flight> flights) {
        this.flights = flights;
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
}
