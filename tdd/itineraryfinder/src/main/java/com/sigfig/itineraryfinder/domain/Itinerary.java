package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Itinerary {

    public static final int ALLOWABLE_GAP_BETWEEN_LAYOVER = 20;

    /**
     * @return valid if there's enough 20 minutes gap between layover
     */
    public boolean isValid() {
        Iterator<Flight> flightsIterator = flights.iterator();
        LocalDateTime arrivalTime = flightsIterator.next().getArrivalTime();
        while(flightsIterator.hasNext()) {
            Flight nextFlight = flightsIterator.next();
            LocalDateTime nextFlightDepartureTime = nextFlight.getDepartureTime();
            if (!isThereEnoughGapBetweenLayover(arrivalTime, nextFlightDepartureTime))
                return false;
            arrivalTime = nextFlight.getArrivalTime();
        }
        return true;
    }

    public String printItinerary() {
        return String.join("\n", flights.stream().map( flight -> flight.flightInfo() ).collect(Collectors.toList()));
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

    private boolean isThereEnoughGapBetweenLayover(LocalDateTime arrivalTime, LocalDateTime nextFlightDepartureTime) {
        return nextFlightDepartureTime.minusMinutes(ALLOWABLE_GAP_BETWEEN_LAYOVER).isAfter(arrivalTime);
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "flights=" + flights +
                '}';
    }
}
