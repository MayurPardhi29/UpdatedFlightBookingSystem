package com.mayur.checkinservice.repository;

import com.mayur.checkinservice.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    Optional<Checkin> findByBookingReference(String bookingReference);
}