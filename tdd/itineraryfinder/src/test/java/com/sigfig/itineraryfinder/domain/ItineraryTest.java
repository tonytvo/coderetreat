package com.sigfig.itineraryfinder.domain;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static com.sigfig.itineraryfinder.domain.Airport.IAD;
import static com.sigfig.itineraryfinder.domain.Airport.OFK;
import static com.sigfig.itineraryfinder.domain.FlightBuilder.APRIL_1ST_2017_13;
import static com.sigfig.itineraryfinder.domain.FlightBuilder.OFK_IAD_2017_APRIL_1ST_10_TO_13;
import static com.sigfig.itineraryfinder.domain.FlightBuilder.SFO_OFK_2017_APRIL_1ST_5_TO_8;

public class ItineraryTest {

    @Test
    public void givenThereIsMoreThan20MinutesGapBetweenLayOverTheItineraryShouldBeValid() throws Exception {
        Itinerary itinerary = Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8, OFK_IAD_2017_APRIL_1ST_10_TO_13);
        Assert.assertEquals("Itinerary should be valid as there are more than 20 minutes between layover",
                true, itinerary.isValid());
    }

    @Test
    public void givenThereIsLessThan20MinutesGapBetweenLayOverTheItineraryShouldBeInValid() throws Exception {
        Itinerary itinerary = Itinerary.of(SFO_OFK_2017_APRIL_1ST_5_TO_8,
                new Flight("111", OFK, IAD, LocalDateTime.of(2017, Month.APRIL, 1, 8, 3), APRIL_1ST_2017_13));
        Assert.assertEquals("Itinerary should be invalid as there is less than 20 minutes between layover",
                false, itinerary.isValid());
    }
}