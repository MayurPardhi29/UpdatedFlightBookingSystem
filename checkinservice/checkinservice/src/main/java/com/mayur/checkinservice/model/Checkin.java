package com.mayur.checkinservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingReference;
    private String confirmationNumber;
    private LocalDateTime checkinTime;
    private String status; // "COMPLETED", "FAILED"

    @PrePersist
    protected void onCreate() {
        this.checkinTime = LocalDateTime.now();
        this.confirmationNumber = "CHK-" + UUID.randomUUID().toString().substring(0, 8);
    }
}