package com.mayur.fareservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long flightId;

    private BigDecimal basePrice;
    private BigDecimal tax;
    private BigDecimal serviceFee;
    private String currency;
    private String fareClass; // ECONOMY, BUSINESS, FIRST

    @Transient
    private BigDecimal totalPrice;

    @PostLoad
    private void calculateTotalPrice() {
        this.totalPrice = basePrice.add(tax).add(serviceFee);
    }
}