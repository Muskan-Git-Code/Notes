# 📘 DBMS (Database Management System)

## 📌 Basics
- **Data**: Raw facts and figures.
- **Information**: Processed data with meaning.
- **File**: Collection of similar types of information.
- **Database**: Organized collection of related data.
- **DBMS**: Software used to manage databases.

## ✅ Characteristics of DBMS
- Easy to understand
- Non-redundant (minimizes data duplication)
- Data Consistency
- Multi-user access
- More secure
- Follows **ACID** Properties

## ❌ Disadvantages of DBMS
- Requires high-speed processor and large memory
- Backup and recovery may be slow for large DBs

---

## 🔐 ACID Properties
| Property     | Description                                                            |
|--------------|------------------------------------------------------------------------|
| **Atomicity** | All or none of a transaction executes                                  |
| **Consistency** | DB remains valid before and after transaction                          |
| **Isolation** | Multiple transactions run without interference                         |
| **Durability** | Changes of a successful transaction, persist even after system failure |

---

## 🧱 Database Models
Define how data is logically structured, stored, and accessed.

| Model | Description                                                              | Example                                                                               |
|-------|--------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Hierarchical Model** | Organizes data in a tree-like structure with parent-child relationships. | Organization structure (manager, team-lead, developer).                               |
| **Network Model** | Graph structure                                                          | University (students enroll in multiple courses and each course has many students).   |
| **Relational Model (RDBMS)** | Stores data in tables                                    | Banking system with tables like Customers, Accounts, and Transactions with keys linking them. |
| **Entity-Relationship (ER) Model** | High-level conceptual view of entities, attributes, and relationships.   | diagram showing Student and Course entities connected via Enrollment relationship. |

> **Weak Entity:** Entity which cannot be uniquely identified by its own attributes

### ➡️ Types of Relationships:
- One-to-One (1:1): One attribute can have one records in another table
- One-to-Many (1:N)
- Many-to-One (M:1)
- Many-to-Many (M:N)

---

## 📐 Relational Algebra

| Operator     | Symbol | Description                                       | SQL Query                                                                                                                                                 |
|--------------|------|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Union**        | ∪    | A or B                                            | `SELECT empname FROM emp1 UNION SELECT empname FROM emp2;`                                                                                                |
| **Intersection** | ∩    | A and B                                           |                                                                                                                                                           |
| **Set Difference** | − | Tuples in one table but not in another            | `SELECT empname FROM emp1 WHERE empname NOT IN (SELECT empname FROM emp2);`                                                                               |
| **Division** | ÷ | Find entries related to a common attribute        | `SELECT COUNT(*), deptno FROM emp GROUP BY deptno HAVING COUNT(*)>5;`                                                         |
| **Natural Join** | ⋈ | Join on all common attributes                     | `SELECT * FROM emp NATURAL JOIN dept;`                                                                                                                    |
| **Inner Join (Equi Join)** | ⋈ | Joins on an equality condition         | `SELECT * FROM emp INNER JOIN dept ON emp.dno = dept.dno;`                                                                                                |
| **Left Outer Join** | ⟕ | All rows from left table + matched from right     | `SELECT * FROM emp LEFT OUTER JOIN dept ON emp.dno = dept.dno;`                                                                                           |
| **Right Outer Join** | ⟖ | All rows from right table + matched from left     | `SELECT * FROM emp RIGHT OUTER JOIN dept ON emp.dno = dept.dno;`                                                                                          |
| **Full Outer Join** | ⟗ | All rows from both sides with NULL where no match | `SELECT * FROM emp FULL OUTER JOIN dept ON emp.dno = dept.dno;`  |
| **Self Join** | - | Joining table with itself                         | `SELECT A.empname, B.empname FROM emp A, emp B WHERE A.manager_id = B.emp_id;`                                                                            |

---

## 🗝️ Keys in DBMS
| Key Type             | Description                             |
|----------------------|-----------------------------------------|
| **Super Key**        | Uniquely identifies tuples              |
| **Candidate Key**    | Minimal super key                       |
| **Prime Attribute**  | Attributes used in making candidate key |
| **Primary Key (PK)** | Chosen one of the candidate key         |
| **Alternate Key**    | Candidate key not chosen as PK          |
| **Foreign Key (FK)** | References PK in another table          |

