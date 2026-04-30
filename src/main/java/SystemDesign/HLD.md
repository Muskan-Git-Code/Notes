## High Level Design (HLD): 
Tells about overall architecture of a system. Idea is to design scalable, maintainable, performance optimized and easily extendable system.

**Flow:**
> Client Request through UI → DNS (for knowing IP of website) → Load Balancer (routes traffic) → Application Server (Scaling) → Message Queue (for buffer time, notify when consumer is available) → Database (Handles large data requests) → Caching (faster data access)

## Approach for interview:
> Requirements → Core Entities → API/ System Interface → Data Flow & High-Level Design → Deep Dives
- **Step 1: Requirement Analysis:** Ask a lot of questions => 15 min
  - Functional Requirements: (Write as story telling i.e. in our system step by step what user should be able to do)
    - "User should be able to.." statements
    - Prioritize top 3 requirements and ask if these requirements are good to move forward.
  - Non-Functional Requirements:
    - "System should be able to.." statements
      - scale for incoming requests with low latency and high throughput
      - High Availability, Eventual Consistency and Partition tolerance
      - Read-write ratio: more reads as compare to write
      - Usability i.e. user-friendly interface
      - Extendable for future enhancements
      - security features like protection of user data via authentication, authorization and rate limiting
- **Step 2: Core Entities:** Who are the actors and noun/ resources in system?
- **Step 3: API/ System Interface:** Think basic functionalities and say I am going to outline some simple APIs, though may come back for optimization as we dig deeper into the design => 10 min.
  ```
  POST /url
  Request Body:{
    "id": Integer,
    "name": String,
    "creation_date": Timestamp
  }
  ->
  Response Body:{
    "statusCode": 200/400,
    "errorMessage": String,
    "data": {
      "successMessgae": String
    }
  }
  ```
- **Step 4: DB Choice:** DB Schema (SQL vs NoSQL): It depends on scale and consistency requirements of the system. While anything from MySQL to MongoDB would be fine choices. I will go with:
  - SQL for its ACID properties.
  - NoSQL as system is read heavy and requires horizontal scaling.
- **Step 5: High Level Design:** Based on APIs create diagram, and explain in detail => 20 min
- **Step 6:** Edge cases, and Deep Dives => 10 min

---
### 🧱 **Representation of System:**

#### 1️⃣ **Class Diagram**
Shows different *classes* and *objects* for different functionalities of a system.
```markdown
| Library              | Book                  | Member              |
|----------------------|-----------------------|---------------------|
| books: List<Book>    | title: String         | mId: String         |
| members: List<Member>| author: String        | name: String        |
|                      | bno: String           | contact: String     |
|                      | available: Boolean    |                     |
```

---
#### 2️⃣ **UML (Unified Modeling Language) Sequence Diagram**
Tells how different classes interact together.
```
Member                              Library                               Book
    |                                   |                                   |
    | -- requestBorrow(book)-->         |                                   |
    |                                   |-- checkAvailability() -->         |
    |                                   |        <<available>>              |
    |                                   |   <--checkoutBook()--             |
    |     <--borrowed--                 |                                   |
```

---
#### 3️⃣ **Use Case Diagram**
Used to understand different *use-cases* for a system. 

**Example: Library Management System**
* Manage Books
* Manage Members
* Borrow Book
* Return Book


---
### 📘 *Distributed System:*
> A group of computers that *communicate and coordinate actions* in order to appear as a *single coherent system* to the end user.

### Software Development LifeCycle (SDLC)
For building software from idea to development and maintenance includes:
> Requirement Gathering and Analysis → System Design and Data Flow → Implementation/ Coding → Testing and Bug Fixing → Production Deployment → Maintenance and Monitoring performance
---

### 📌 *CAP Theorem / Brewer’s Theorem*
Any distributed system can have only *2 things out of*:
* *Consistency*: All clients see the same data at the same time.
* *Availability*: The system is available even if some nodes/ server fail.
* *Partition/ Fault Tolerance*: The system continues operating despite network failures.

> In scenario of partial tolerance, we usually *prefer availability* and are okay with *eventual consistency* (data eventually reflects latest value), if high availability required and there are less write operations on the system.
---

