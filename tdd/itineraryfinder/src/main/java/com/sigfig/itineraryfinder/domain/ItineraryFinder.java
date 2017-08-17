package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class ItineraryFinder {
    /**
     * This function would find the earliest possible itinerary for given sourceAirport, destination airport and list of
     * scheduled flights
     * Note that it will ignore any itineraries that don't have enough 20 minutes gap between layover
     * @param scheduledFlights list of scheduled flights info
     * @param sourceAirport departure airport
     * @param destinationAirport arrival airport
     * @return earliest possible itinerary (all flights including connecting flights that has the earliest arrival time)
     */
    public Itinerary findItinerary(List<Flight> scheduledFlights, Airport sourceAirport, Airport destinationAirport) {
        if (scheduledFlights.size() == 0)
            return new Itinerary(Collections.emptyList());

        AirportGraph graph = AirportGraph.from(scheduledFlights);
        List<List<Airport>> allPaths = graph.getAllPaths(sourceAirport, destinationAirport);
        if (allPaths.isEmpty()) {
            return new Itinerary(Collections.emptyList());
        }

        List<Itinerary> allItinerariesMatchedGivenPaths = getAllItinerariesMatchPaths(allPaths, scheduledFlights);

        List<Itinerary> validItineraries = filterValidItineraries(allItinerariesMatchedGivenPaths);

        return getItineraryWithEarliestArrivalTime(validItineraries);
    }

    /**
     * Given a list of scheduled flights and list of paths from one airport to another airport
     * It would find all possible itinerary with flight schedules match with the specified paths
     * i.e.: given path a->b, and flight schedules a->b, b->c, this function would return Itinerary of a->b
     * @param paths each path is a list of airport (each airport can be viewed as a node in a graph)
     * @param scheduledFlights available scheduled flights info such as departure airport, arrival time, etc...
     * @return list of all itineraries that match the given paths
     */
    protected List<Itinerary> getAllItinerariesMatchPaths(List<List<Airport>> paths, List<Flight> scheduledFlights) {
        List<Itinerary> allMatchFlights = Lists.newArrayList();
        paths.forEach( path -> allMatchFlights.addAll(getAllItinerariesMatchSinglePath(scheduledFlights, path)) );
        return allMatchFlights;

    }

    private List<Itinerary> filterValidItineraries(List<Itinerary> allFlightsMatched) {
        return allFlightsMatched.stream().filter( itinerary -> itinerary.isValid() ).collect(Collectors.toList());
    }

    private Itinerary getItineraryWithEarliestArrivalTime(List<Itinerary> validFlights) {
        return validFlights.stream().min(Comparator.comparing(itinerary -> itinerary.getFinalArrivalTime())).get();
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
