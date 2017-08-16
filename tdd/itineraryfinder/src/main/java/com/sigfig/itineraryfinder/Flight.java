package com.sigfig.itineraryfinder;

public class Flight {
    Airport sourceAirport;
    Airport destinationAirport;

    public Flight(Airport sourceAirport, Airport destinationAirport) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
    }
}
