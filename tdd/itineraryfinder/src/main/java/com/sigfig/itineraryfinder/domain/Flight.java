package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;

class Flight {
    private Airport sourceAirport;
    private Airport destinationAirport;

    private LocalDateTime arrivalTime;

    public Flight(Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalTime) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "sourceAirport=" + sourceAirport +
                ", destinationAirport=" + destinationAirport +
                ", arrivalTime=" + arrivalTime +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (sourceAirport != flight.sourceAirport) return false;
        if (destinationAirport != flight.destinationAirport) return false;
        return arrivalTime != null ? arrivalTime.equals(flight.arrivalTime) : flight.arrivalTime == null;
    }

    @Override
    public int hashCode() {
        int result = sourceAirport != null ? sourceAirport.hashCode() : 0;
        result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }
}
