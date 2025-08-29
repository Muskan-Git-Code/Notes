## 🔹 LLD Interview Scenario: Design a Ride-Sharing Application (e.g., Uber/Ola)

---

### 🧠 Step 1: Requirement Analysis

#### ✅ Functional Requirements:
* Rider, Driver Registration & Login - verify phone/ email (optional)
* Ride Booking - Ride Matching (assigning nearest driver on ride request), Fare estimation based on distance
* Ride cancellation (optional)
* Chat between rider & driver (optional)
* Ride Status Tracking (requested, ongoing, completed)
* Map integrations (optional)
* Payment Handling - Payment Method gateway, On paid, getting invoice (optional)
* Rating System (optional)
* Car-pooling (allowing different riders to accomodate in same ride) (optional)
* Notification (optional)

List down all the requirements, then ask if require to include any other functionality?
Say, being aware of limited time, i start with main service i.e. Ride Booking.

---

### 🚦 Step 2: UML/UseCase, Class Diagram

#### Flow/ UML Diagram
> Client (Hit the API Gateway through UI) → Application Layer (Business Logic, for insider services) → Database (stores user, driver details)

### UseCase Diagram
> Rider → Requests Cab (Giving pickup, drop location, distance, fare calculated) → Finding nearest cab (Driver sees the request, if accepts) → ride starts → On ride finish, show in recent rides and payment 

### HLD Diagram (Without this also works)
![Ride Booking System](RideSharing_HldDiagram.png)

### Class Diagram
| Entity                    | Attributes                                                                                                                                            | Design Pattern                                                                   |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| **User**                  | userId, name, phone, email                                                                                                                            |                                                                                  |
| **Rider**                 | Inherits **User**                                                                                                                                     | Factory                                                                          |
| **Driver**                | Inherits **User**, isAvailable, location, carDetails                                                                                                  | Factory                                                                          |
| **Location**              | latitude, longitude                                                                                                                                   |                                                                                  |
| **CarDetails**            | carId, model, number, color                                                                                                                           |                                                                                  |
| **Ride**                  | rideId, Rider, Driver, source, destination, rideStatus, fare                                                                                          |                                                                                  |
| **RideStatus**            | Pending, Assigned, Ongoing, Completed, Cancelled, TimeOut                                                                                             |                                                                                  |
| **RequestCabService**     | Calculate fare, distance based on pickup and drop location                                                                                            | Decorator (adding extra fare/tip, or more than 1 pickup/ drop location later on) |
| **CabFinderService**      | Ride matching i.e. finding most suitable driver based on distance (possibly using BFS/Dijkstra algorithm)                                             | Strategy                                                                         |
| **TripManagementService** | Manages all ongoing trips, and archives them after ride completion or cancellation (singleton, since driver availability must be globally consistent) | Singleton (if cab driver is available for all rides, else not available for all) |

---

### SOLID principles
Talk about SOLID principles, without saying word solid.

* Each service focuses on single task, so that any change won't impact any other functionality, like:
  * RequestCabService only handles fare & distance calculation.
  * CabFinderService only finds the most suitable driver.
  * TripManagementService only manages trip lifecycle.
* New ride types (like luxury cabs, premium cabs) can be added by extending CabDetails, without changing the existing core logic.
* Rider and Driver can replace User, as they inherit common properties and behaviors, ensuring correct inheritance.
* Interfaces are segregated based on the requirement, like CabFinderService only depends on driver availability and location, not on payment details or notifications.
* High-level modules (like TripManagementService) rely on abstractions rather than concrete implementations, allowing flexibility in swapping out algorithms (like Dijkstra → DFS search) or storage backends (MySQL → Cassandra → Redis).

✨ This way, our architecture stays clean, extensible, testable, and scalable, maintaining all SOLID principles.


### Database
* DriverLocationManagementService - Cassandra, Redis
* TripManagementService - Cassandra, Redis, MySQL
* CabFinderService - Kafka

### 🔐Design Patterns Used
* **Singleton** → `RideManager`
* **Observer** → Notify driver when new ride request arrives


### 🗂️ Step 3: API Design
API Design includes http method name, api endpoint name, request body and response body.
Adding API Design for authentication, similarly we can write for all.

