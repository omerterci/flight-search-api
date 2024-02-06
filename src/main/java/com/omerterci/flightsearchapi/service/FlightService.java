package com.omerterci.flightsearchapi.service;

import com.omerterci.flightsearchapi.model.Flight;
import com.omerterci.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId, LocalDate departureDate, LocalDate returnDate) {
        LocalDateTime startOfDepartureDay = departureDate.atStartOfDay();
        LocalDateTime endOfDepartureDay = departureDate.plusDays(1).atStartOfDay();

        List<Flight> flights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeBetween(
                departureAirportId, arrivalAirportId, startOfDepartureDay, endOfDepartureDay);

        if (returnDate != null) {
            LocalDateTime startOfReturnDay = returnDate.atStartOfDay();
            LocalDateTime endOfReturnDay = returnDate.plusDays(1).atStartOfDay();
            List<Flight> returnFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeBetween(
                    arrivalAirportId, departureAirportId, startOfReturnDay, endOfReturnDay);
            flights.addAll(returnFlights);
        }

        return flights;
    }
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

}
