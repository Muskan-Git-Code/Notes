### 🧱 **Representation of System:**

---
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

---

### 📌 *CAP Theorem / Brewer’s Theorem*

Any distributed system can have only *2 things out of*:
* *Consistency*: All clients see the same data at the same time.
* *Availability*: The system is available even if some nodes/ server fail.
* *Partition/ Fault Tolerance*: The system continues operating despite network failures.

> In scenario of partial tolerance, we usually *prefer availability* and are okay with *eventual consistency* (data eventually reflects latest value), if high availability required and there are less write operations on the system.

---
### 📏 *Units*
```
1 KB = 1000 Bytes
1 MB = 1000 KB
1 GB = 1000 MB
1 TB = 1000 GB
```

---
### 🚀 *Scaling*: 
Handling large amount of requests at once and responds quickly with the latest information. 

#### *Vertical Scaling (Scale-Up)*
* Increase scale of system by adding more power (i.e. adding better CPU processors, increasing RAM) to a *single machine*.
* Single point of failure, but is Cost-effective for small systems.

#### *Horizontal Scaling (Scale-Out)*
* With increase in demand of business when its not possible to have such large databases, we prefer horizontal scaling i.e. add *more machines*
* Requires *load balancer*. And have better fault tolerance and scalability.


#### 🔀 *Load Balancers*
* Balance/ Routes incoming traffic among servers, using routing algorithms like Hashing, Consistent Hashing, Least Connections (route to server having least number of requests), Round Robin (task is executed for given time quantum)
* Maintain server registry (whether available or not) & health check

---

### 🔄 Hashing & Consistent Hashing
**Hashing:** Mapping keys and values into the hash table using hash function (map xth request at server of `index = x % n`)

**Consistent Hashing:**
* Distributes data across servers on a virtual hash ring.
* Minimal reorganization (as automatically routes to next server in the ring if a node fails), and More consistent (as ensures same request maps to the same server)
* Requires strong hash function, otherwise may have uneven data distribution

---

