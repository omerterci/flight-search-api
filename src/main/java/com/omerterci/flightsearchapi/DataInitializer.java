package com.omerterci.flightsearchapi;

import com.omerterci.flightsearchapi.model.Airport;
import com.omerterci.flightsearchapi.model.Flight;
import com.omerterci.flightsearchapi.service.AirportService;
import com.omerterci.flightsearchapi.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AirportService airportService, FlightService flightService) {
        return args -> {
            String[] cities = {"Ankara", "İstanbul", "İzmir", "Antalya", "Moscow", "Paris", "London", "Atlanta", "Denver", "Dubai"};
            for (String city : cities) {
                Airport airport = new Airport(city);
                airportService.saveAirport(airport);
            }

            List<Airport> airports = airportService.findAllAirports();
            if (airports.isEmpty()) {
                // Listede havaalanı yoksa, bu durumu ele al
                System.out.println("Havaalanı listesi boş. Mock uçuşlar eklenemiyor.");
                return; // Metodu erken sonlandır
            }

            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                Airport departureAirport = airports.get(random.nextInt(airports.size()));
                Airport arrivalAirport;
                do {
                    arrivalAirport = airports.get(random.nextInt(airports.size()));
                } while (arrivalAirport.equals(departureAirport));

                int hour = random.nextInt(24);

                LocalDateTime departureDateTime = LocalDateTime.now().plusDays(1)
                        .plusDays(random.nextInt(30))
                        .withHour(hour)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);

                LocalDateTime returnDateTime = departureDateTime
                        .plusDays(random.nextInt(10) + 1)
                        .withHour(hour)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);

                Double price = 100.0 + (1000.0 - 100.0) * random.nextDouble(); // random.nextInt(10) yerine daha gerçekçi bir fiyat hesaplaması için random.nextDouble() kullanıldı

                Flight flight = new Flight(departureAirport, arrivalAirport, departureDateTime, returnDateTime, price);
                flightService.saveFlight(flight);
            }
        };
    }
}
