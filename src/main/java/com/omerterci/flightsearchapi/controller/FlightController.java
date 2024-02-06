package com.omerterci.flightsearchapi.controller;

import com.omerterci.flightsearchapi.model.Flight;
import com.omerterci.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.findAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<Flight>> searchFlightsByDate(
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        List<Flight> flights = flightService.findFlights(departureAirportId, arrivalAirportId, departureDate, returnDate);
        return ResponseEntity.ok(flights);
    }

}