---

## 🔒 Integrity Constraints
- **Entity Integrity**: PK cannot be NULL
- **Referential Integrity**: FK must by NULL or PK of another table

---

## 🧠 Functional Dependency (FD)
Denotes relation between two tables, as `X → Y`
- **Trivial**: If Y is subset of X. Y ⊆ X
- **Non-trivial**: Y ⊄ X

---

## 📏 Normalization
Process of organizing data in database to avoid data redundancy/ duplication, inconsistency, and insert/ update/ delete anomaly. 

| Form                      | Rule                                                                                        |
|---------------------------|---------------------------------------------------------------------------------------------|
| 1NF                       | No multi-valued/composite attributes                                                        |
| 2NF                       | No partial dependency (any attribute which is determined by any one part of PK)             |
| 3NF                       | No transitive dependency (i.e. A->B, B->C, C->A)                                            |
| 4NF/ BCNF (Boyce-codd NF) | No multi-valued dependency and LHS of every FD is super key                                 |
| 5NF                       | No join dependency; lossless decomposition (Table R decomposed to R1, R2; then R1 U R2 = R) |

> **Denormalization**: Adding redundancy to get rid of complex data.

---

## 🔢 SQL Functions

### 🧮 Mathematical
`ABS(-6)`, `POWER(3,2)`, `ROUND(3.9)`, `SQRT(25)`, `MOD(15, 7) {remainder}`, `FLOOR(12.8) {12}`, `CEIL(12.8) {13}`, `GREATEST(4,5,7)`, `LEAST(4,5)`

### 🔤 String
`LENGTH("Hello")`, `UPPER("hi") {HI}`, `LOWER("HI") {hi}`, `INITCAP("hi") {Hi}`, `SUBSTR(str, pos, len)`, `ASCII('a')`, `CONCAT("hel", "lo")`

### 📊 Aggregate
`COUNT()`, `MAX()`, `MIN()`, `AVG()`, `SUM()`

> **GREATEST(salary, bonus):** works among multiple expressions for single row
> 
> **MAX(salary):** Work for finding max in single column.

---

## 📌 SQL Clauses
| Clause | Use                           |
|--------|-------------------------------|
| `WHERE` | Row filtering before grouping |
| `GROUP BY` | Aggregate grouping            |
| `HAVING` | Conditions on aggregates      |
| `ORDER BY` | Sorting                       |
| `LIMIT`, `TOP`, `ROWNUM` | Row restriction               |
| `LIKE`, `IN`, `EXISTS` | Pattern & condition checking  |

> Use `HAVING` instead of `WHERE` with aggregate functions.

---

## 🔐 SQL Constraints
Specify rules for data during table creation/ modification; ensuring accuracy and reliability of data.

| Constraint | Description |
|------------|-------------|
| **NOT NULL** | Disallow NULL |
| **UNIQUE** | No duplicates (NULLs allowed) |
| **PRIMARY KEY** | UNIQUE + NOT NULL |
| **FOREIGN KEY** | Reference another table |
| **CHECK** | Enforce custom conditions |
| **DEFAULT** | Provide default value |
---

## 🔤 DBMS Languages
| Category | Command | Purpose | Example |
|----------|---------|---------|---------|
| 🏗️ **DDL** (Data Definition) | `CREATE` | Create new object | `CREATE TABLE Students(id INT, name VARCHAR(50));` |
| | `DROP` | Delete object | `DROP TABLE Students;` |
| | `ALTER` | Modify structure | `ALTER TABLE Students ADD age INT;` |
| | `TRUNCATE` | Remove all rows, keep structure | `TRUNCATE TABLE Students;` |
| | `RENAME` | Rename object | `ALTER TABLE Students RENAME TO Pupils;` |
| 🧮 **DML** (Data Manipulation) | `INSERT` | Add data | `INSERT INTO Students VALUES (1, 'Muskan', 22);` |
| | `UPDATE` | Modify data | `UPDATE Students SET age = 23 WHERE id = 1;` |
| | `DELETE` | Remove data | `DELETE FROM Students WHERE id = 1;` |
| 🔍 **DQL** (Data Query) | `SELECT` | Retrieve data | `SELECT name, age FROM Students WHERE age > 20;` |
| 🔐 **DCL** (Data Control) | `GRANT` | Give permissions | `GRANT SELECT ON Students TO user1;` |
| | `REVOKE` | Remove permissions | `REVOKE SELECT ON Students FROM user1;` |
| 🔄 **TCL** (Transaction Control) | `COMMIT` | Save changes | `COMMIT;` |
| | `ROLLBACK` | Undo changes | `ROLLBACK;` |
| | `SAVEPOINT` | Mark point in transaction | `SAVEPOINT sp1;` |


