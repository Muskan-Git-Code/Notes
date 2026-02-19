# 📘 DBMS (Database Management System)

### 📌 Basics
- **Data**: Raw facts and figures.
- **Information**: Processed data with meaning.
- **File**: Collection of similar types of information.
- **Database**: Organized collection of related data.
- **DBMS**: Software managing organized data with characteristics like non-redundancy (duplication), consistency, multi-user access, and ACID properties.
---

### 🔐 ACID Properties
| Property     | Description                                                            |
|--------------|------------------------------------------------------------------------|
| **Atomicity** | All or none of a transaction executes                                  |
| **Consistency** | DB remains valid before and after transaction                          |
| **Isolation** | Multiple transactions run without interference                         |
| **Durability** | Changes of a successful transaction, persist even after system failure |

---

## 🧷 Locks and Concurrency
🔐 **Lock:** Maintains data consistency.
* **Transaction Lock:** Ensure consistency within a database.
* **Distributed Lock (like zookeeper):** Ensure coordinate access to shared resources across multiple servers.

🔁 **Deadlock:** Set of processes waiting for each other’s resources.

### Conditions for Deadlock:
If all below 4 condition satisfy then deadlock may occur.
1. **Mutual Exclusion:** Only one process can use a resource at a time.
2. **Hold and Wait:**  A process is holding one resource and waiting for another. It can lead to starvation (i.e. lower-priority tasks get jammed as higher priority task keep on executing).
3. **No Preemption:** If a process is holding a resource then another process can't preempt/ interfare in-between.
4. **Circular Wait:** Processes are waiting in circular chain (like p1 waiting for p2, p2 waiting for p3, p3 waiting for p1).


### Deadlock Handling
* **Ostrich Approach/ Ignoring the deadlock:** Ostrich behaviour to stick head in sand and pretend there is no problem. Sometimes when computer window hangs, we simply reboot to get it working again.
* **Detect & Recover:** Resource scheduler keep track of resources allocated for deadlock detection. And then being corrected by terminating process or resource preemption (allocating resource to another process).
* **Prevent:** Break one of 4 deadlock conditions
* **Avoid:** 
  * **Banker’s Algorithm:** Allocates resources only if all processes can complete their execution without causing a deadlock
  * **Resource Allocation Graph:** A deadlock detection method, i.e. directed graph between processes, having resource as weights on each edge
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
## 📊 SQL Query Examples

| Query                                                                                                                      | Purpose                                                                |
|----------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| `SELECT MAX(salary) FROM emp;`                                                                                             | Find the **highest salary** in the `emp` table                         |
| `SELECT MAX(salary), deptno FROM emp GROUP BY deptno;`                                                                     | Find the **highest salary per department**                             |
| `SELECT COUNT(*) FROM emp;`                                                                                                | Count **total number of employees**                                    |
| `SELECT COUNT(*), deptno FROM emp GROUP BY deptno HAVING COUNT(*) > 5;`                                                    | Count employees per department, only show if **more than 5 employees** |
| `SELECT ename FROM emp WHERE ename LIKE '%M%';`                                                                            | Find employees whose **name contains 'M'**                             |
| `SELECT * FROM emp LIMIT 4;`                                                                                               | Fetch the **first 4 rows**                                             |
| `SELECT salary FROM emp ORDER BY salary DESC LIMIT 3, 1;`                                                                  | Fetch the **4th highest salary**                                       |
| `(SELECT salary FROM emp ORDER BY salary DESC LIMIT 3, 1) UNION (SELECT salary FROM emp ORDER BY salary DESC LIMIT 5, 1);` | Fetch the **4th and 6th highest salaries**                             |
| `SELECT utc_to_date(created_t), EXTRACT(YEAR FROM created_t) AS "Year" FROM emp;`                                          | Convert UTC timestamp to **date** and extract **year**                 |
| `INSERT INTO TargetTable (Column1, Column2, Column3) SELECT Column1, Column2, Column3 FROM SourceTable`                    | Copy record(s) into another table                                      |

---
## 🧱 Definitions:

