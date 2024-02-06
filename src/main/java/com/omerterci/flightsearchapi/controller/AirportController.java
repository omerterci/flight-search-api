package com.omerterci.flightsearchapi.controller;

import com.omerterci.flightsearchapi.model.Airport;
import com.omerterci.flightsearchapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.findAllAirports();
    }
    @GetMapping("/search")
    public String search(Model model) {
        List<Airport> airports = airportService.findAllAirports();
        model.addAttribute("airports", airports);
        return "search";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return airportService.findAirportById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByCity")
    public ResponseEntity<List<Airport>> searchAirportsByCity(@RequestParam String city) {
        List<Airport> airports = airportService.findByCity(city);
        if(airports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airports);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.ok().build();
    }

}
