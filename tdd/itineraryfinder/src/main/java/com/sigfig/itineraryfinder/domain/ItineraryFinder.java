package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;

import java.util.*;
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

        List<Itinerary> allFlightsMatched = getAllItinerariesMatchPaths(allPaths, scheduledFlights);
        Itinerary resultItinerary = allFlightsMatched.stream().min(Comparator.comparing(itinerary -> itinerary.getFinalArrivalTime())).get();
        return resultItinerary;
    }

    protected List<Itinerary> getAllItinerariesMatchPaths(List<List<Airport>> paths, List<Flight> scheduledFlights) {
        List<Itinerary> allMatchFlights = Lists.newArrayList();
        paths.forEach( path -> allMatchFlights.addAll(getAllItinerariesMatchSinglePath(scheduledFlights, path)) );
        return allMatchFlights;

    }

    private List<Itinerary> getAllItinerariesMatchSinglePath(List<Flight> scheduledFlights, List<Airport> path) {
        Iterator<Airport> pathIterator = path.iterator();
        Airport firstAirport = pathIterator.next();
        List<List<Flight>> allFlightsMatchEdgesFromPath = Lists.newArrayList();
        while(pathIterator.hasNext()) {
            Airport secondAirport = pathIterator.next();
            allFlightsMatchEdgesFromPath.add(getFlightsMatchSourceAndDestination(scheduledFlights, firstAirport, secondAirport));
            firstAirport = secondAirport;
        }

        List<List<Flight>> allFlights = Lists.cartesianProduct(allFlightsMatchEdgesFromPath);
        return allFlights.stream()
                .map(flight -> new Itinerary(flight)).collect(Collectors.toList());
    }

    private List<Flight> getFlightsMatchSourceAndDestination(List<Flight> scheduledFlights, Airport firstAirport, Airport secondAirport) {
        return scheduledFlights.stream()
                .filter( flight -> flight.getSourceAirport() == firstAirport && flight.getDestinationAirport() == secondAirport )
                .collect(Collectors.toList());
    }
}