* **Database Models:** Define how data is logically structured, stored, and accessed. It can be:
    * **Hierarchical Model**: Tree-like structure with parent-child relationships. Example: Organization structure (manager, team-lead, developer).
    * **Network Model**: Graph structure. Example: University (different students enroll in multiple courses).
    * **Relational Model (RDBMS)**: Stores data in tables. Example: Banking system with tables like Customers, Accounts, and Transactions.
    * **Entity-Relationship (ER) Model**: High-level conceptual view of entities, attributes, and relationships. Example: diagram showing Student and Course entities connected via Enrollment relationship.


* **Weak Entity:** Entity which cannot be uniquely identified by its own attributes.
* **Strong Entity:** Entity which can be uniquely identified by its own attributes.


* **Integrity Constraints:** Rules to maintain accuracy and consistency of data. It can be:
    * **Entity Integrity:** Primary key cannot be NULL.
    * **Referential Integrity:** Foreign key must be NULL or match a primary key in another table.


* **Transaction:** Way of grouping multiple operations together in single atomic operation using commit and rollback.


* **Functional Dependency (FD):** Relationship between attributes, denoted as X → Y, where X determines Y. It can be:
    * **Trivial FD:** If Y is a subset of X (Y ⊆ X).
    * **Non-trivial FD:** If Y is not a subset of X (Y ⊄ X).


* **Normalization:** Process of organizing data to reduce redundancy/ duplication, inconsistency and insert/ update/ delete anomaly. It includes:
    * **1NF:** No multi-valued or composite attributes.
    * **2NF:** No partial dependency (attributes depend on whole primary key).
    * **3NF:** No transitive dependency (non-key attributes depend only on primary key).
    * **4NF/BCNF:** No multi-valued dependency and LHS of every FD is super key.
    * **5NF:** No join dependency; tables can be decomposed without loss of data.
* **Denormalization:** Adding redundancy to get rid of complex data.


* **Keys in DBMS:** Unique identifiers for records in a table. It includes:
    * **Super Key:** Uniquely identifies tuples.
    * **Candidate Key:** Minimal super key.
    * **Prime Attribute:** Attributes used in making candidate key.
    * **Primary Key (PK):** Chosen one of the candidate keys.
    * **Alternate Key:** Candidate key not chosen as PK.
    * **Foreign Key (FK):** References PK in another table.


* **SQL Constraints:** Rules for data during table creation/ modification. It includes:
    * **NOT NULL:** Disallow NULL values.
    * **UNIQUE:** No duplicate values (NULLs allowed).
    * **PRIMARY KEY:** UNIQUE + NOT NULL.
    * **FOREIGN KEY:** Reference another table.
    * **CHECK:** Enforce custom conditions.
    * **DEFAULT:** Provide default value.


* **SQL Clauses:** Used to filter, group, sort, and limit query results. It includes:
    * **WHERE:** Row filtering before grouping.
    * **GROUP BY:** Aggregate grouping.
    * **HAVING:** Conditions on aggregates.
    * **ORDER BY:** Sorting.
    * **LIMIT, TOP, ROWNUM:** Row restriction.
    * **LIKE, IN, EXISTS:** Pattern & condition checking.


* **SQL Functions:** Built-in functions for different operations like:
    * **Mathematical:** `ABS(-6)`, `POWER(3,2)`, `ROUND(3.9)`, `SQRT(25)`, `MOD(15, 7)` {remainder}, `FLOOR(12.8)` {12}, `CEIL(12.8)` {13}, `GREATEST(salary, bonus)` {works among multiple expressions for single row}, `LEAST(4,5)`.
    * **String:** `LENGTH("Hello")`, `UPPER("hi")` {HI}, `LOWER("HI")` {hi}, `INITCAP("hi")` {Hi}, `SUBSTR(str, pos, len)`, `ASCII('a')`, `CONCAT("hel", "lo")`.
    * **Aggregate:** `COUNT()`, `MAX(salary)` {finding max in single column}, `MIN()`, `AVG()`, `SUM()`.

---

---

# 🖥️ Operating System (OS), UNIX Commands & Networking
 
**🧠 Operating System (OS):** System software that manages computer hardware and helps application programs to run. Example: DOS, WINDOWS, LINUX/UNIX.


* **Program**: A passive set of instructions.
* **Process**: A program in execution (active).


