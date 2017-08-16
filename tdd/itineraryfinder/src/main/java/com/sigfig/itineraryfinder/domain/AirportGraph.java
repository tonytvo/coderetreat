package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class AirportGraph {
    private final Collection<Airport> vertices;
    private Map<Airport, List<Airport>> edges = Maps.newHashMap();

    public AirportGraph(Collection<Airport> vertices) {
        this.vertices = vertices;
    }

    public static AirportGraph from(List<Flight> scheduledFlights) {
        Set<Airport> vertices = new HashSet<>();
        scheduledFlights.forEach(flight -> vertices.addAll(Arrays.asList(flight.getSourceAirport(), flight.getDestinationAirport())));
        AirportGraph graph = new AirportGraph(vertices);

        scheduledFlights.forEach(flight -> graph.addEdge(flight.getSourceAirport(), flight.getDestinationAirport()));
        return graph;
    }

    public void addEdge(Airport sourceAirport, Airport destinationAirport) {
        if (this.edges.containsKey(sourceAirport)) {
            List<Airport> existingAdjacentVertices = this.edges.get(sourceAirport);
            if (!existingAdjacentVertices.contains(destinationAirport)) {
                existingAdjacentVertices.add(destinationAirport);
            }
            return;
        }

        this.edges.put(sourceAirport, Lists.newArrayList(destinationAirport));
    }

    public List<Airport> getVertices() {
        return new ArrayList<>(this.vertices);
    }

    public List<Pair<Airport, Airport>> getEdges() {
        return this.edges.entrySet()
                .stream()
                .flatMap( verticeToAdjacentVerticesMap ->
                        generateListOfEdges(verticeToAdjacentVerticesMap.getKey(), verticeToAdjacentVerticesMap.getValue()).stream() )
                .collect(Collectors.toList());
    }

    public List<List<Airport>> getAllPaths(Airport sourceAirport, Airport destinationAirport) {
        List<List<Airport>> allPaths = new ArrayList<>();
        Map<Airport, Boolean> visitedAirport = vertices.stream()
                .collect(Collectors.toMap(Function.identity(), vertice -> Boolean.FALSE));
        getAllPathsRecursive(sourceAirport, destinationAirport, visitedAirport, Lists.newArrayList(), allPaths);
        return allPaths;
    }

    private List<Pair<Airport, Airport>> generateListOfEdges(Airport vertice, List<Airport> adjacentVertices) {
        return adjacentVertices.stream()
                .map( adjacentVertice -> new Pair<>(vertice, adjacentVertice) )
                .collect(Collectors.toList());
    }

    private void getAllPathsRecursive(Airport sourceAirport, Airport destinationAirport,
                                      Map<Airport, Boolean> visitedAirport, ArrayList<Airport> currentPath,
                                      List<List<Airport>> allPaths) {
        currentPath.add(sourceAirport);
        visitedAirport.put(sourceAirport, Boolean.TRUE);

        if (sourceAirport == destinationAirport) {
            allPaths.add(Lists.newArrayList(currentPath));
        } else if (edges.get(sourceAirport) != null) {
            for (Airport adjacentAirportToSource: edges.get(sourceAirport)) {
                if (visitedAirport.get(adjacentAirportToSource) == Boolean.FALSE)
                    getAllPathsRecursive(adjacentAirportToSource, destinationAirport, visitedAirport, currentPath, allPaths);
            }
        }

        if (currentPath.size() > 0)
            currentPath.remove(currentPath.size() - 1);
        visitedAirport.put(sourceAirport, Boolean.FALSE);
    }
}
