package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.sigfig.itineraryfinder.domain.Airport.IAD;
import static com.sigfig.itineraryfinder.domain.Airport.OFK;
import static com.sigfig.itineraryfinder.domain.Airport.SFO;

public class FlightsDataGenerator {
    public final static Flight SFO_OFK_2017_APRIL_1ST_8 = new Flight(SFO, OFK,
            LocalDateTime.of(2017, Month.APRIL, 1, 8, 0));

    public final static LocalDateTime APRIL_1ST_2017_13 = LocalDateTime.of(2017, Month.APRIL, 1, 13, 0);

    public final static Flight OFK_IAD_2017_APRIL_1ST_13 = new Flight(OFK, IAD, APRIL_1ST_2017_13);

    public final static Flight SFO_IAD_2017_APRIL_1ST_15 = new Flight(SFO, IAD,
            LocalDateTime.of(2017, Month.APRIL, 1, 15, 0));


    public static List<Flight> setupStandardTripWith1ConnectingFlight() {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_8);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_13);
        return scheduledFlights;
    }
}
