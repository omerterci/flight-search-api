package com.omerterci.flightsearchapi.scheduling;

import com.omerterci.flightsearchapi.model.Airport;
import com.omerterci.flightsearchapi.model.Flight;
import com.omerterci.flightsearchapi.service.AirportService;
import com.omerterci.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
//
@Component
public class FlightDataScheduler {

    private final FlightService flightService;
    private final AirportService airportService;

    @Autowired
    public FlightDataScheduler(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }


    @Scheduled(cron = "0 0 6 * * ?")
    public void fetchFlightData() {
        List<Airport> airports = airportService.findAllAirports();
        if (airports.isEmpty()) {
            String[] cities = {"Ankara", "İstanbul", "İzmir", "Antalya", "Moscow", "Paris", "London", "Atlanta","Denver", "Dubai"};
            for (String city : cities) {
                Airport airport = new Airport(city);
                airportService.saveAirport(airport);
            }
            airports = airportService.findAllAirports();
        }

        Random random = new Random();

        Airport departureAirport = airports.get(random.nextInt(airports.size()));
        Airport arrivalAirport = airports.get(random.nextInt(airports.size()));
        while (arrivalAirport.getId().equals(departureAirport.getId())) {
            arrivalAirport = airports.get(random.nextInt(airports.size()));
        }


        LocalDateTime departureDateTime = LocalDateTime.now().plusDays(random.nextInt(30));
        LocalDateTime returnDateTime = departureDateTime.plusDays(random.nextInt(10) + 1);
        Double price = 100.0 + (1000.0 - 100.0) * random.nextDouble();


        Flight flight = new Flight(departureAirport, arrivalAirport, departureDateTime, returnDateTime, price);
        flightService.saveFlight(flight);

        System.out.println("Mock Flight Added: " + flight.getId());
    }
}
