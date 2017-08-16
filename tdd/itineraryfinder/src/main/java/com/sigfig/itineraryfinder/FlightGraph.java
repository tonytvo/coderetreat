package com.sigfig.itineraryfinder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FlightGraph {
    List<Airport> vertices = Lists.newArrayList();
    Map<Airport, List<Airport>> edges = Maps.newHashMap();

    public FlightGraph(Set<Airport> vertices) {
        this.vertices = new ArrayList<>(vertices);
    }

    public void addEdge(Airport sourceAirport, Airport destAirport) {
        if (this.edges.containsKey(sourceAirport)) {
            List<Airport> existingAdjacentVertices = this.edges.get(sourceAirport);
            if (!existingAdjacentVertices.contains(destAirport))
                existingAdjacentVertices.add(destAirport);
            return;
        }

        this.edges.put(sourceAirport, Lists.newArrayList(destAirport));
    }

    public static FlightGraph fromScheduledFlights(List<Flight> scheduledFlights, Airport sourceAirport, Airport destAirport) {
        Set<Airport> vertices = new HashSet<>();
        scheduledFlights.forEach(flight -> vertices.addAll(Arrays.asList(flight.sourceAirport, flight.destinationAirport)));
        vertices.addAll(Arrays.asList(sourceAirport, destAirport));

        FlightGraph flightGraph = new FlightGraph(vertices);
        scheduledFlights.forEach(flight -> flightGraph.addEdge(flight.sourceAirport, flight.destinationAirport) );
        return flightGraph;
    }

    public List<Airport> getVertices() {

        return this.vertices;
    }

    public List<Pair<Airport, Airport>> getEdges() {
        return this.edges.entrySet()
                .stream()
                .flatMap( verticeToAdjacentVerticesMap ->
                        generateListOfPairs(verticeToAdjacentVerticesMap.getKey(), verticeToAdjacentVerticesMap.getValue()).stream() )
                .collect(Collectors.toList());
    }

    private List<Pair<Airport, Airport>> generateListOfPairs(Airport vertice, List<Airport> adjacentVertices) {
        return adjacentVertices
                .stream()
                .map ( adjacentVertice -> new Pair<>(vertice, adjacentVertice) )
                .collect(Collectors.toList());
    }

    public List<List<Airport>> getAllPaths(Airport sourceAirport, Airport destinationAirport) {
        List<List<Airport>> allPaths = new ArrayList<>();
        Map<Airport, Boolean> visited = vertices.stream()
                .collect(Collectors.toMap(Function.identity(), vertice -> Boolean.FALSE) );
        getAllPathsResursive(sourceAirport, destinationAirport, visited, Lists.newArrayList(), allPaths);
        return allPaths;
    }

    public void getAllPathsResursive(Airport sourceAirport, Airport destinationAirport, Map<Airport, Boolean> visited,
                                     List<Airport> currentPath, List<List<Airport>> allPaths) {
        currentPath.add(sourceAirport);
        visited.put(sourceAirport, Boolean.TRUE);
        if (sourceAirport == destinationAirport) {
            allPaths.add(Lists.newArrayList(currentPath));
        } else if (edges.get(sourceAirport) != null) {
            for (Airport adjentAirportToSource : edges.get(sourceAirport)) {
                if (visited.get(adjentAirportToSource) == Boolean.FALSE)
                    getAllPathsResursive(adjentAirportToSource, destinationAirport, visited, currentPath, allPaths);
            }
        }

        if (currentPath.size() > 0)
            currentPath.remove(currentPath.size() - 1);
        visited.put(sourceAirport, Boolean.FALSE);

    }
}
