package com.sigfig.itineraryfinder.domain;

class Flight {
    private Airport sourceAirport;
    private Airport destinationAirport;

    public Flight(Airport sourceAirport, Airport destinationAirport) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (sourceAirport != flight.sourceAirport) return false;
        return destinationAirport == flight.destinationAirport;
    }

    @Override
    public int hashCode() {
        int result = sourceAirport != null ? sourceAirport.hashCode() : 0;
        result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        return result;
    }
}
