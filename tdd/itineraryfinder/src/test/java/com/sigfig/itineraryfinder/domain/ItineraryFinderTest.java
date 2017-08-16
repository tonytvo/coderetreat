package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static com.sigfig.itineraryfinder.domain.Airport.*;

public class ItineraryFinderTest {

    private ItineraryFinder itineraryFinder;

    @Before
    public void setUp() throws Exception {
        itineraryFinder = new ItineraryFinder();
    }

    @Test
    public void shouldAcceptEmptyScheduledFlight() throws Exception {
        List<Flight> scheduledFlights = Collections.emptyList();
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals("there should be no flights", 0, itinerary.getNumFlights());
    }

    @Test
    public void shouldReturnItineraryWithSingleFlight() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(SFO, IAD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals("there should be no flights", 1, itinerary.getNumFlights());
    }

    @Test
    public void shouldReturnItineraryWithExpectedDepartureAndArrivalAirport() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(SFO, IAD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Test
    public void shouldReturnItineraryWithConnectingFlight() throws Exception {
        List<Flight> scheduledFlights = FlightsDataGenerator.setupStandardScheduledFlightsWith1ConnectingFlight();
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals("there should be 2 flights", 2, itinerary.getNumFlights());
        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Test
    public void shouldNotReturnItineraryGivenSourceAirportDoesNotMatch() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(SFO, OFK));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, OFK, IAD);
        Assert.assertEquals("there should be no flights as source airport doesn't match",
                0, itinerary.getNumFlights());
    }

    @Test
    public void shouldGivenCorrectFlightsGivenThePath() throws Exception {
        List<Airport> path = Lists.newArrayList(SFO, OFK, IAD);
        List<Flight> standardFlightWith1ConnectingFlight = FlightsDataGenerator.setupStandardScheduledFlightsWith1ConnectingFlight();
        List<Flight> matchingFlights = itineraryFinder.getFlightsMatchPath(path, standardFlightWith1ConnectingFlight);
        Assert.assertEquals(Arrays.asList(new Flight(SFO, OFK), new Flight(OFK, IAD)), matchingFlights);
    }

    @Test
    public void shouldReturnItineraryGivenThereOnly1Match() {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(SFO, OFK));
        scheduledFlights.add(new Flight(OFK, IAD));
        scheduledFlights.add(new Flight(SFO, AUD));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Ignore
    @Test
    public void givenMultipleFlightsShouldReturnTheEarliestPossibleFlight() throws Exception {

    }

    private void assertDepartureAndArrivalAirportEquals(Airport expectedDepartureAirport, Airport expectedArrivalAirport, Itinerary itinerary) {
        Assert.assertEquals("First Departure Airport should be SFO", expectedDepartureAirport, itinerary.getFirstDepartureAirport());
        Assert.assertEquals("Final Destination Airport should be IAD", expectedArrivalAirport, itinerary.getFinalArrivalAirport());
    }
}