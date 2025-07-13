package com.mayur.fareservice.controller;

import com.mayur.fareservice.model.Fare;
import com.mayur.fareservice.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fares")
public class FareController {
    @Autowired private FareService fareService;

    @PostMapping
    public ResponseEntity<Fare> createFare(@RequestBody Fare fare) {
        return new ResponseEntity<>(fareService.createFare(fare), HttpStatus.CREATED);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<Fare> getFareByFlightId(@PathVariable Long flightId) {
        return new ResponseEntity<>(fareService.getFareByFlightId(flightId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Fare>> getAllFares() {
        return new ResponseEntity<>(fareService.getAllFares(), HttpStatus.OK);
    }

    @PutMapping("/flight/{flightId}")
    public ResponseEntity<Fare> updateFare(@PathVariable Long flightId, @RequestBody Fare fareUpdates) {
        return new ResponseEntity<>(fareService.updateFare(flightId, fareUpdates), HttpStatus.OK);
    }

    @DeleteMapping("/flight/{flightId}")
    public ResponseEntity<Void> deleteFare(@PathVariable Long flightId) {
        fareService.deleteFare(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}