package com.omerterci.flightsearchapi.service;

import com.omerterci.flightsearchapi.model.Airport;
import com.omerterci.flightsearchapi.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AirportServiceTest {

    @MockBean
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService; // AirportService nesnesini Spring'den otomatik olarak enjekte et

    @Test
    public void testSaveAirport() {
        // Given
        Airport airport = new Airport("TestCity");
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        // When
        Airport savedAirport = airportService.saveAirport(airport);

        // Then
        verify(airportRepository).save(airport);
        assertThat(savedAirport.getCity()).isEqualTo("TestCity");
    }
}