package com.mayur.flightservice.service;

import com.mayur.flightservice.exception.ResourceNotFoundException;
import com.mayur.flightservice.model.Flight;
import com.mayur.flightservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
    }

    public List<Flight> searchFlights(String departure, String arrival, LocalDate date) {
        departure = departure.trim();
        arrival = arrival.trim();

        return flightRepository.findByDepartureAndArrivalAndDepartureTimeBetween(
                departure,
                arrival,
                date.atStartOfDay(),
                date.atTime(23, 59, 59)
        );
    }

    public Flight updateFlight(Long id, Flight flight) {
        Flight existingFlight = getFlightById(id);
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setDeparture(flight.getDeparture());
        existingFlight.setArrival(flight.getArrival());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        return flightRepository.save(existingFlight);
    }

    public void deleteFlight(Long id) {

        flightRepository.deleteById(id);
    }
}