#### SignIn/ SignUp API
```text 
POST /v1/signup
Request Body: { "name": String, "email": String }
Response Body: {
  "statusCode": 200/400,
  "errorMessage": String,
  "data": {
    "userId": Integer,
    "name": String,
    "email": String,
    "createdAt": Timestamp
  }
}
```
```text 
GET /v1/signin
Request Body: { email: String, password: String }
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        url: homePageUrl
    }
}
```

#### Rider API (To book the cab, see booking, cancel booking)
```text
POST /v1/bookings
Headers: Authorization: Bearer <jwt> token
Request Body: {
  "pickup": {"lat": 12.934, "lng": 77.612, "addr": "..."},
  "drop":   {"lat": 12.961, "lng": 77.644, "addr": "..."},
  "vehicleType": "Normal Cab | Premium Cab",
  "paymentMethod": "CARD|UPI|CASH",
  "cityId": "blr"
}
Response Body: {
    statusCode: 200/ 400
    errorMessage: String
    data: {
        "bookingId": "...",
        "state": "PENDING",
        "eta": 5,            // estimated time, 5 minutes
        "fareEstimate": {"min": 120, "max": 150, "currency": "INR"}
    }
}
```
```text
GET /v1/bookings/{bookingId}
ResponseBody{
    statusCode: 200/400
    errorMessage: String
    data: {
        bookingId: Integer, 
        Booking details
    }
}
```
```text
POST /v1/bookings/{bookingId}/cancel
Headers: Authorization
ResponseBody{
    statusCode: 200/400
    errorMessage: String
    data: {
        bookingId: Integer, 
        Message: "Booking Cancelled/ cannot cancel as trip already started"
    }
} 
```

#### Driver API (update status, accept ride offer)
```text
POST /v1/driver/status
Request Body: { "status": "ONLINE|OFFLINE|BUSY" }
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        Message: Status sent!
    }
}
```
```text
POST /v1/driver/offers/{bookingId}/accept
Headers: Authorization (driver), Idempotency-Key
Request Body: { "driverLocation": {"lat": 12.93, "lng": 77.61} }
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        assigned: true,
        tripDetails: {..} 
    }
}
```

#### Internal API (create trip, update trip transition in moving/completed, price estimation)
```text
POST /v1/trips
Request Body: { "bookingId": Integer, "driverId": Integer, "customerId": Integer, "cityId": "blr" }
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        tripId: Integer,
        tripStatus: "ENROUTE"
    }
}
```
```text
UPDATE /v1/trips/{tripId}/events
Request Body: { "event": "PICKED_UP|START|COMPLETE|CANCEL", "tripId": 1733900123 }
Response Body: {
    statusCode: 200/400
    errorMessage: "Invalid transition"
    data: {
        message: "New status assigned"
    }
}
```
```text
POST /v1/pricing/estimate 
RequestBody: { "pickup": Location(lat/lan), "drop": location, cityId: Integer }
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        distanceKm: Long,
        durationMin: TimeStamp,
        estimateAmt: {min, max, currency},
        surgeMultiplier(based on type of cab): Long,
        PlatformFee: Long
    }
}
```