### 🚀 *Scaling*: 
Handling large amount of requests at once and responds quickly with the latest information.
* **Vertical Scaling (Scale-Up):** Increase scale of system by adding more power (i.e. adding better CPU processors, increasing RAM) to a *single machine*. Single point of failure, but is Cost-effective for small systems.
* **Horizontal Scaling (Scale-Out):** With increase in demand of business when it's not possible to have such large databases, we prefer horizontal scaling i.e. add *more machines*. Requires *load balancer* and have better fault tolerance and scalability.

> ☢️ **Single Point of Failure:** Just like any other computer, if our main server (i.e. Load Balancer) dies, the system fails. To avoid this we can swap the main-server by a **_stand-by routing server_**. For swapping either change the IP address in DNS registry, or assign a static IP to main server and point that static IP to stand-by server on failure.
---

### 📬 Message / Processing/ Task Queue (Producer-Consumer problem)
Used for asynchronous (multi-thread) communication between producers and consumers. It gives better performance as producer/ consumer works independently, loosely coupled. Example: Kafka (High performance data pipelines), RabbitMQ, AWS SQS.
> **Workflow:** Producer sends task → Queue stores it and assigns to server → If delayed, reassigns to next available server
---

### 📂 Databases
1. **SQL DB (like MySQL, PostgreSQL):** is for structured data with RDBMS ensuring ACID properties, faster updates, and handling complex queries.

2. **NoSQL DB (like MongoDB, DynamoDB, Cassandra):** is for distributed, unstructured data, offers dynamic schema with faster insertions and retrievals.

3. **Graph DB (like Neo4j):** is for interconnected data. Example: social networks, recommendations, fraud link analysis.

4. **Time-Series DB (like TimescaleDB):** is for time-stamped data. Example: Metrics, Monitoring and IOT sensor data.

5. **Blob/File Storage (like Amazon S3, Google Cloud Storage):** is for storing large binary objects like images, videos, documents. The file is uploaded/ persisted to the storage system and provides a URL for access.

6. **Search-optimized DB (like ElasticSearch, OpenSearch):** is designed for fast full-text search using inverted indexes that map terms to documents. Text is tokenized and stemmed so variations like "running" and "runs" are matched as "run".

> **Database Choice:** SQL for relationships & transactions, NoSQL for scale & speed. Example: Question-answer form where questions can be MCQ or string answers, can be stored in NoSQL. If SQL, then can store image link instead of image itself.
---

### 🧬 Database Management Techniques:
**Indexing:** Fast data retrieval technique using key-value mappings on frequently accessed fields. (e.g. indexing city and date to quickly find events in London on December 25th).

**Replication:** Copies data to multiple servers (improves availability but not latency or throughput)

**Sharding:** Splits database to improve performance.
* **Horizontal Sharding:** Split by rows (e.g., A-D, E-H)
* **Vertical Sharding:** Split by columns (e.g., user profiles, transactions)

> Sharding is difficult to implement practically, so if database is not that large then other mechanism like indexing is better solution.

In case if one of the shards fails, then we can use **Master-Slave Architecture** where Master (most updated copy) handles write operations, and Slaves sync with master for serving read operations. A good single point failure tolerance. If master fails, slaves chooses one master among themselves.

---
### ⚡ Caching
Stores frequently accessed data for fast retrieval, reducing database load and improving performance. May require sync logic for cache. *Example: Redis.*

**Types**
* **In-Memory Cache:** Per server cache, for faster data access.
* **Global Cache:** Shared across servers, more consistent.

**Cache Policies:** Decides when to load/evict data from cache to ensure it has most relevant data, like **LRU** (Least Recently Used data), **LFU** (Least Frequently Used data), **TTL** (time to live i.e. auto-expire after a fixed time).

**Write Strategies:**
* **Write-Through Cache:** Sync cache and DB parallely, improves consistency.
* **Write-Back Cache:** Update cache first and DB while evicting cache, improves performance.


### 🌍 Content Delivery Network (CDN)
A distributed cache, serving content from locations closest to users, reducing latency. *Example: Cloudflare, Akamai, Amazon CloudFront*
✅ Combination of **Blob Storage + CDN** is a good approach to get low latency + high availability
---

