package com.mayur.bookingservice.controller;

import com.mayur.bookingservice.model.Booking;
import com.mayur.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmBooking(
            @RequestParam Long flightId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String gender) {
        String referenceNumber = bookingService.confirmBooking(flightId, firstName, lastName, gender);
        return ResponseEntity.ok("Booking confirmed. Reference: " + referenceNumber);
    }

    @GetMapping("/search")
    public ResponseEntity<Booking> searchByReference(
            @RequestParam String referenceNumber) {
        return ResponseEntity.ok(bookingService.findByReferenceNumber(referenceNumber));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestBody Booking bookingUpdates) {
        // Prevent ID and protected fields from being updated
        bookingUpdates.setId(id);
        return ResponseEntity.ok(bookingService.updateBooking(id, bookingUpdates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}