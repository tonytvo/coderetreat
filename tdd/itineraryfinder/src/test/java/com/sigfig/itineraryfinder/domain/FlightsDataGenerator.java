package com.sigfig.itineraryfinder.domain;

import java.util.ArrayList;
import java.util.List;

import static com.sigfig.itineraryfinder.domain.Airport.IAD;
import static com.sigfig.itineraryfinder.domain.Airport.OFK;
import static com.sigfig.itineraryfinder.domain.Airport.SFO;

public class FlightsDataGenerator {
    public static List<Flight> setupStandardScheduledFlightsWith1ConnectingFlight() {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(new Flight(SFO, OFK));
        scheduledFlights.add(new Flight(OFK, IAD));
        return scheduledFlights;
    }
}
