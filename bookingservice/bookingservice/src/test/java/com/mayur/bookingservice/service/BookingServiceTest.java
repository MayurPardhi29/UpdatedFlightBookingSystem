package com.mayur.bookingservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mayur.bookingservice.model.Booking;
import com.mayur.bookingservice.repository.BookingRepository;
import com.mayur.flightservice.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testConfirmBooking() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        when(restTemplate.getForObject(anyString(), eq(Flight.class))).thenReturn(flight);

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setFlightNumber("FL123");
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Act
        String referenceNumber = bookingService.confirmBooking(1L, "John", "Doe", "Male");

        // Assert
        assertNotNull(referenceNumber);
    }
}