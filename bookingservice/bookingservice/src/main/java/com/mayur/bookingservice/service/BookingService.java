package com.mayur.bookingservice.service;

import com.mayur.bookingservice.exception.ResourceNotFoundException;
import com.mayur.bookingservice.model.Booking;
import com.mayur.bookingservice.repository.BookingRepository;
import com.mayur.flightservice.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import com.mayur.fareservice.model.Fare;

import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String FLIGHT_SERVICE_URL = "http://flight-service/flights";

    private static final String FARE_SERVICE_URL = "http://fare-service/api/fares";

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    public String confirmBooking(Long flightId, String firstName, String lastName, String gender) {
        // Verify flight exists
        Flight flight = restTemplate.getForObject(
                FLIGHT_SERVICE_URL + "/" + flightId, Flight.class);

        if (flight == null) {
            throw new ResourceNotFoundException("Flight not found with id: " + flightId);
        }

        // Get fare information from Fare Service
        Fare fare = restTemplate.getForObject(
                FARE_SERVICE_URL + "/flight/" + flightId, Fare.class);

        if (fare == null) {
            throw new ResourceNotFoundException("Fare not found for flight ID: " + flightId);
        }

        Booking booking = new Booking();
        booking.setPassengerFirstName(firstName);
        booking.setPassengerLastName(lastName);
        booking.setGender(gender);
        booking.setFlightNumber(flight.getFlightNumber());
        booking.setStatus("CONFIRMED");
        booking.setFareId(fare.getId());
        booking.setTotalPrice(fare.getBasePrice().add(fare.getTax()).add(fare.getServiceFee()));
        booking.setFareClass(fare.getFareClass());

        return bookingRepository.save(booking).getReferenceNumber();
    }

    public Booking findByReferenceNumber(String referenceNumber) {
        return bookingRepository.findByReferenceNumber(referenceNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with reference: " + referenceNumber));
    }

    public Booking updateBooking(Long id, Booking bookingUpdates) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    // Update only allowed fields
                    if (bookingUpdates.getPassengerFirstName() != null) {
                        existingBooking.setPassengerFirstName(bookingUpdates.getPassengerFirstName());
                    }
                    if (bookingUpdates.getPassengerLastName() != null) {
                        existingBooking.setPassengerLastName(bookingUpdates.getPassengerLastName());
                    }
                    if (bookingUpdates.getGender() != null) {
                        existingBooking.setGender(bookingUpdates.getGender());
                    }
                    if (bookingUpdates.getStatus() != null) {
                        existingBooking.setStatus(bookingUpdates.getStatus());
                    }
                    // Flight number cannot be changed after creation
                    return bookingRepository.save(existingBooking);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }
}