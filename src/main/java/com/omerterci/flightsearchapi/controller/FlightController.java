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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

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

    @GetMapping("/api/flights/searchByDate")
    @Operation(summary = "Search flights by date", description = "Searches for flights based on a specific departure and arrival airport ID and date range.")
    public ResponseEntity<List<Flight>> searchFlightsByDate(
            @RequestParam @Parameter(description = "Departure Airport ID") Long departureAirportId,
            @RequestParam @Parameter(description = "Arrival Airport ID") Long arrivalAirportId,
            @RequestParam @Parameter(description = "Departure Date", example = "2024-02-15", schema = @Schema(type = "string", format = "date", example = "2024-02-15")) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) @Parameter(description = "Return Date (optional)", example = "2024-02-24", schema = @Schema(type = "string", format = "date", example = "2024-02-24")) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        List<Flight> flights = flightService.findFlights(departureAirportId, arrivalAirportId, departureDate, returnDate);
        return ResponseEntity.ok(flights);
    }


}