---

## 🧷 Locks and Concurrency

### 🔐 Lock
- Maintains data consistency.

### 🔁 Deadlock
- Set of processes waiting for each other’s resources.

#### Conditions for Deadlock:
If all below 4 condition satisfy then deadlock may occur. 
1. **Mutual Exclusion:** Only one process can use a resource at a time.
2. **Hold and Wait:**  A process is holding one resource and waiting for another. It can lead to starvation (i.e. lower-priority tasks get jammed as higher priority task keep on executing).
3. **No Preemption:** If a process is holding a resource then another process can't preempt/ interfare in-between.
4. **Circular Wait:** Processes are waiting in circular chain (like p1 waiting for p2, p2 waiting for p3, p3 waiting for p1).

### 🔄 Deadlock Handling
| Method                                      | Description                                                                                                                                                                                   |
|---------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Ostrich Approach/ Ignoring the deadlock** | Ostrich behaviour to stick head in sand and pretend there is no problem. Sometimes when computer window hangs, we simply reboot to get it working again.                                      |
| **Detect & Recover**                        | Resource scheduler keep track of resources allocated for deadlock detection. And then being corrected by terminating process or resource preemption (allocating resource to another process). |
| **Prevent**                                 | Break one of 4 conditions                                                                                                                                                                     |
| **Avoid**                                   | Banker’s Algorithm (Allocates resources only if all processes can complete their execution without causing a deadlock)                                                                        |

> **Resource Allocation Graph:** A deadlock detection method, i.e. directed graph between processes, having resource as weights on each edge
---

## 🛠️ Handling Concurrency Use Cases
- **Same seat booking**: Use DB Locking (Shared and exclusive lock), Sync keyword or Mutual Exclusion
- **Bank transfer failure**: Use `COMMIT` and `ROLLBACK` i.e. commit changes only after process is completed.
- **Replication failure (while replicating data in D1, D2, D3):** Commit after update in all nodes
- **Server-failure/ services-stopped issues after deployment:** Use load balancer to redirect traffic to healthy server. In case if all servers are down, then rollback to previous stable version.
---

## 📊 SQL Query Examples

| Query | Purpose |
|-------|---------|
| `SELECT MAX(salary) FROM emp;` | Find the **highest salary** in the `emp` table |
| `SELECT MAX(salary), deptno FROM emp GROUP BY deptno;` | Find the **highest salary per department** |
| `SELECT COUNT(*) FROM emp;` | Count **total number of employees** |
| `SELECT COUNT(*), deptno FROM emp GROUP BY deptno HAVING COUNT(*) > 5;` | Count employees per department, only show if **more than 5 employees** |
| `SELECT ename FROM emp WHERE ename LIKE '%M%';` | Find employees whose **name contains 'M'** |
| `SELECT * FROM emp LIMIT 4;` | Fetch the **first 4 rows** |
| `SELECT salary FROM emp ORDER BY salary DESC LIMIT 3, 1;` | Fetch the **4th highest salary** |
| `(SELECT salary FROM emp ORDER BY salary DESC LIMIT 3, 1) UNION (SELECT salary FROM emp ORDER BY salary DESC LIMIT 5, 1);` | Fetch the **4th and 6th highest salaries** |
| `SELECT utc_to_date(created_t), EXTRACT(YEAR FROM created_t) AS "Year" FROM emp;` | Convert UTC timestamp to **date** and extract **year** |
| `SELECT * INTO tempdb..Claims FROM Claims WHERE id = 3;` | Copy record(s) into another table (here into `tempdb..Claims`) |
---

