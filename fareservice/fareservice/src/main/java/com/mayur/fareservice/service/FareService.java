package com.mayur.fareservice.service;

import com.mayur.fareservice.exception.FareNotFoundException;
import com.mayur.fareservice.model.Fare;
import com.mayur.fareservice.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class FareService {
    @Autowired private FareRepository fareRepository;

    public Fare createFare(Fare fare) {
        if (fareRepository.existsByFlightId(fare.getFlightId())) {
            throw new IllegalArgumentException("Fare already exists for flight ID: " + fare.getFlightId());
        }
        return fareRepository.save(fare);
    }

    public Fare getFareByFlightId(Long flightId) {
        return fareRepository.findByFlightId(flightId)
                .orElseThrow(() -> new FareNotFoundException("Fare not found for flight ID: " + flightId));
    }

    public List<Fare> getAllFares() {
        return fareRepository.findAll();
    }

    public Fare updateFare(Long flightId, Fare fareUpdates) {
        Fare existingFare = getFareByFlightId(flightId);
        if (fareUpdates.getBasePrice() != null) existingFare.setBasePrice(fareUpdates.getBasePrice());
        if (fareUpdates.getTax() != null) existingFare.setTax(fareUpdates.getTax());
        if (fareUpdates.getServiceFee() != null) existingFare.setServiceFee(fareUpdates.getServiceFee());
        if (fareUpdates.getCurrency() != null) existingFare.setCurrency(fareUpdates.getCurrency());
        if (fareUpdates.getFareClass() != null) existingFare.setFareClass(fareUpdates.getFareClass());
        return fareRepository.save(existingFare);
    }

    public void deleteFare(Long flightId) {
        Fare fare = getFareByFlightId(flightId);
        fareRepository.delete(fare);
    }
}