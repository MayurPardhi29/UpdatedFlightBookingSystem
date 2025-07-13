package com.mayur.flightservice.repository;

import com.mayur.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAndArrivalAndDepartureTimeBetween(
            String departure, String arrival, LocalDateTime start, LocalDateTime end);
}