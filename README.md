
# Flight Booking System - API Documentation

This document provides details about the **Flight Booking System**, including API endpoints, request/response formats, and how to use them. The system consists of two services:

- **FlightService**: Manages flight-related operations.
- **BookingService**: Manages booking-related operations.

---

## 📌 FlightService

**Base URL**: `http://localhost:8082/flights`

### 1️⃣ Create a Flight
- **Method**: `POST`
- **Endpoint**: `/`
- **Request Body**:
```json
{
  "flightNumber": "FL123",
  "departure": "New York",
  "arrival": "London",
  "departureTime": "2025-12-25T10:00:00",
  "arrivalTime": "2025-12-25T18:00:00"
}
```
- **Response**:
```json
{
  "id": 1,
  "flightNumber": "FL123",
  "departure": "New York",
  "arrival": "London",
  "departureTime": "2025-12-25T10:00:00",
  "arrivalTime": "2025-12-25T18:00:00"
}
```

### 2️⃣ Get All Flights
- **Method**: `GET`
- **Endpoint**: `/`
- **Response**:
```json
[
  {
    "id": 1,
    "flightNumber": "FL123",
    "departure": "New York",
    "arrival": "London",
    "departureTime": "2025-12-25T10:00:00",
    "arrivalTime": "2025-12-25T18:00:00"
  }
]
```

### 3️⃣ Get a Flight by ID
- **Method**: `GET`
- **Endpoint**: `/{id}`
- **Response**:
```json
{
  "id": 1,
  "flightNumber": "FL123",
  "departure": "New York",
  "arrival": "London",
  "departureTime": "2025-12-25T10:00:00",
  "arrivalTime": "2025-12-25T18:00:00"
}
```

### 4️⃣ Search Flights
- **Method**: `GET`
- **Endpoint**: `/search?departure=New%20York&arrival=London&date=2025-12-25`
- **Response**:
```json
[
  {
    "id": 1,
    "flightNumber": "FL123",
    "departure": "New York",
    "arrival": "London",
    "departureTime": "2025-12-25T10:00:00",
    "arrivalTime": "2025-12-25T18:00:00"
  }
]
```

### 5️⃣ Update a Flight
- **Method**: `PUT`
- **Endpoint**: `/{id}`
- **Request Body**:
```json
{
  "flightNumber": "FL123",
  "departure": "New York",
  "arrival": "London",
  "departureTime": "2025-12-25T10:00:00",
  "arrivalTime": "2025-12-25T18:00:00"
}
```
- **Response**:
```json
{
  "id": 1,
  "flightNumber": "FL123",
  "departure": "New York",
  "arrival": "London",
  "departureTime": "2025-12-25T10:00:00",
  "arrivalTime": "2025-12-25T18:00:00"
}
```

### 6️⃣ Delete a Flight
- **Method**: `DELETE`
- **Endpoint**: `/{id}`
- **Response**: `204 No Content`

---

# 📌 FareService
**Base URL:** `http://localhost:8084/api/fares`

## 1️⃣ Create a Fare
**Method:** `POST`  
**Endpoint:** `/`

### **Request Body:**
```json
{
  "flightId": 1,
  "basePrice": 199.99,
  "tax": 25.50,
  "serviceFee": 10.00,
  "currency": "USD",
  "fareClass": "ECONOMY"
}
```

### **Response:**
```json
{
  "id": 1,
  "flightId": 1,
  "basePrice": 199.99,
  "tax": 25.50,
  "serviceFee": 10.00,
  "currency": "USD",
  "fareClass": "ECONOMY",
  "totalPrice": null
}
```

---

## 2️⃣ Get Fare by Flight ID
**Method:** `GET`  
**Endpoint:** `/flight/{flightId}`  
**Example:** `GET /api/fares/flight/1`

### **Response:**
```json
{
  "id": 1,
  "flightId": 1,
  "basePrice": 199.99,
  "tax": 25.50,
  "serviceFee": 10.00,
  "currency": "USD",
  "fareClass": "ECONOMY",
  "totalPrice": 235.49
}
```

---

## 3️⃣ Update Fare
**Method:** `PUT`  
**Endpoint:** `/flight/{flightId}`  
**Example:** `PUT /api/fares/flight/1`

### **Request Body:**
```json
{
  "basePrice": 249.99
}
```
**Response:** Updated fare object.

---

## 4️⃣ Delete Fare
**Method:** `DELETE`  
**Endpoint:** `/flight/{flightId}`  
**Example:** `DELETE /api/fares/flight/1`

---


## 📌 BookingService

**Base URL**: `http://localhost:8081/bookings`

### 1️⃣ Create a Booking
- **Method**: `POST`
- **Endpoint**: `/`
- **Request Body**:
```json
{
  "passengerFirstName": "John",
  "passengerLastName": "Doe",
  "gender": "MALE",
  "flightNumber": "FL123",
  "status": "CONFIRMED",
  "fareId": 1,
  "totalPrice": 235.49,
  "fareClass": "ECONOMY"
}
```
- **Response**:
```json
{
  "id": 1,
  "passengerFirstName": "John",
  "passengerLastName": "Doe",
  "gender": "MALE",
  "flightNumber": "FL123",
  "referenceNumber": "BOOK-78B5FE17",
  "bookingTime": "2025-03-28T01:21:39.428862",
  "status": "CONFIRMED",
  "fareId": 1,
  "totalPrice": 235.49,
  "fareClass": "ECONOMY"
}
```

