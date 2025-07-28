## 🔹 LLD Interview Scenario: Design a Ride-Sharing Application (e.g., Uber/Ola)
Start with telling the flow. I will go with these steps, feel free to let me know if you want me to skip any step.

---

### 🧠 Step 1: Gathering Requirements

#### ❓ Ask Clarifying Questions:
* Target users: Are we designing only for riders, or drivers as well?
* Architecture: Is it a monolithic or microservices architecture?

#### ✅ Functional Requirements:
* Rider Registration & Login
* Driver Registration & Login
* Ride Booking
* Ride Matching (assigning nearest driver)
* Ride Status Tracking (requested, ongoing, completed)
* Payment Handling (optional)
* Including tip (optional)
* Rating System (optional)
* Map integrations (optional)
* Payment gateway (optional)
* Chat between rider & driver (optional)
* Ride cancellation (optional)
* Car-pooling (allowing different riders to accomodate in same ride) (optional)

#### 🛠️ Non-Functional Requirements:
* Scalable (millions of users)
* High Availability
* Fault Tolerant
* Eventual Consistency
* Low Latency (performance i.e. should run without any lag or delay)
* Database (mostly users came to book the ride i.e. adding new data in database, that means write-heavy system)
* Thread Safety
* Extensible Design (to different browsers)
* security (protection of user data)
* User-friendly interface (optional)
* Notification (optional)

List down all the requirements, then ask if require to include any other functionality?
Say, being aware of limited time these are the basic ones i want to start for (or mark others as optional).

---

### 🚦 Step 2: UML/UseCase, Class Diagram

#### Flow/ UML Diagram
```text
Client (Hit the API Gateway through UI) → Application Layer (Add location, see price, Book Ride, On ride accept (Start/End ride), Calculate fare) → Database (store userId, location, price) 
```

#### Class Diagram
```
| Entity      | Attributes                                                                                                                        |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------|
| User        | userId, name, phone, email                                                                                                        |
| Rider       | inherits User                                                                                                                     |
| Driver      | inherits User, isAvailable, location, carDetails                                                                                  |
| Car         | carId, model, number, color                                                                                                       |
| Ride        | rideId, Rider, Driver, source, destination, status, fare                                                                          |
| Location    | latitude, longitude                                                                                                               |
| RideService | book ride (ride matching), start/stop ride, calculate fare                                                                        |
| RideManager | manages all rides (singleton, as for all users if d1 is taking the ride that means it is shown as not available so single object) |
```

---

### 🗂️ Step 3: API Design
API Design includes http method name, api endpoint name, request body and response body.
Adding API Design for authentication, similarly we can write for all.

### POST `/signup`
```text 
Request Body: {
  "userId": Integer,
  "name": String,
  "password": String,
  "email": String,
  "phone": String
}

Response Body: {
  "statusCode": 200/400,
  "errorMessage": String,
  "data": {
    "userId": Integer,
    "name": String,
    "email": String,
    "phone": String,
    "createdAt": Timestamp
  }
}
```

### GET `/signin`
```text 
Request Body: {
    email: String,
    password: String
}
Response Body: {
    statusCode: 200/400
    errorMessage: String
    data: {
        url: homePageUrl
    }
}
```

---

### 🧱 Step 4: Class Structure (Entity, Repository, Services, Controller, Exception)
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
class RideService {
Ride bookRide(Rider rider, Location source, Location dest);
void startRide(String rideId);
void endRide(String rideId);
double calculateFare(Location source, Location dest);
}

class RideManager {
private Map<String, Ride> ongoingRides;
private static RideManager instance;

    private RideManager() {
        ongoingRides = new HashMap<>();
    }

    public static RideManager getInstance() {
        if (instance == null) {
            instance = new RideManager();
        }
        return instance;
    }

    public void addRide(Ride ride) { ongoingRides.put(ride.rideId, ride); }
    public Ride getRide(String rideId) { return ongoingRides.get(rideId); }
}
```

#### Ride Matching/ Book Ride Logic
Find nearest driver from available drivers by distance.
```java
class DriverMatchingService {
    List<Driver> availableDrivers;

    public Driver findNearestDriver(Location source) {
        Driver nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Driver d : availableDrivers) {
            if (d.isAvailable) {
                double dist = distance(d.location, source);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = d;
                }
            }
        }
        return nearest;
    }

    private double distance(Location a, Location b) {   // sqrt( (x1-x2)^2 + (y2-y1)^2 );
        return Math.sqrt(Math.pow(a.latitude - b.latitude, 2)
                       + Math.pow(a.longitude - b.longitude, 2));
    }
}
```

#### Controller Class 
Either calling API's or main method for booking a ride
```java
// Pseudocode
RideService rideService = new RideService();
Ride ride = rideService.bookRide(rider, sourceLoc, destLoc);
rideService.startRide(ride.getRideId());
rideService.endRide(ride.getRideId());
```

---

### 🔐 Step 5: Design Patterns Used
* **Singleton** → `RideManager`
* **Observer** → Notify driver when new ride request arrives

---

### 📦 Step 6: **Databases & Storage**
* **User/Driver/Trip**: RDBMS for normalized data
* **Location Data**: Redis + Cassandra
* **Cache**: Redis (in-memory) for current ride state
* **Queues**: Kafka / RabbitMQ (for ride requests, notifications)


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



