package com.sigfig.itineraryfinder.infrastructure;

import com.sigfig.itineraryfinder.domain.Airport;
import com.sigfig.itineraryfinder.domain.Flight;

import java.time.LocalDateTime;

public class FlightDTO {
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Flight toFlight() {
        return new Flight(flightNumber, Airport.valueOf(sourceAirport), Airport.valueOf(destinationAirport),
                LocalDateTime.parse(departureTime.replaceAll("Z$", "")),
                LocalDateTime.parse(arrivalTime.replaceAll("Z$", "")));
    }
    private String flightNumber;
    private String sourceAirport;
    private String destinationAirport;
    private String departureTime;
    private String arrivalTime;
}