# 🖥️ Operating System (OS), UNIX Commands & Networking

## 🧠 Operating System (OS)
System software that manages computer hardware and helps application programs to run. Example: DOS, WINDOWS, LINUX/UNIX.

### ⚙️ Booting Process:
OS is loaded by BIOS (Basic Input Output System) to initialize hardware during booting process (loading OS from secondary storage (HDD/SSD Disk) to main memory (RAM)).

### 📋 Program vs Process:
* **Program**: A passive set of instructions.
* **Process**: A program in execution (active).

---

## ⏳ Scheduling in OS
System software which schedules execution of all processes.

### 🧭 Scheduler Types:
1. **Long-Term Scheduler (Job Scheduler)**: Selects jobs from disk to load into memory.
2. **Mid-Term Scheduler (Swapper)**: Swaps processes in/out of main memory.
3. **Short-Term Scheduler (CPU Scheduler)**: Chooses process for CPU execution.

### 🧱 Dispatcher:
Gives control of the CPU to the process selected by the short-term scheduler.

### 🎯 Scheduling Criteria:
* High CPU Utilization
* High Throughput
* Low Turnaround Time
* Low Waiting Time
* Low Response Time

### 📊 Scheduling Algorithms:
* **FCFS**: First Come First Serve
* **SJF**: Shortest Job First
* **SRTF**: Shortest Remaining Time First (Preemptive version of SJF)
* **Round Robin**: Equal time quantum to processes
* **Priority Scheduling**: Higher priority executes first

---

## 💾 Memory Management

### 📌 Allocation Strategies:
* **First Fit**: First big enough partition
* **Next Fit**: Next available partition
* **Best Fit**: Smallest fitting partition
* **Worst Fit**: Largest free partition

### 🧱 Memory Techniques:
* **Paging**: Memory divided into fixed-size pages.
* **Segmentation**: Memory divided into variable-size segments.

### 🌐 Virtual Memory:
Allows large programs to run by loading only required pages or segments into main memory on demand, using:
* **Demand Paging:** Loads pages only when needed.
* **Demand Segmentation:** Loads segments only when needed.

### Pagination
Display data page wise.

### ❗ Fragmentation:
Refers to inefficient use of memory, where available memory is fragmented (i.e. broken into smaller non-contiguous blocks).
* **Internal**: Wasted space within allocated memory (Solved by Best Fit)
* **External**: Unusable scattered free space (Solved by Paging)

### ⚠️ Page Fault:
Accessing a page which is not available in main memory (RAM).

### 🔁 Thrashing:
Occurs when the system spends more time swapping pages between RAM and disk than executing tasks, due to frequent page faults.

### 🔄 Page Replacement Algorithms:
* **FIFO**: First In First Out
* **OPT**: Optimal Page Replacement
* **LRU**: Least Recently Used
* **LFU**: Least Frequently Used
* **MFU**: Most Frequently Used pages will be used for replacement

---

# 💻 UNIX/Linux Basic Commands

### 👥 User Info:
* `who` → All logged-in users
* `who am i` → Current user info

### 📂 File & Directory:
* `touch f1` → Create file f1
* `cat f1 > f2` → Create file f2 with content of f1
* `cat f2` → View content of f2
* `cp/mv f2 f1` → Copy/Move file
* `rm f2` → Delete file
* `mkdir d1`, `rmdir d1`, `pwd` → Directory handling i.e. create/ remove/ present-working directory
* `cd ..` → Previous directory
* `cd folder2` → Next directory
* `head -100 f1`, `tail -100 f1` → View top/bottom 100 lines
* `grep word` → Search text in files
* `ls -ltr` → List all files with details and time in reverse sorted order

### 🔒 Permissions:
* `chmod 765 f1` → Change permissions (i.e. give read/write/execute permission to user/group/owner; like 765= 111 110 101 = rwx rw- r-x)

### 📊 File Operations:
* `vi f1` → Shows file in edit mode.
    * `i` → insert
    * `/` → search
    * `n` → next search
    * `:set nu` → set line number
    * `:wq!` → save
    * `:q!` → come out of file without saving changes.

