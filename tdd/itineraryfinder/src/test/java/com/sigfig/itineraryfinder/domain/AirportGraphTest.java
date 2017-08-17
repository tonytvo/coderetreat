package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.sigfig.itineraryfinder.domain.Airport.*;

public class AirportGraphTest {
    @Test
    public void givenItineraryWith1ConnectingFlightShouldGenerateCorrectGraphWith3Vertices() throws Exception {
        List<Flight> scheduledFlights = FlightBuilder.setupStandardTripWith1ConnectingFlight();
        AirportGraph graph = AirportGraph.from(scheduledFlights);

        List<Airport> actualVertices = graph.getVertices();

        List<Airport> expectedVertices = Arrays.asList(SFO, IAD, OFK);
        assertListEquals(expectedVertices, actualVertices);
    }

    @Test
    public void givenItineraryWith1ConnectingFlightShouldGenerateCorrectGraphWith2Edges() throws Exception {
        List<Flight> scheduledFlights = FlightBuilder.setupStandardTripWith1ConnectingFlight();
        AirportGraph graph = AirportGraph.from(scheduledFlights);

        List<Pair<Airport, Airport>> actualEdges= graph.getEdges();

        assertListEquals(Arrays.asList(new Pair<>(SFO, OFK), new Pair<>(OFK, IAD)), actualEdges);
    }

    @Test
    public void shouldReturnSinglePathSFOToOFK() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(SFO, OFK);
        AirportGraph graph = new AirportGraph(vertices);
        graph.addEdge(SFO, OFK);

        List<List<Airport>> actualPaths = graph.getAllPaths(SFO, OFK);

        List<List<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(SFO, OFK));
        assertListEquals(expectedPaths, actualPaths);
    }

    @Test
    public void shouldReturn2PathsForAirportSFOToIAD() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(SFO, OFK, IAD);
        AirportGraph graph = new AirportGraph(vertices);
        graph.addEdge(SFO, IAD);
        graph.addEdge(SFO, OFK);
        graph.addEdge(OFK, IAD);

        List<List<Airport>> actualPaths = graph.getAllPaths(SFO, IAD);

        List<List<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(SFO, IAD),
                Lists.newArrayList(SFO, OFK, IAD));
        assertListEquals(expectedPaths, actualPaths);
    }

    @Test
    public void shouldReturn3PathsForAirportSFOToIAD() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(SFO, OFK, IAD, AUD);
        AirportGraph graph = new AirportGraph(vertices);
        graph.addEdge(SFO, IAD);
        graph.addEdge(SFO, OFK);
        graph.addEdge(OFK, IAD);
        graph.addEdge(SFO, AUD);
        graph.addEdge(AUD, IAD);

        List<List<Airport>> actualPaths = graph.getAllPaths(SFO, IAD);

        List<List<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(SFO, IAD),
                Lists.newArrayList(SFO, OFK, IAD),
                Lists.newArrayList(SFO, AUD, IAD));
        assertListEquals(expectedPaths, actualPaths);
    }

    @Test
    public void shouldReturn3PathsForAirportSFOToIADWithoutDuplicatingVisitedAirport() throws Exception {
        Set<Airport> vertices = Sets.newHashSet(SFO, OFK, IAD, AUD);
        AirportGraph graph = new AirportGraph(vertices);
        graph.addEdge(SFO, OFK);
        graph.addEdge(SFO, AUD);
        graph.addEdge(OFK, SFO);
        graph.addEdge(OFK, AUD);
        graph.addEdge(OFK, IAD);
        graph.addEdge(AUD, IAD);

        List<List<Airport>> actualPaths = graph.getAllPaths(SFO, IAD);

        List<List<Airport>> expectedPaths = Arrays.asList(Lists.newArrayList(SFO, AUD, IAD),
                Lists.newArrayList(SFO, OFK, IAD),
                Lists.newArrayList(SFO, OFK, AUD, IAD));
        assertListEquals(expectedPaths, actualPaths);
    }

    private void assertListEquals(List expectedVertices, List actualVertices) {
        Assert.assertTrue(expectedVertices.containsAll(actualVertices));
        Assert.assertTrue(actualVertices.containsAll(expectedVertices));
    }
}