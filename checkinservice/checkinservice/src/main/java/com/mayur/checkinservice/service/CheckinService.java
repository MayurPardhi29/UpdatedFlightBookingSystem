package com.mayur.checkinservice.service;

import com.mayur.bookingservice.model.Booking;
import com.mayur.checkinservice.exception.CheckinException;
import com.mayur.checkinservice.model.Checkin;
import com.mayur.checkinservice.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String BOOKING_SERVICE_URL = "http://BOOKING-SERVICE/bookings";

    public Checkin performCheckin(String bookingReference) {
        try {
            // Check if check-in already exists
            Optional<Checkin> existingCheckin = checkinRepository.findByBookingReference(bookingReference);
            if (existingCheckin.isPresent()) {
                throw new CheckinException("Check-in already completed for booking: " + bookingReference);
            }

            // Get booking details from Booking Service
            Booking booking = restTemplate.getForObject(
                    BOOKING_SERVICE_URL + "/search?referenceNumber=" + bookingReference,
                    Booking.class);

            if (booking == null) {
                throw new CheckinException("Booking not found with reference: " + bookingReference);
            }

            // Validate booking status
            if (!"CONFIRMED".equals(booking.getStatus())) {
                throw new CheckinException("Only confirmed bookings can be checked in");
            }

            // Create new check-in record
            Checkin checkin = new Checkin();
            checkin.setBookingReference(bookingReference);
            checkin.setStatus("COMPLETED");

            // Save check-in record
            Checkin savedCheckin = checkinRepository.save(checkin);

            // Update booking status in Booking Service
            booking.setStatus("CHECKED_IN");
            restTemplate.put(BOOKING_SERVICE_URL + "/" + booking.getId(), booking);

            return savedCheckin;
        } catch (Exception e) {
            // Save failed check-in attempt
            Checkin failedCheckin = new Checkin();
            failedCheckin.setBookingReference(bookingReference);
            failedCheckin.setStatus("FAILED");
            checkinRepository.save(failedCheckin);

            throw new CheckinException("Check-in failed: " + e.getMessage());
        }
    }

    public String generateBoardingPass(String bookingReference) {
        Checkin checkin = checkinRepository.findByBookingReference(bookingReference)
                .orElseThrow(() -> new CheckinException("No check-in found for booking: " + bookingReference));

        if (!"COMPLETED".equals(checkin.getStatus())) {
            throw new CheckinException("Check-in not completed for booking: " + bookingReference);
        }

        Booking booking = restTemplate.getForObject(
                BOOKING_SERVICE_URL + "/search?referenceNumber=" + bookingReference,
                Booking.class);

        return String.format(
                "BOARDING PASS\n" +
                        "Confirmation: %s\n" +
                        "Passenger: %s %s\n" +
                        "Flight: %s\n" +
                        "Check-in Time: %s\n" +
                        "Seat: %s",
                checkin.getConfirmationNumber(),
                booking.getPassengerFirstName(),
                booking.getPassengerLastName(),
                booking.getFlightNumber(),
                checkin.getCheckinTime(),
                assignSeat()
        );
    }

    private String assignSeat() {
        int row = (int) (Math.random() * 30) + 1;
        char seat = (char) ('A' + (int) (Math.random() * 6));
        return row + String.valueOf(seat);
    }

    public Checkin getCheckinDetails(String bookingReference) {
        return checkinRepository.findByBookingReference(bookingReference)
                .orElseThrow(() -> new CheckinException("No check-in found for booking: " + bookingReference));
    }
}