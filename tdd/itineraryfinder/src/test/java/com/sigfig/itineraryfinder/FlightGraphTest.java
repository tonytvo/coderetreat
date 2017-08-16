package com.sigfig.itineraryfinder;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FlightGraphTest {

    @Test
    public void shouldGenerateCorrectGraphWith3Vertices() throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(Airport.SFO, Airport.OFK));
        flights.add(new Flight(Airport.OFK, Airport.IAD));
        FlightGraph graph = FlightGraph.fromScheduledFlights(flights, Airport.SFO, Airport.IAD);
        List<Airport> actualVertices = graph.getVertices();
        List<Airport> expectedVertices = Arrays.asList(Airport.SFO, Airport.IAD, Airport.OFK);

        assertListsAreEquals(actualVertices, expectedVertices);
    }

    @Test
    public void shouldGenerateCorrectGraphWith2Edges() throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(Airport.SFO, Airport.OFK));
        flights.add(new Flight(Airport.OFK, Airport.IAD));
        FlightGraph graph = FlightGraph.fromScheduledFlights(flights, Airport.SFO, Airport.IAD);
        List<Pair<Airport, Airport>> actualEdges = graph.getEdges();

        List<Pair<Airport, Airport>> expectedEdges = Lists.newArrayList(new Pair<>(Airport.SFO, Airport.OFK),
                new Pair<>(Airport.OFK, Airport.IAD));
        assertListsAreEquals(expectedEdges, actualEdges);
    }

    @Test
    public void shouldReturnPathSFOToOFK() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(Airport.SFO, Airport.OFK);
        FlightGraph graph = new FlightGraph(vertices);
        graph.addEdge(Airport.SFO, Airport.OFK);
        List<List<Airport>> actualPaths = graph.getAllPaths(Airport.SFO, Airport.OFK);
        Assert.assertEquals(Arrays.asList(Lists.newArrayList(Airport.SFO, Airport.OFK)), actualPaths);
    }

    @Test
    public void shouldReturn2PathsSFOToIAD() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(Airport.SFO, Airport.OFK, Airport.IAD);
        FlightGraph graph = new FlightGraph(vertices);
        graph.addEdge(Airport.SFO, Airport.IAD);
        graph.addEdge(Airport.SFO, Airport.OFK);
        graph.addEdge(Airport.OFK, Airport.IAD);
        List<List<Airport>> actualPaths = graph.getAllPaths(Airport.SFO, Airport.IAD);
        List<ArrayList<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(Airport.SFO, Airport.IAD),
                Lists.newArrayList(Airport.SFO, Airport.OFK, Airport.IAD));
        System.out.println("expectedPaths = " + expectedPaths);
        System.out.println("actualPaths = " + actualPaths);
        assertListsAreEquals(expectedPaths, actualPaths);
    }

    @Test
    public void shouldReturn3PathsSFOToIAD() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(Airport.SFO, Airport.OFK, Airport.AUD, Airport.IAD);
        FlightGraph graph = new FlightGraph(vertices);
        graph.addEdge(Airport.SFO, Airport.IAD);
        graph.addEdge(Airport.SFO, Airport.OFK);
        graph.addEdge(Airport.OFK, Airport.IAD);
        graph.addEdge(Airport.SFO, Airport.AUD);
        graph.addEdge(Airport.AUD, Airport.IAD);

        List<List<Airport>> actualPaths = graph.getAllPaths(Airport.SFO, Airport.IAD);
        List<ArrayList<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(Airport.SFO, Airport.IAD),
                Lists.newArrayList(Airport.SFO, Airport.OFK, Airport.IAD),
                Lists.newArrayList(Airport.SFO, Airport.AUD, Airport.IAD));
        System.out.println("expectedPaths = " + expectedPaths);
        System.out.println("actualPaths = " + actualPaths);
        assertListsAreEquals(expectedPaths, actualPaths);
    }

    @Test
    public void shouldReturn3PathsWithoutDuplicatingVisitedNode() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(Airport.SFO, Airport.OFK, Airport.AUD, Airport.IAD);
        FlightGraph graph = new FlightGraph(vertices);
        graph.addEdge(Airport.SFO, Airport.OFK);
        graph.addEdge(Airport.SFO, Airport.AUD);
        graph.addEdge(Airport.OFK, Airport.SFO);
        graph.addEdge(Airport.OFK, Airport.AUD);
        graph.addEdge(Airport.OFK, Airport.IAD);
        graph.addEdge(Airport.AUD, Airport.IAD);

        List<List<Airport>> actualPaths = graph.getAllPaths(Airport.SFO, Airport.IAD);
        List<ArrayList<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(Airport.SFO, Airport.AUD, Airport.IAD),
                Lists.newArrayList(Airport.SFO, Airport.OFK, Airport.IAD),
                Lists.newArrayList(Airport.SFO, Airport.OFK, Airport.AUD, Airport.IAD));
        System.out.println("expectedPaths = " + expectedPaths);
        System.out.println("actualPaths = " + actualPaths);
        assertListsAreEquals(expectedPaths, actualPaths);
    }

    private void assertListsAreEquals(List actualVertices, List expectedVertices) {
        Assert.assertTrue(actualVertices.containsAll(expectedVertices));
        Assert.assertTrue(expectedVertices.containsAll(actualVertices));
    }
}