#### WebSocket API (get all drivers/ customers in a city)
```text
Driver: `GET wss://api.example.com/ws/driver/stream?cityId=blr`
Customer: `GET wss://api.example.com/ws/customer/stream?cityId=blr`
```

#### Kafka Event Payload
Partition: per city, replication factor: 3
```text
Kafka Topic: booking.requested.v1 (key: bookingId) – created by Cab Request Svc.
Kafka Event Payload: {
  "bookingId": "...",
  "customerId": "...",
  "pickup": {"lat":12.9,"lng":77.6},
  "drop": {"lat":12.96,"lng":77.64},
  "vehicleType": "SEDAN",
  "cityId": "blr",
  "createdAt": 1733900123
}
```

#### Database Schemas

##### Cassandra Schemas (CQL language)
Driver States (time-series)
```sql
CREATE TABLE driver_states (
  driver_id uuid,
  event_time timestamp,
  city_id text,
  lat double,
  lng double,
  status text,
  PRIMARY KEY ((driver_id), event_time)
) WITH CLUSTERING ORDER BY (event_time DESC)
```

Bookings
```sql
CREATE TABLE bookings (
  booking_id uuid PRIMARY KEY,
  customer_id uuid,
  city_id text,
  pickup_lat double,
  pickup_lng double,
  drop_lat double,
  drop_lng double,
  state text,
  created_at timestamp,
  offer_expiry_at timestamp,
  assigned_driver_id uuid
);
```

Trips
```sql
CREATE TABLE trips (
  trip_id uuid PRIMARY KEY,
  booking_id uuid,
  driver_id uuid,
  customer_id uuid,
  city_id text,
  status text,
  start_ts timestamp,
  end_ts timestamp,
  polyline text,
  distance_km double,
  duration_min double,
  price double
);
```

##### MySQL Schemas (DDL)
```sql
CREATE TABLE receipts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  trip_id CHAR(36) NOT NULL,
  currency VARCHAR(8) NOT NULL,
  fare_subtotal DECIMAL(10,2) NOT NULL,
  taxes DECIMAL(10,2) NOT NULL,
  discounts DECIMAL(10,2) NOT NULL DEFAULT 0,
  total DECIMAL(10,2) NOT NULL,
  fare_breakdown JSON,
  payment_ref VARCHAR(128),
  settlement_status ENUM('PENDING','CAPTURED','FAILED','REFUNDED') DEFAULT 'PENDING',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY idx_trip_id (trip_id),
  KEY idx_created_at (created_at)
) ENGINE=InnoDB;
```

---

### 🧱 Class Structure (Entity, Repository, Services, Controller, Exception)
While writing class structure, one should always add null checks, try-catch block, and exception handling.

#### Basic Entity Classes
```java
class User {
    String userId;
    String name;
    String phone;
}

class Rider extends User {
    // Additional rider behavior
}

class Driver extends User {
    boolean isAvailable;
    Location location;
    Car car;
}

class Car {
    String number;
    String model;
    String color;
}

class Location {
    double latitude;
    double longitude;
}

enum RideStatus {
    REQUESTED, ONGOING, COMPLETED, CANCELLED
}

class Ride {
    String rideId;
    Rider rider;
    Driver driver;
    Location source;
    Location destination;
    RideStatus status;
    double fare;
}
```

#### Basic Services 
```java
class RequestCabService {
  double calculateFare(Location source, Location dest);
}
class CabFinderService {
  Trip bookRide(Rider rider, Location source, Location dest);
}
class TripManagementService {
  void startRide(String rideId);
  void endRide(String rideId);
}

class TripManagementServiceImpl implements TripManagementService {  // Singleton Design Pattern for all riders.
private Map<String, Ride> ongoingRides;
private static TripManagementServiceImpl instance;

    private TripManagementServiceImpl() {
        ongoingRides = new HashMap<>();
    }

    public static TripManagementServiceImpl getInstance() {
        if (instance == null){  instance = new TripManagementServiceImpl(); }
        return instance;
    }

    public void addRide(Ride ride) { ongoingRides.put(ride.rideId, ride); }
    public Ride getRide(String rideId) { return ongoingRides.get(rideId); }
}

//#### Controller Class
//Either calling API's or main method for booking a ride
void fn() {
    // Pseudocode
    TripManagementService rideService = new TripManagementService();
    Ride ride = rideService.bookRide(rider, sourceLoc, destLoc);
    rideService.startRide(ride.getRideId());
    rideService.endRide(ride.getRideId());
}
```

---

### 🔎 Mid/Follow-up Questions

#### 1. 🔁 How will you handle concurrency?
* Use locks/synchronized blocks on driver availability and ride booking.
* Use thread-safe data structures like `ConcurrentHashMap`.

#### 2. 📡 What if no drivers are available?
* Return failure response with a retry option.
* Maintain a waiting queue for riders.

#### 3. 📊 How would you scale this?
* Use messaging queues (Kafka) for event handling.

#### 4. 🧭 How to calculate real-time distance?
* Integrate with map APIs (Google Maps, OpenStreetMap).

#### 5. 🔄 What about payment?
* Create `PaymentService` with methods like: `generateInvoice()`, `deductAmount()`
* Integrate external payment gateway (Stripe, Razorpay).

---
