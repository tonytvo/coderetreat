package com.sigfig.itineraryfinder.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigfig.itineraryfinder.domain.Airport;
import com.sigfig.itineraryfinder.domain.Flight;
import com.sigfig.itineraryfinder.domain.Itinerary;
import com.sigfig.itineraryfinder.domain.ItineraryFinder;
import com.sigfig.itineraryfinder.infrastructure.FlightDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ItineraryFinderApp {

    public static void main(String [] args) throws IOException {
        String scheduleFlights = getScheduledFlightJSON();
        System.out.println("Scheduled flights in json:\n" + scheduleFlights);

        List<Flight> flights = parseFlightsFromJson(scheduleFlights);
        ItineraryFinder itineraryFinder = new ItineraryFinder();
        Itinerary itinerary = itineraryFinder.findItinerary(flights, Airport.SFO, Airport.IAD);
        System.out.println("\n\nPossible earliest Itinerary:\n" + itinerary.printItinerary());
    }

    private static String getScheduledFlightJSON() {
        return "[\n" +
                    "    {\n" +
                    "        \"flightNumber\": 117,\n" +
                    "        \"sourceAirport\": \"SFO\",\n" +
                    "        \"destinationAirport\": \"OAK\",\n" +
                    "        \"departureTime\": \"2017-01-25T22:17:05Z\",\n" +
                    "        \"arrivalTime\": \"2017-01-25T22:21:00Z\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"flightNumber\": 451,\n" +
                    "        \"sourceAirport\": \"OAK\",\n" +
                    "        \"destinationAirport\": \"IAD\",\n" +
                    "        \"departureTime\": \"2017-01-26T04:01:00Z\",\n" +
                    "        \"arrivalTime\": \"2017-01-26T10:21:00Z\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"flightNumber\": 453,\n" +
                    "        \"sourceAirport\": \"SFO\",\n" +
                    "        \"destinationAirport\": \"IAD\",\n" +
                    "        \"departureTime\": \"2017-01-26T04:00:00Z\",\n" +
                    "        \"arrivalTime\": \"2017-01-26T10:24:00Z\"\n" +
                    "    }\n" +
                    "]";
    }

    private static List<Flight> parseFlightsFromJson(String scheduleFlights) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<FlightDTO> flightDTOs = mapper.readValue(scheduleFlights, new TypeReference<List<FlightDTO>>(){});
        return flightDTOs.stream().map( flightDTO -> flightDTO.toFlight() ).collect(Collectors.toList());
    }
}
