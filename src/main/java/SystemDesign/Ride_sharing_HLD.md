### 🧠 Step 1: Requirement Analysis

#### ❓ Ask Clarifying Questions:
* Target users: Are we designing only for riders, or drivers as well?
* Architecture: Is it a monolithic or microservices architecture?

#### ✅ Functional Requirements:
* Rider, Driver Registration & Login - verify phone/ email
* Ride Booking - Ride Matching (assigning nearest driver on ride request), Fare estimation based on distance
* Ride cancellation (optional)
* Chat between rider & driver (optional)
* Ride Status Tracking (requested, ongoing, completed)
* Map integrations (optional)
* Payment Handling - Payment Method gateway, On paid, getting invoice
* Rating System (optional)
* Car-pooling (allowing different riders to accomodate in same ride) (optional)

#### 🛠️ Non-Functional Requirements:
* Scalable - Millions of users, so should be able to handle high throughput, and large datasets, while maintaining low latency
* High Availability
* Fault Tolerant
* Eventual Consistency
* Usability - Intutive, user-friendly interface for users to input data, and view results
* Extendability: Ability to support additional features in future
* Security and Privacy: TLS, JWT/ OAuth Authentication, Authorization, and rate limiting.



* Rate limiting per user
* Database (mostly users came to book the ride i.e. adding new data in database, that means write-heavy system)
* Thread Safety
* security (protection of user data)
* Notification (optional)

List down all the requirements, then ask if require to include any other functionality?
Say, being aware of limited time these are the basic ones i want to start for (or mark others as optional).


### High Level Architecture Diagram:

1) Edge & API Gateway → 2) Real-Time Ingestion (locations, events) → 3) Match & Pricing Services → 4) Routing/ETA & Maps → 5) Trip Orchestrator (state machine) → 6) Payments/Billing → 7) Data Stores (OLTP, cache, TSDB, search) → 8) Async & Batch (Kafka, stream proc, aggregations) → 9) Frontend (iOS/Android/Web) → 10) Monitoring/Logging.


Auth and API Gateway: API Performance Gateway (different payloads for rider, driver), Security (Authorization, Authentication), and Rate Limiting

Data Ingestion Layer: Collect real time location and event details through third party API. 


### Core Entities
Customer (Requests cab, giving start, end points and fare calculated based on distance), Driver (nearby driver among all drivers), and ride starts if driver accepts the ride.

### Diagram
![Ride Booking System](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fbeautiful%2F&psig=AOvVaw1KgYJ0asAB1lXuXRtzyLJ3&ust=1755626849080000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCODxmej5lI8DFQAAAAAdAAAAABAE)

**Drivers (d1, d2, d3)** interacts with different **websocket servers** using **load balancer**, and managed by **websocket manager**. Driver connections are then routed to the **'Driver Location Management Service'**, which maintains real-time driver stats (GPS co-ordinates, availability status) using **redis** (for faster in-memory cache) and asynchronously updates **cassandra database** (due to its ability to handle geographically distributed data with high scalability and availability).

**Customers (c1, c2, c3)** also interacts with different **websocket servers** using **load balancer**, and managed by **websocket manager**. Customer connections are then routed to **'Cab Request Management Service'**, which validates trip details (pickup, drop location, fare estimated based on distance), and publish request to **kafka** message queue.

**Kafka** feeds **'Cab Finder Service'** which identifies most suitable driver, by querying redis for real-time driver location. To optimize driver selection it may use **graph-based algorithm** (like Dijkstra) to find nearest available driver. Once driver accepts request, the **'Trip Management Service'** initializes and maintains ride state with GPS updates in **redis** and persist data in **Cassandra Database** for fast real-time lookups. On ride completion, a summary (pickup, drop, driver, customer, distance, fare, payment method) details are stored in **MySQL database** for its ACID properties, as historical trip data.


### APIs used
**Customer**
```
POST /v1/bookings {pickup, drop, driver, customer, distance, fare, payment method}
GET  /v1/bookings/{id}
WS  /v1/stream  // booking & trip updates
```

**Driver**
```
POST /v1/driver/status {ONLINE|OFFLINE|BUSY}
POST /v1/driver/accept {bookingId}
WS /v1/driver/stream  // locationUpdates → server, offers (nearest rides) ← server
```

### Edge cases and failure modes
- no driver found
- multiple driver accepts the ride
- driver disconnect in mid of ride
- customer cancelled the ride in between, or when driver arriving to the location
- Fraud cases (like same person as customer and driver from different accounts)
- payment failure

