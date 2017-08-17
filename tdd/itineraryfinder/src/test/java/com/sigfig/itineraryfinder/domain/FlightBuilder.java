package com.sigfig.itineraryfinder.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.sigfig.itineraryfinder.domain.Airport.IAD;
import static com.sigfig.itineraryfinder.domain.Airport.OAK;
import static com.sigfig.itineraryfinder.domain.Airport.SFO;

public class FlightBuilder {
    public static final LocalDateTime APRIL_1ST_2017_5 = LocalDateTime.of(2017, Month.APRIL, 1, 5, 0);
    public static final LocalDateTime APRIL_1ST_2017_8 = LocalDateTime.of(2017, Month.APRIL, 1, 8, 0);
    public final static LocalDateTime APRIL_1ST_2017_10 = LocalDateTime.of(2017, Month.APRIL, 1, 10, 0);
    public final static LocalDateTime APRIL_1ST_2017_13 = LocalDateTime.of(2017, Month.APRIL, 1, 13, 0);
    public final static LocalDateTime APRIL_1ST_2017_15 = LocalDateTime.of(2017, Month.APRIL, 1, 15, 0);

    public final static Flight SFO_OFK_2017_APRIL_1ST_5_TO_8 = new Flight("117", SFO, OAK, APRIL_1ST_2017_5, APRIL_1ST_2017_8);
    public final static Flight OFK_IAD_2017_APRIL_1ST_10_TO_13 = new Flight("451", OAK, IAD, APRIL_1ST_2017_10, APRIL_1ST_2017_13);
    public final static Flight SFO_IAD_2017_APRIL_1ST_13_TO_15 = new Flight("453", SFO, IAD, APRIL_1ST_2017_13, APRIL_1ST_2017_15);


    public static List<Flight> setupStandardTripWith1ConnectingFlight() {
        List<Flight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(SFO_OFK_2017_APRIL_1ST_5_TO_8);
        scheduledFlights.add(OFK_IAD_2017_APRIL_1ST_10_TO_13);
        return scheduledFlights;
    }
}
