package com.omerterci.flightsearchapi.service;

import com.omerterci.flightsearchapi.model.Flight;
import com.omerterci.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        if(returnDateTime == null) {
            return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeBetween(
                    departureAirportId, arrivalAirportId, departureDateTime, departureDateTime.plusDays(1));
        } else {
            return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeBetween(
                    departureAirportId, arrivalAirportId, departureDateTime, returnDateTime);
        }
    }
}
