package com.mayur.bookingservice.repository;

import com.mayur.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByReferenceNumber(String referenceNumber);
}

