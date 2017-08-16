package com.sigfig.itineraryfinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItineraryFinderTest {

    private ItineraryFinder itineraryFinder;

    @Before
    public void setUp() throws Exception {
        itineraryFinder = new ItineraryFinder();
    }

    @Test
    public void shouldAcceptEmptyScheduledFlights() throws Exception {
        List<Flight> scheduledFlights = Collections.emptyList();
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, Airport.SFO, Airport.IAD);
        Assert.assertEquals("there should be no flights", 0, itinerary.getNumFlights());
    }

    @Test
    public void shouldReturnItineraryWithSingleFlight() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(Airport.SFO, Airport.IAD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, Airport.SFO, Airport.IAD);
        Assert.assertEquals("there should be no flights", 1, itinerary.getNumFlights());
        assertSameDepartureDestinationAirport(Airport.SFO, Airport.IAD, itinerary);
    }

    @Test
    public void shouldReturnItineraryWithConnectingFlight() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(Airport.SFO, Airport.OFK));
        scheduledFlights.add(new Flight(Airport.OFK, Airport.IAD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, Airport.SFO, Airport.IAD);
        Assert.assertEquals("there should be no flights", 2, itinerary.getNumFlights());
        assertSameDepartureDestinationAirport(Airport.SFO, Airport.IAD, itinerary);
    }

    @Test
    public void shouldNotReturnItineraryGivenSourceDestinationDoesNotMatch() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(Airport.OFK, Airport.IAD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, Airport.SFO, Airport.IAD);
        Assert.assertEquals("there should be no flights", 0, itinerary.getNumFlights());
    }

    private void assertSameDepartureDestinationAirport(Airport expectedDepartureAirport, Airport expectedDestinationAirport, Itinerary itinerary) {
        Assert.assertEquals("should start at SFO airport", expectedDepartureAirport, itinerary.getFirstDepartureAirport());
        Assert.assertEquals("should end at IAD airport", expectedDestinationAirport, itinerary.getFinalDestinationAirport());
    }
}