## 🌐 Some common cases:

#### [Error Handling] / [Handling cascading failures (ServiceA (working) → ServiceB (fails))]
Resilient systems prevents cascading failures using timeouts/ TTL, retries with backoff, circuit breakers, and clear HTTP error codes, enabling fail-fast and graceful degradation.

When a downstream service fails, systems follow a fail-fast and recover pattern i.e. ServiceA enforces strict timeouts to avoid blocking, opens the circuit breaker to stop repeated failures, responds gracefully (often by accepting requests asynchronously via queues), and automatically resumes traffic once health checks confirm recovery.


#### Logging, Monitoring, Auditing, Observability/ Traceability
For each request add a unique request-ID at the API Gateway for distributed tracing, Structured JSON for centralized logging using proper log levels, auditing for data analysis and track system health using metrics like latency, error rate, throughput and resource usage. This ensures that when a failure occurs, we move from Detection to Root Cause in seconds.


#### Performance Optimization / Low Latency
At high scale (e.g., 1M+ requests/sec), a single system cannot handle the load. Low Latency is achieved by minimizing network overhead (using techniques like DNS, browser caching, CDNs, horizontal scaling, and message queues) and reducing database overhead (using shards, indexing, batch processing, and processing non-critical tasks asynchronously).


#### What Happens When You Enter a URL in the Browser
When a URL is entered, the browser first resolves the domain via DNS, then establishes a TCP connection and secures it with TLS. It sends an HTTP request to the server, receives the response, and finally renders the page by parsing HTML, CSS, and JavaScript.


#### Ensuring Security 
Use an API Gateway as a single entry point to enforce rate limiting, IP whitelisting, and traffic control. Isolate infrastructure with VPCs and private subnets. Secure identity via authentication, authorization, and JWTs. Protect data with TLS for encryption in transit, service-to-service auth, and secret management (AWS KMS, HashiCorp Vault) to avoid hardcoding and enable key/credential rotation. At the application layer, validate inputs, scan for vulnerabilities, and log errors.


#### Handling large files
Use web-sockets for real-time file sync. 

Upload/ download the file from blob storage to backend server then to client is slow. Optimized solution is to use pre-signed url to directly upload/ download from blob storage reducing latency and avoid duplicate uploads.

Large files (e.g., 100 MB) are split into smaller chunks (e.g., 5 MB) and uploaded using S3 Multipart Upload via pre-signed url, enabling resumable uploads, chunk-level retries, and real-time progress tracking so users can see upload status and resume after failures.

#### 🛠️ Handling Concurrency
Use DB locks, synchronized blocks and transaction control `COMMIT` and `ROLLBACK` (i.e. commit changes only after process is completed).
In real world systems, we use two-phase reservation pattern with TTL (Time to Live). A seat is first Held for a short time of ~10 min (soft lock), then Booked after payment; if payment fails or times out, the TTL auto-releases the seat back to Available.

---
## 🗃️ Terminology:
* 📏 **Units:** 1 KB = 1000 Bytes, 1 MB = 1000 KB, 1 GB = 1000 MB, 1 TB = 1000 GB
* 🔀 **Load Balancers (e.g. AWS Elastic Load Balancer, NGINX):** Balance/ Routes incoming traffic among servers through DNS using routing algorithms like Hashing, Consistent Hashing, Least Connections (route to server having least number of requests), Round Robin (task is executed for given time quantum)
* 🌐 **DNS Server (Domain Name System):** Translates domain names i.e. WWW into IP addresses.
* ⏱️ **Latency (DNS time+ Request time):** Time to respond to a request by the server.
* 📈 **TPS (Throughput Per Second):** Number of requests handled per second by the system for knowing network bandwidth and scalability.
* 🔒 **Rate Limiting:** Limits requests per client in given time window (like 100 reqs/min). Example: Redis (count number of requests in server), AWS API Gateway (builtin rate limiting)
* 🖥️ Server Types: **Application Server** (Handles HTTP requests) and **Database Server** (Manages data storage)
* 📊 Data Processing Types: **Batch Processing** (Process entire sets of data like, billing all accounts) and **Stream Processing** (Process real-time data flows like online games)
* ⚙️ **Retry Mechanism (Fault Tolerance method):** Automatically reattempts failed tasks (API calls, DB queries, message processing) to handle temporary issues. Retries are limited (e.g., 3–5 times) using **_Fixed Delay_** (constant gap) or **_Exponential Backoff_** (1s → 2s → 4s). If all retries fail, the message moves to a **Dead Letter Queue (DLQ)** which stores failed messages for later inspection or manual fix.
* **Circuit Breaker:** Prevents a failing service from being called repeatedly, protecting the system from cascading failures.
* 📊 **Metrics Monitoring:** Tracks system health via latency, error rate, throughput and resource usage. Tools: Prometheus, Grafana, CloudWatch.

