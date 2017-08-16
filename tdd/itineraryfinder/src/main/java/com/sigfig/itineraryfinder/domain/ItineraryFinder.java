package com.sigfig.itineraryfinder.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class ItineraryFinder {
    public Itinerary findItinerary(List<Flight> scheduledFlights, Airport sourceAirport, Airport destinationAirport) {
        if (scheduledFlights.size() == 0)
            return new Itinerary(Collections.emptyList());

        AirportGraph graph = AirportGraph.from(scheduledFlights);
        List<List<Airport>> allPaths = graph.getAllPaths(sourceAirport, destinationAirport);
        if (allPaths.isEmpty()) {
            return new Itinerary(Collections.emptyList());
        }
        return new Itinerary(getFlightsMatchPath(allPaths.get(0), scheduledFlights));
    }

    protected List<Flight> getFlightsMatchPath(List<Airport> path, List<Flight> scheduledFlights) {
        Airport firstAirport = path.remove(0);
        Iterator<Airport> airportIterator = path.iterator();
        List<Flight> resultFlights = new ArrayList<>();
        while(airportIterator.hasNext()) {
            Airport secondAirport = airportIterator.next();
            Flight matchingFlight = getFirstFlightWithSourceAndDestinationAirport(scheduledFlights, firstAirport, secondAirport);
            resultFlights.add(matchingFlight);
            firstAirport = secondAirport;
        }
        return resultFlights;
    }

    private Flight getFirstFlightWithSourceAndDestinationAirport(List<Flight> scheduledFlights, Airport firstAirport, Airport secondAirport) {
        return scheduledFlights.stream()
                        .filter( flight -> flight.getDestinationAirport() == secondAirport && flight.getSourceAirport() == firstAirport)
                        .collect(Collectors.toList()).get(0);
    }
}
