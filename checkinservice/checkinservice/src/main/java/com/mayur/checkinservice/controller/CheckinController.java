package com.mayur.checkinservice.controller;

import com.mayur.checkinservice.model.Checkin;
import com.mayur.checkinservice.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkins")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @PostMapping
    public ResponseEntity<Checkin> performCheckin(@RequestParam String bookingReference) {
        Checkin checkin = checkinService.performCheckin(bookingReference);
        return ResponseEntity.ok(checkin);
    }

    @GetMapping("/boarding-pass")
    public ResponseEntity<String> getBoardingPass(@RequestParam String bookingReference) {
        String boardingPass = checkinService.generateBoardingPass(bookingReference);
        return ResponseEntity.ok(boardingPass);
    }

    @GetMapping("/{bookingReference}")
    public ResponseEntity<Checkin> getCheckinDetails(@PathVariable String bookingReference) {
        Checkin checkin = checkinService.getCheckinDetails(bookingReference);
        return ResponseEntity.ok(checkin);
    }
}