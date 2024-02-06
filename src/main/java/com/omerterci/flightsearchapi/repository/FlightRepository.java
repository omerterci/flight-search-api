package com.omerterci.flightsearchapi.repository;

import com.omerterci.flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeBetween(
            Long departureAirportId, Long arrivalAirportId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
