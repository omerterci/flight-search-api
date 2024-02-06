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
    private AirportService airportService;

    @Test
    public void testSaveAirport() {
        Airport airport = new Airport("TestCity");
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport savedAirport = airportService.saveAirport(airport);

        verify(airportRepository).save(airport);
        assertThat(savedAirport.getCity()).isEqualTo("TestCity");
    }
}