package com.sigfig.itineraryfinder.domain;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static com.sigfig.itineraryfinder.domain.Airport.*;
import static com.sigfig.itineraryfinder.domain.FlightBuilder.*;

public class ItineraryFinderTest {

    private static final LocalDateTime APRIL_2ND_2017_15 = LocalDateTime.of(2017, Month.APRIL, 2, 15, 0);
    private static final LocalDateTime APRIL_2ND_2017_13 = LocalDateTime.of(2017, Month.APRIL, 2, 13, 0);

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
        scheduledFlights.add(SFO_IAD_2017_APRIL_1ST_13_TO_15);
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals("there should be no flights", 1, itinerary.getNumFlights());
    }

    @Test
    public void shouldReturnItineraryWithExpectedDepartureAndArrivalAirport() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_IAD_2017_APRIL_1ST_13_TO_15);
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Test
    public void shouldReturnItineraryWith1ConnectingFlight() throws Exception {
        List<Flight> scheduledFlights = FlightBuilder.setupStandardTripWith1ConnectingFlight();
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals("there should be 2 flights", 2, itinerary.getNumFlights());
        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Test
    public void shouldNotReturnItineraryGivenSourceAirportDoesNotMatch() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, OFK, IAD);
        Assert.assertEquals("there should be no flights as source airport doesn't match",
                0, itinerary.getNumFlights());
    }

    @Test
    public void givenSinglePathOf2VerticesShouldReturn2FlightsMatched() throws Exception {
        List<List<Airport>> paths = Arrays.asList(Lists.newArrayList(SFO, IAD));

        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_IAD_2017_APRIL_1ST_13_TO_15);
        Flight sfoToIAD2017April2nd15 = new Flight("111", SFO, IAD, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(sfoToIAD2017April2nd15);
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);

        List<Itinerary> matchingItineraries = itineraryFinder.getAllItinerariesMatchPaths(paths, scheduledFlights);

        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_IAD_2017_APRIL_1ST_13_TO_15)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToIAD2017April2nd15)));
    }

    @Test
    public void givenSinglePathOf3VerticesShouldReturn4FlightsMatched() throws Exception {
        List<List<Airport>> paths = Arrays.asList(Lists.newArrayList(SFO, OFK, IAD));

        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        Flight sfoToOFK2017April2nd15 = new Flight("111", SFO, OFK, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(sfoToOFK2017April2nd15);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_10_TO_13);
        Flight ofkToIAD2017April2nd15 = new Flight("111", OFK, IAD, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(ofkToIAD2017April2nd15);

        List<Itinerary> matchingItineraries = itineraryFinder.getAllItinerariesMatchPaths(paths, scheduledFlights);

        System.out.println(matchingItineraries);
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8, OFK_IAD_2017_APRIL_1ST_10_TO_13)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8, ofkToIAD2017April2nd15)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToOFK2017April2nd15, OFK_IAD_2017_APRIL_1ST_10_TO_13)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToOFK2017April2nd15, ofkToIAD2017April2nd15)));
    }

    @Test
    public void given2PathsShouldReturn6FlightsMatched() throws Exception {
        List<List<Airport>> paths = Arrays.asList(Lists.newArrayList(SFO, OFK, IAD), Lists.newArrayList(SFO, IAD));

        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        Flight sfoToOFK2017April2nd15 = new Flight("111", SFO, OFK, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(sfoToOFK2017April2nd15);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_10_TO_13);
        Flight ofkToIAD2017April2nd15 = new Flight("111", OFK, IAD, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(ofkToIAD2017April2nd15);
        scheduledFlights.add(SFO_IAD_2017_APRIL_1ST_13_TO_15);
        Flight sfoToIAD2017April2nd15 = new Flight("111", SFO, IAD, APRIL_2ND_2017_13, APRIL_2ND_2017_15);
        scheduledFlights.add(sfoToIAD2017April2nd15);

        List<Itinerary> matchingItineraries = itineraryFinder.getAllItinerariesMatchPaths(paths, scheduledFlights);

        System.out.println(matchingItineraries);
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8, OFK_IAD_2017_APRIL_1ST_10_TO_13)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8, ofkToIAD2017April2nd15)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToOFK2017April2nd15, OFK_IAD_2017_APRIL_1ST_10_TO_13)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToOFK2017April2nd15, ofkToIAD2017April2nd15)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(SFO_IAD_2017_APRIL_1ST_13_TO_15)));
        Assert.assertTrue(matchingItineraries.contains(Itinerary.of(sfoToIAD2017April2nd15)));
    }

    @Test
    public void shouldReturnItineraryGivenThereOnly1MatchIn2Flights() {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_10_TO_13);
        scheduledFlights.add(new Flight("111", SFO, AUD, APRIL_2ND_2017_13, APRIL_2ND_2017_13));

        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);

        assertDepartureAndArrivalAirportEquals(SFO, IAD, itinerary);
    }

    @Test
    public void givenListOfFlightsShouldReturnTheFlightHasTheEarliestArrivalDate() throws Exception {
        List<List<Flight>> flights = Lists.newArrayList(Arrays.asList(SFO_OFK_2017_APRIL_1ST_5_TO_8, OFK_IAD_2017_APRIL_1ST_10_TO_13),
                Arrays.asList(SFO_IAD_2017_APRIL_1ST_13_TO_15));
        List<Flight> earliestFlight = flights.stream().min(Comparator.comparing(flight -> flight.get(flight.size() - 1).getArrivalTime())).get();
        Assert.assertEquals(Arrays.asList(SFO_OFK_2017_APRIL_1ST_5_TO_8, OFK_IAD_2017_APRIL_1ST_10_TO_13), earliestFlight);
    }

    @Test
    public void givenMultipleFlightsShouldReturnTheEarliestPossibleFlight() throws Exception {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_IAD_2017_APRIL_1ST_13_TO_15);
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_10_TO_13);
        scheduledFlights.add(new Flight("111", OFK, IAD, LocalDateTime.parse("2017-04-01T08:11:00"), APRIL_1ST_2017_10));
        Itinerary itinerary = itineraryFinder.findItinerary(scheduledFlights, SFO, IAD);
        Assert.assertEquals(APRIL_1ST_2017_13, itinerary.getFinalArrivalTime());
    }

    private void assertDepartureAndArrivalAirportEquals(Airport expectedDepartureAirport, Airport expectedArrivalAirport, Itinerary itinerary) {
        Assert.assertEquals("First Departure Airport should be SFO", expectedDepartureAirport, itinerary.getFirstDepartureAirport());
        Assert.assertEquals("Final Destination Airport should be IAD", expectedArrivalAirport, itinerary.getFinalArrivalAirport());
    }
}