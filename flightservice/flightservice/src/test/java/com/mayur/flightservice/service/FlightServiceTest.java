package com.mayur.flightservice.service;

import com.mayur.flightservice.exception.ResourceNotFoundException;
import com.mayur.flightservice.model.Flight;
import com.mayur.flightservice.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void testGetFlightById() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        // Act
        Flight result = flightService.getFlightById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("FL123", result.getFlightNumber());
    }

    @Test
    public void testGetFlightById_NotFound() {
        // Arrange
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> flightService.getFlightById(1L));
    }
}