package com.mayur.fareservice.repository;

import com.mayur.fareservice.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FareRepository extends JpaRepository<Fare, Long> {
    Optional<Fare> findByFlightId(Long flightId);
    boolean existsByFlightId(Long flightId);
}