package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;

public class Flight {
    private Airport sourceAirport;
    private Airport destinationAirport;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String flightNumber;

    @Override
    public String toString() {
        return "Flight{" +
                "sourceAirport=" + sourceAirport +
                ", destinationAirport=" + destinationAirport +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (sourceAirport != flight.sourceAirport) return false;
        if (destinationAirport != flight.destinationAirport) return false;
        if (arrivalTime != null ? !arrivalTime.equals(flight.arrivalTime) : flight.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
            return false;
        return flightNumber != null ? flightNumber.equals(flight.flightNumber) : flight.flightNumber == null;
    }

    @Override
    public int hashCode() {
        int result = sourceAirport != null ? sourceAirport.hashCode() : 0;
        result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        return result;
    }

    public Flight(String flightNumber, Airport sourceAirport, Airport destinationAirport, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String flightInfo() {
        return String.format("Board flight %s to depart %s at %s and arrive at %s at %s",
                flightNumber, sourceAirport.toString(), departureTime,
                destinationAirport.toString(), arrivalTime);
    }
}
