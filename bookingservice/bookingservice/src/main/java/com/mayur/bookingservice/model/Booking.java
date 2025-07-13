package com.mayur.bookingservice.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerFirstName;
    private String passengerLastName;
    private String gender;
    private String flightNumber;

    @Column(unique = true, updatable = false)
    private String referenceNumber;

    @Column(updatable = false)
    private LocalDateTime bookingTime;

    private String status;

    // New Fields
    private Long fareId;
    private BigDecimal totalPrice;
    private String fareClass;

    // Pre-persist hook to auto-generate values
    @PrePersist
    protected void onCreate() {
        if (this.referenceNumber == null) {
            this.referenceNumber = "BOOK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
        if (this.bookingTime == null) {
            this.bookingTime = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = "CONFIRMED";
        }
    }

    // Constructors
    public Booking() {}

    public Booking(String passengerFirstName, String passengerLastName,
                   String gender, String flightNumber, String status,
                   Long fareId, BigDecimal totalPrice, String fareClass) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.gender = gender;
        this.flightNumber = flightNumber;
        this.status = status;
        this.fareId = fareId;
        this.totalPrice = totalPrice;
        this.fareClass = fareClass;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassengerFirstName() { return passengerFirstName; }
    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() { return passengerLastName; }
    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getReferenceNumber() { return referenceNumber; }
    protected void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDateTime getBookingTime() { return bookingTime; }
    protected void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // New Getters and Setters
    public Long getFareId() { return fareId; }
    public void setFareId(Long fareId) { this.fareId = fareId; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getFareClass() { return fareClass; }
    public void setFareClass(String fareClass) { this.fareClass = fareClass; }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(referenceNumber, booking.referenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceNumber);
    }

    // toString()
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", gender='" + gender + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", bookingTime=" + bookingTime +
                ", status='" + status + '\'' +
                ", fareId=" + fareId +
                ", totalPrice=" + totalPrice +
                ", fareClass='" + fareClass + '\'' +
                '}';
    }
}