### 🧠 *Stateful vs Stateless Systems*
* **Stateful Systems** saves status and session info, and have low latency.
* **StateLess Systems** serves response back as per given state, and is resilient to server failure (as doesn't store session info).

---
### ⏱️ *Latency*
* Time to respond to a request by the server. 
* Latency= DNS time+ Request time.
* We can reduce latency by reducing DNS time (caching memory into browser/ OS), or request time (i.e. handling database in more optimized way like using file storage (S3/ CDN), or database techniques (like indexing, replication, sharding, etc)).

---

### 🌐 *DNS Server* (Domain Name System)
> Translates domain names i.e. WWW into IP addresses.

---

### 📈 *TPS (Throughput Per Second)*
> Number of requests handled per second by the system, for knowing network bandwidth and scalability.  
> Knowing if system is write-heavy or read-heavy. 
> Example: We have 1 Million of I/O requests coming per second. One system can’t handle these many requests, so we should have some caching, scaling or message queue enabled for handling these many requests.

---

### 📊 *Data Processing*
* *Batch Processing*: Process entire sets of data (e.g., billing all accounts)
* *Stream Processing*: Process real-time data flows (e.g., online games)

---

### 📂 SQL vs NoSQL Database
**SQL** is used for structured data with RDBMS ensuring **ACID properties**, faster updates, and handling complex queries.

**NoSQL** is for distributed, unstructured data (like document-oriented, graph-based). It supports dynamic schema and offers faster insertions and retrievals.

> If database looks same for all values then SQL, else NoSQL. Example: Question-answer form where questions can be MCQ or string answers, can be stored in NoSQL.

---

### 🗃️ Blob/ File Storage
Used to store binary large objects like images, videos, and documents. Avoid storing bulky files, store file links instead.
* **Examples:** Amazon S3 (AWS Cloud Storage), Microsoft Azure, Google Cloud Storage, CDN (Content Delivery Network)

---

**S3 (AWS Cloud Storage)**: Offers scalability, availability, security, and performance through s3 buckets in diff regions.

**CDN (Content Delivery Network)**: A geographically distributed proxy servers used to provide high availability and performance, like video/image streaming, caching.

✅ Combination of **S3 + CDN** is a good approach to get low latency + high availability

---

### 📂 Cassandra
* A distributed NoSQL database, which offers high **write throughput**, **scalability** and **availability**
* It uses **CQL (Cassandra Query Language)**, whose syntax is similar to MySQL.


> **Terminology:**
> * **Node:** A single machine running Cassandra
> * **Cluster:** Collection of nodes with related data
> * **DataCenter:** Grouping of nodes based on geographical area

---

### 📬 Message / Processing/ Task Queue
Used for asynchronous (multi-thread) communication between producers and consumers. It gives better performance as producer/ consumer works independently, loosely coupled. Example: Kafka (High performance data pipelines).

**Workflow:** Producer sends task --> Queue stores it and assigns to server --> If delay, reassigns to next available server

---

### 🏗️ Monolith vs Microservices

Monolith: Single code base, simpler for smaller systems, but hard to change, and test.
Microservices: Independent services, suitable for large-scale systems, easy to deploy, modify and maintain.

---

### 🧬 Indexing, Replication & Sharding

**Indexing:** Fast data retrieval technique using key-value mappings.

**Replication:** Copies data to multiple servers (improves availability but not latency or throughput)

**Sharding:** Splits database to improve performance.
* **Horizontal Sharding:** Split by rows (e.g., A-D, E-H)
* **Vertical Sharding:** Split by columns (e.g., user profiles, transactions)

> Sharding is difficult to implement practically, so if database is not that large then other mechanism like indexing is better solution.

In case if one of the shards fails, then we can use **Master-Slave Architecture** where Master (most updated copy) handles write operations, and Slaves sync with master for serving read operations. A good single point failure tolerance. If master fails, slaves chooses one master among themselves. 

---



---

### ⚡ Caching
Stores frequently accessed data for quick retrieval. It reduces DB load, and improves speed. Though may require sync logic for cache. Example: Redis (NoSQL database used for caching).

**Types:**
* **In-Memory Cache:** Per server cache, for faster data access.
* **Global Cache:** Shared cache, more accurate and consistent.


**Cache Policies:** Decides when to load/evict data from cache to ensure it has most relevant data.
* **LRU (Least Recently Used)**: Having only recently used data.
* **LFU (Least Frequently Used):** Having only frequently used data.


**Write Strategies:**
* **Write-Through Cache:** Sync cache and DB parallely, improves consistency. 
* **Write-Back Cache:** Update cache first and DB while evicting cache, improves performance.

---

### 🔍 Filtering & Pagination
In real system when there is lot of data, it ensures system is not overburdened.
* **Filtering:** Filtering data by location, rating, etc. Example: `GET /products?category=electronics&brand=sony,samsung`
* **Pagination:** Return 10–20 items per API call using `offset/limit` in sql. Example of API: `GET /products?page=2&size=10`
* **Sorting:** Let client sort data based on a parameter. Example: `GET /products?sort=-price,name` (i.e. sort by price in desc and name by asc)

---

### 🖥️ Server Types
* **Application Server:** Handles HTTP requests
* **Database Server:** Manages data storage

--- 

### 🧩 Scaling Systems
To handle high request volumes:
```text
Client Request through browser → DNS (for knowing IP of website) → Message Queue (for buffer time, notify when consumer is available) → Load Balancer (routes traffic) → Application Server → Database (Handles large data requests) → Caching (faster data access)
```

---

## ☢️ Single Point of Failure
Just like any other computer, if our **main server (i.e. Load Balancer)** dies, the system fails. 
* To avoid this we can swap the main-server by a **stand-by routing server**. For swapping either change the IP address in DNS registry, or assign a static IP to main server and point that static IP to stand-by server on failure.

---

## 🔒 Rate Limiting
Limits requests per client in given time window (e.g. 100 reqs/min). Like Redis (count number of requests in server), AWS API Gateway (builtin rate limiting)

---

## 🔐 Authentication & Authorization

### 🔑 Authentication
Verifies user identity, like using OAuth, JWT (JSON Web Tokens, are stateless token passed in headers).

### 🔓 Authorization
Determines what user can do, like role-based access control. Example: Access for rider vs driver vs admin could be different.

---