### 2️⃣ Get All Bookings
- **Method**: `GET`
- **Endpoint**: `/`
- **Response**:
```json
[
  {
    "id": 1,
    "passengerFirstName": "John",
    "passengerLastName": "Doe",
    "gender": "MALE",
    "flightNumber": "FL123",
    "referenceNumber": "BOOK-78B5FE17",
    "bookingTime": "2025-03-28T01:21:39.428862",
    "status": "CONFIRMED",
    "fareId": 1,
    "totalPrice": 235.49,
    "fareClass": "ECONOMY"
  }
]
```

### 3️⃣ Get a Booking by ID
- **Method**: `GET`
- **Endpoint**: `/{id}`
- **Response**:
```json
{
  "id": 1,
  "passengerFirstName": "John",
  "passengerLastName": "Doe",
  "gender": "MALE",
  "flightNumber": "FL123",
  "referenceNumber": "BOOK-78B5FE17",
  "bookingTime": "2025-03-28T01:21:39.428862",
  "status": "CONFIRMED",
  "fareId": 1,
  "totalPrice": 235.49,
  "fareClass": "ECONOMY"
}
```

### 4️⃣ Confirm a Booking
- **Method**: `POST`
- **Endpoint**: `/confirm?flightId=1&firstName=John&lastName=Doe&gender=Male`
- **Response**:
```
Your booking is confirmed. Reference number is BOOK-468AE037
```

### 5️⃣ Search Booking by Reference Number
- **Method**: `GET`
- **Endpoint**: `/search?referenceNumber=BOOK-468AE037`
- **Response**:
```json
{
  "id": 2,
  "passengerFirstName": "John",
  "passengerLastName": "Doe",
  "gender": "Male",
  "flightNumber": "FL123",
  "referenceNumber": "BOOK-468AE037",
  "bookingTime": "2025-03-28T01:24:14.287525",
  "status": "CONFIRMED",
  "fareId": 2,
  "totalPrice": 1569.49,
  "fareClass": "ECONOMY"
}
```

### 6️⃣ Update a Booking
- **Method**: `PUT`
- **Endpoint**: `/{id}`
- **Request Body**:
```json
{
  "passengerFirstName": "Jane",
  "passengerLastName": "Doe",
  "gender": "Female",
  "flightNumber": "FL123",
  "status": "CANCELLED"
}
```
- **Response**:
```json
{
  "id": 1,
  "passengerFirstName": "Johine",
  "passengerLastName": "Doe",
  "gender": "FEMALE",
  "flightNumber": "FL123",
  "referenceNumber": "BOOK-78B5FE17",
  "bookingTime": "2025-03-28T01:21:39.428862",
  "status": "CANCELLED",
  "fareId": 1,
  "totalPrice": 235.49,
  "fareClass": "ECONOMY"
}
```

### 7️⃣ Delete a Booking
- **Method**: `DELETE`
- **Endpoint**: `/{id}`
- **Response**: `204 No Content`


# 📌 CheckinService

**Base URL:** `http://localhost:8083/api/checkins`

## 1️⃣ Perform Check-in
- **Method:** POST  
- **Endpoint**: `/search?referenceNumber=BOOK-468AE037`
- **Response**:

### ✅ Success Response (200 OK):
```json
{
  "id": 1,
  "bookingReference": "BOOK-468AE037",
  "confirmationNumber": "CHK-efd4039b",
  "checkinTime": "2025-03-28T01:33:05.4849115",
  "status": "COMPLETED"
}
```

---

## 2️⃣ Get Boarding Pass
**Method:** GET  
**Endpoint:** `/api/checkins/boarding-pass?bookingReference=ABC12345`
```

### ✅ Success Response (200 OK):
```
BOARDING PASS
Passenger: John Doe
Flight: FL123
Seat: 12B
Check-in Time: 2025-08-15T10:30:45.12345
Status: CHECKED_IN
Reference: ABC12345
```

```

## 3️⃣ Get Check-in Details
**Method:** GET  
**Endpoint:** `/ABC12345`
```

### ✅ Success Response (200 OK):
```json
{
  "id": 1,
  "bookingReference": "ABC12345",
  "confirmationNumber": "CHK-3A4B5C6D",
  "checkinTime": "2025-08-15T10:30:45.12345",
  "status": "COMPLETED"
}
```

---

## 🔄 Prerequisites:
- Booking must be confirmed before check-in
- Valid booking reference required for all endpoints
- Boarding pass only available after successful check-in

---

## ⚠️ Error Responses:
- **400 Bad Request** - Invalid booking reference
- **404 Not Found** - Booking not found or not checked in
- **409 Conflict** - Already checked in