### 🔧 Misc:
* `date` → Current date & time
* `ps -ef` → Process status
* `kill -9 <port>` → Kill process at particullar port
* `clear` → Clear screen

---

# 🌐 Data Communication & Networking

## 🌍 Basics:
* **Data Communication**: Exchange of data between devices.
* **Computer Network**: Devices connected to share resources.

## 📡 Protocols:
* **HTTP (Hypertext transfer protocol):** Used for requesting receiving web responses (like HTML). 
* **TCP/IP (Transmission control protocol):** Tells how data is transmitted over internet. 
* **FTP (File Transfer Protocol):** Transferring files from one server to another.
* **DNS (Domain Name System):** Translates domain names i.e. WWW into IP addresses.

## 🌐 IP Addressing:
* **IPv4**: 32-bit, e.g., `192.168.1.1`
* **IPv6**: 128-bit, e.g., `2001:db8::1`

## 🏗️ Network Models:

### 🖧 OSI Model (7 Layers):
1. **Application** – User services (HTTP, FTP)
2. **Presentation** – Data translation/encryption
3. **Session** – Manage sessions
4. **Transport** – Reliable data transfer (TCP/UDP)
5. **Network** – Logical addressing (IP)
6. **Data Link** – Physical addressing i.e. reading, writing data
7. **Physical** – Hardware, cabling, signals

### 🌐 TCP/IP Model:
* Application → Transport → Network → Network Interface (Data Link, Physical) layer. 

---

# 🤖 Generative AI
- **Artificial Intelligence (Think):** Intelligence in machine. Example: Chatbot helping you to order.
- **Machine Learning (Learn):** AI that learns from data to make predictions. Example: you order often on weekends.
  - ML Types:
    - **Supervised Learning:** Learns from labeled data, (Like classification, regression models) Example: Spam/ NotSpam, cat/ dog.
    - **Unsupervised Learning:** Learns from unlabelled data, by finding patterns. Example: frequent buyers on a website.
- **GenAI (Generates):** AI that generates new content. Example: Your favorite pizza is just a click away.

### Different ML Models:
- **Classification Model:** Predicts categories, like image is of cat or dog
- **Regression Model:** Predicts continuous values, like price of house.

### Different GenAI Models:
- 🧠 **Large Language Models (Text):** LLMs are trained on huge text data to understand and generate text. Example: ChatGPT, Google Gemini, Claude
- 🎨 **Image Models:** Example: Google Imagen3
- 🎵 **Audio Models:** Example: Google MusicLM
- 🎥 **Video Models:** Example: Google Veo

### 🏗️ LLM Model Roles & State
- **Roles** →
  - *System Prompt* → Defines behavior (“You are a helpful coding assistant”), hidden from user.
  - *User Prompt* → Input request.
  - *Assistant Prompt* → Model’s reply.
  - *Tool Role* → External actions (APIs, DB queries).

- **Stateless Nature** →
  - Each request is independent; no built‑in memory.
  - “Memory” is simulated by re‑sending **conversation history** in every request.

> - **Ollama** → Run open‑source LLMs locally (like gpt-4, gemini); data stays secure, faster inference, no external sharing.
---

- **When to use AI?** → Repetitive tasks, creative suggestions, large data summarization, generating context.
- **When not to use AI?** → Sensitive/confidential data, require critical judgments.
- - **When to use ML over GenAI** → When tasks involve structured data, predictions, or pattern detection.
- **How do you stay up to date on Al improvements and innovations?** → I stay updated by exploring new AI releases, following community discussions and webinars, and using AI tools and copilots in daily development.


- **What’s prompt engineering?** → Designing inputs/ prompts to get accurate and useful responses from LLMs.
- **How do you leverage prompts?** → I craft clear, context-rich prompts for accurate results. Example: while generating Java code, I specify the system prompt ("You are a senior SDE"), language ("expert in java"), problem ("Solve this.."), expected outcome ("accurate result, followed best practices, no failures"), constraints ("1 million data streaming"), and example ("..") to get precise, reliable solutions.
---