**⏳ Scheduling:** System software which schedules execution of all processes. It can be: 
1. **Long-Term Scheduler (Job Scheduler)**: Selects jobs from disk to load into memory.
2. **Mid-Term Scheduler (Swapper)**: Swaps processes in/out of main memory.
3. **Short-Term Scheduler (CPU Scheduler)**: Chooses process for CPU execution.


**🎯 Scheduling Criteria:** Metrics to evaluate scheduling algorithms, like ↑ CPU Utilization, ↑ high Throughput, ↓ Turnaround Time, ↓ Waiting Time, ↓ Response Time.


**📊 Scheduling Algorithms:** Methods to schedule processes for execution. It can be:
* **FCFS**: First Come First Serve
* **SJF**: Shortest Job First
* **SRTF**: Shortest Remaining Time First (Preemptive version of SJF)
* **Round Robin**: Equal time quantum to processes
* **Priority Scheduling**: Higher priority executes first


**💾 Memory Management:** OS component that manages memory allocation and deallocation for processes. It includes techniques like:
* **Paging:** Memory divided into fixed-size pages.
* **Segmentation:** Memory divided into variable-size segments.
* **Virtual Memory:** Allows large programs to run by loading only required pages or segments into main memory on demand, using:
  * **Demand Paging:** Loads pages only when needed.
  * **Demand Segmentation:** Loads segments only when needed.


**📌 Memory Allocation Strategies:** Methods to allocate memory to processes. It can be:
* **First Fit**: First big enough partition
* **Next Fit**: Next available partition
* **Best Fit**: Smallest fitting partition
* **Worst Fit**: Largest free partition


**Page Replacement Algorithms:** Methods to decide which page to replace when a new page needs to be loaded into memory. It can be:
* **FIFO**: First In First Out
* **OPT**: Optimal Page Replacement
* **LRU**: Least Recently Used
* **LFU**: Least Frequently Used
* **MFU**: Most Frequently Used pages will be used for replacement


**Context Switch:** Saving state of currently running process and loading state of next process to run.
**Dispatcher:** Gives control of CPU to the process selected by short-term scheduler.
 
**Swapping:** Moving processes between main memory and disk to manage memory efficiently.
**Thrashing:** Excessive swapping leading to performance degradation.

**Fragmentation:** Inefficient use of memory due to scattered free space (external) or wasted space within allocated memory (internal). It can be solved by techniques like paging and best fit allocation.
**Page Fault:** Accessing a page which is not available in main memory (RAM).
**Segmentation Fault:** Accessing memory that the process is not allowed to access.
---

---

# 💻 UNIX/Linux Basic Commands

**👥 User Info:**
* `who` → All logged-in users
* `who am i` → Current user info
 
**📂 File & Directory:**
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

**🔒 Permissions:**
* `chmod 765 f1` → Change permissions (i.e. give read/write/execute permission to user/group/owner; like 765= 111 110 101 = rwx rw- r-x)

**📊 File Operations:**
* `vi f1` → Shows file in edit mode.
    * `i` → insert
    * `/` → search
    * `n` → next search
    * `:set nu` → set line number
    * `:wq!` → save
    * `:q!` → come out of file without saving changes.

**🔧 Misc:**
* `date` → Current date & time
* `ps -ef` → Process status
* `kill -9 <port>` → Kill process at particullar port
* `clear` → Clear screen
---

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

### Example of Ollama API to showcase how LLM works:
```
curl --location 'http://localhost:11434/api/chat' \
--header 'Accept: */*' \
--header 'Content-Type: application/json' \
--data '{
    "model": "gemma3:4b",
    "messages": [
        {
            "role": "system",
            "content": "You are an intelligent senior staff software engineer. Task: To help users with their java codes. Outcomes - Your response should be accurate, no failures."
        },
        {
          "role": "user",
          "content": "Hi"
        },
        {
          "role": "assistant",
          "content": "Hello! How can I help you today?"
        },
        {
          "role": "user",
          "content": "My name is Lelouch. whats yours"
        },
        {
          "role": "assistant",
          "content": "That'\''s a nice name, Lelouch. I don'\''t have a personal name."
        },
        {
          "role": "user",
          "content": "how is the weather today?"
        },
        {
          "role": "assistant",
          "content": "Lelouch, Today it’s sunny ~ 29 °C (85 °F)."
        }
    ],
    "stream": false
}'
```
