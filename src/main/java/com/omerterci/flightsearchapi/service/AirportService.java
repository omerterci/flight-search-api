package com.omerterci.flightsearchapi.service;

import com.omerterci.flightsearchapi.model.Airport;
import com.omerterci.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> findAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public List<Airport> findByCity(String city) {
        return airportRepository.findByCity(city);
    }

}

