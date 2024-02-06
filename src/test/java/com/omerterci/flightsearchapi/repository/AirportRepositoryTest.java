package com.omerterci.flightsearchapi.repository;

import com.omerterci.flightsearchapi.model.Airport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    public void testFindByCity() {
        // Given
        Airport airport = new Airport("TestCity");
        airportRepository.save(airport);

        // When
        List<Airport> foundAirports = airportRepository.findByCity("TestCity");

        // Then
        assertThat(foundAirports).hasSize(1);
        assertThat(foundAirports.get(0).getCity()).isEqualTo("TestCity");
    }
}