* **Hashing:** Mapping keys and values into the hash table using hash function (map xth request at server of `index = x % n`)
* **Consistent Hashing:** Distributes data across servers on a virtual hash ring, with minimal reorganization (as automatically routes to next server in the ring if a node fails), and More consistent (as ensures same request maps to the same server). Requires strong hash function, otherwise may have uneven data distribution


* **Stateful Systems** saves status and session info, and have low latency. Like, online shopping cart.
* **Stateless Systems** serves response back as per given state, and is resilient to server failure (as doesn't store session info). Like games.


* **Synchronous:** (Single thread) One task at a time, blocking
* **Asynchronous:** Multiple tasks in parallel, non-blocking mechanism. `Request → Accepted message sent to user → pushed to message queue → Processing in background`


* **Critical Task:** Runs synchronously for correctness. Example: Assigning a driver before ride confirms.
* **Non-Critical Task:** Runs asynchronously and monitored via metrics. Example: Push ride confirm notification while updating ride history.

* **Monolith:** A single codebase containing all modules. Easy to build and deploy initially, but harder to scale and modify as the system grows. *Example: Simple services like authentication or notifications.*
* **Microservices:** A system composed of independent services, each responsible for a specific function. Easier to scale, deploy, and maintain in large systems. *Example: A food delivery app with separate services for orders, payments, delivery, and users.*


* **Docker:** A platform that uses a Dockerfile to build images, which serve as blueprints for creating lightweight, portable containers that run applications (code+ libraries+ environment) consistently across environments.
* **Kubernetes:** Manages containerized applications (like docker) across clusters through APIs.
* **Google Cloud Platform (GCP):** Provides on-demand services for compute, storage, networking, databases, and AI/ML, enabling you to build, deploy, and scale applications.


* **Node:** A single machine running database
* **Cluster:** Collection of nodes with related data
* **DataCenter:** Grouping of nodes based on geographical area


* 🔑 **Authentication:** Verifies user identity, like using OAuth, JWT (JSON Web Tokens, are stateless token passed in headers).
* 🔓 **Authorization:** Determines what user can do, like role-based access control. Example: Access for rider vs driver vs admin could be different.


* **TCP/IP (Transmission control protocol):** Provides ordered deliver of data from user to server (e.g., HTTP).
* **TLS (Transport Layer Security):** Encrypts data in transit and authenticates endpoints, ensuring only trusted services communicate securely (e.g., HTTPS).


* **HTTP (Hypertext transfer protocol):** Used for requesting receiving web responses (like HTML).
* **HTTP Method:** GET (read), POST (create), PUT (replace), PATCH (partial update), DELETE (remove)
* **HTTP Status Code:** 200 (OK), 201 (Created), 400 (Bad request), 401 (Unauthorized), 403 (Forbidden), 404 (Not found), 429 (Rate limited), 500 (Server error)


* **Computer Network**: Devices connected to share resources.
* **IP Address (Internet Protocol):** Assigns unique addresses to devices so data can be routed correctly across networks. Types as IPv4 (32-bit, e.g., `192.168.1.1`), IPv6 (128-bit, e.g., `2001:db8::1`)
* **Virtual Private Cloud (VPC) and Private Subnet:** VPC creates a private network, and private subnets keep sensitive services isolated from the internet.
* **IP Whitelisting:** Only pre-approved IP addresses are allowed to access the server.

