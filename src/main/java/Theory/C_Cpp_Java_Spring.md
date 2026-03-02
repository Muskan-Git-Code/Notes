## CODING (C/C++/JAVA) 
- **C:** High-level, general-purpose, procedural-oriented programming language. Used for developing portable applications, compilers and JVMs.
- **C++:** Extended version of C with object-oriented programming (OOP). Used for operating software development and embedded systems.
- **JAVA:** Object-oriented, platform-independent, dynamic programming language. Used in web applications, Android development, enterprise systems, and cloud computing.
---

#### POP vs OOP
- **Procedural-Oriented programming language (POP):** Divides program into smaller functions, and allows data to move freely into system.
- **Object-Oriented programming language (OOP):** Divides program into objects, and is more secure.
---

### Some C++ Concepts
* **Pointers:** Stores memory address of another variable.
  * **Null pointer:** Pointer containing nothing i.e. initialized to `nullptr`
  * **Dangling pointer:** Points to deleted memory location
  * **Void pointer:** Can store address of any data type. Ex: void *ptr;


* **Operator Overloading:** Gives custom meaning to existing operator. Ex: Changing ++ to mean i+2 instead of i+1.
* **Friend Function:** Not a class member, but can access private/protected members, using the friend keyword.


### Function Argument Passing
```cpp
void f1(int a, int b);         // Call by value (Changes made are local to called function)
void f2(int* a, int* b);       // Call by address (pointer is passed, and made permanent changes on address)
void f3(int& a, int& b);       // Call by reference (address is passed, and made permanent changes on address)
```

---

### Major Features in Java 8
**1. Functional Interface:** Interface with exactly one abstract method. It can have default and static methods. `@FunctionalInterface` annotation prevents multiple abstract methods from being accidentally added.

**2. Lambda Expressions:** Anonymous function with no name and identifier, using only ( -> ) symbol. It is compact and readable. Example: `public void fn(String s1, String s2){ return s1.equals(s2); }`, can also be written as `(s1,s2)-> s1.equals(s2);`

**3. Method References:** Refer method of functional interface to an existing method, using scope resolution operator (::). `ClassName::functionName`, Example: `Demo::fn1 `

**4. forEach Method:** Iterates over collection of items, similar to for loop.

**5. Optional Class:** Helps avoid null pointer exception. Example:
```java
import java.util.Optional;
void fn() {
  Optional<User> user = getUser();
  if (user.isPresent()){    User u= user.get();     return Optional.of(u); }
  return Optional.empty();
}
```
**6. Try-With-Resources:** Automatically closes files/ resources at end, without explicitly file.close() statement in finally block.
```java
void fn() {
    try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
        System.out.println(br.readLine());
}}
```
---

### SOLID Principles
Help in writing clean, maintainable, and scalable code.

| Principle             | Description                                                                                                                  | Example                                                                                                                                                    |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Single Responsibility | Every class/function should have only one responsibility, so that any change won't impact other functionalities.             | Instead of one class for `Bill` function, have `finalAmount()`, `printBill()`, `saveBill()` classes (Separate class for saving, printing, computing bill). |
| Open-Closed           | Classes should be open for extension but closed for modification. Allows adding new features without changing existing code. | saveBill() method, extended to save bill as pdf also.                                                                                                      |
| Liskov Substitution   | Parent class should be replaceable by its subclasses, promoting correct inheritance and design.                              | If `Shape` is a base class, then `Square` and `Rectangle` must behave consistently when used in place of `Shape`.                                          |
|
| Interface Segregation | Segregate interfaces as per the requirements of program. Keeping interfaces relevant to specific needs.                      | Instead of interface `Restaurant()`, we can make interface `Waiter() {takeOrder(), serveFood()}` and `Chef() {prepareFood()}`                              |
| Dependency Inversion  | Class should depend on interfaces, not concrete classes. As interfaces won't affect dependent code                           | `class Demo{ User user; Demo(User user){	this.user= user; }}`    	`User user= new Admin() // OR new Client(); in main method`                              |

---

### **Language Processing Systems:**
* **C++:**
> **Source Code** (.cpp) → **Preprocessor** (Processes code before compilation) → **Compiler** (Converts source code to assembly code) → **Assembler** (Changes assembly code to object code i.e. .obj file) → **Linker** (Links object file & library files i.e. .exe file) → **Loader** (Loads into main memory) → **Main Memory** (Program executes)

**Java:**
> **Code** (written using JDK - Java Development Kit which contains development tools) → **javac** (Compiles .java source code to .class bytecode) → **JAVA** (Interprets bytecode) → **JVM** (Java Virtual Machine executes bytecode as part of JRE - Java Runtime Environment using ClassLoader which loads required classes/interfaces into JVM)
---

## OOP Concepts
**1. Class and Objects:** Object is a real-world entity with unique properties, and class is a collection of similiar objects having common attributes. Example: Car class having objects as color, wheel, engine.

**2. Encapsulation (Data Biding):** Binding data and code together into a single unit (class). It can be **Early/ Static/ Compile time binding** (Binds at compile time) and **Late/ Dynamic/ Runtime Binding** (Binds at runtime).

**3. Abstraction:** Hiding internal details and showing only relevant information to the user. It can be achieved using abstract classes and interfaces.

**4. Inheritance:** Creating new class from already existing class. Promotes code reusability and polymorphism.
* Types:
    * **Single:** `Parent → child class` i.e. A class inherits from a parent class
    * **Multilevel:** `Parent1 → Parent2 (child of parent1) → Child class`
    * **Hierarchical:** `Parent → Child1, Child2`
    * **Hybrid:** Combination of multiple inheritance
    * **Multiple:** not supported by java `Parent1, Parent2 → Child`
* Example: 
    ```java
    class Animal {
      void sound() { System.out.println("Some sound"); }
    }
    class Dog extends Animal {
      void sound() { System.out.println("Bark"); }
    }
    
    class Demo{
    public static void main(String args[]){
      Animal a= new Dog();	 //First search in given class, then to base class
      a.eat();	a.sound();	//eating  //barking
    }}
    ```

**5. Polymorphism:** Greek term which means ability to take many forms. It can be **Compile-time/ method overloading** (Same method name with different parameters) and **Runtime/ method overriding** (Same method name in parent and child class).

**6. Message Passing** Object communicate via methods. Example: `emp.getDetails();    // Object emp calls method getDetails`

---

### 🔍 Abstract Class vs Interface
* **Interface:** Declared with interface keyword, used to get abstraction and multiple inheritance. All variables are public static final by default, and methods must be declared as public.
```java
interface Animal {  void makeSound(); }
class Cat implements Animal {
    public void makeSound(){   System.out.println("Meow"); }
}
class Demo{
  public static void main(String args[]){
    Animal a=new Cat();     a.makeSound();  //Meow
}}
```

* **Abstract Class:** Declared with abstract keyword, used to get abstraction. It can have local variable and require anonymous class during instantiation.
```java
abstract class Animal {  abstract void makeSound(); }
class Cat extends Animal {
    void makeSound() {   System.out.println("Meow"); }
}
class Demo{
  public static void main(String args[]){
    Animal a=new Cat(){};     a.makeSound();  //Meow    // Annonymous class {}
}}
```
---

## Exception Handling
A mechanism used to handle errors. It can be:
* **Checked Exceptions:** Detected at compile time, like `ClassNotFoundException`, `IOException`, `SQLException`
* **Unchecked Exceptions:** Detected at runtime, like `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`

**Global Exception Handling:** Managing exceptions across entire application using custom exception classes.

**Exception Handling Keywords:**
* `try`: Main code block, always followed by catch block to test errors.
* `catch`: Handle the exception.
* `finally`: Executes after `try`/`catch`, regardless of exception. It is not executed if code contains `System.exit()` or if there is a system crash (i.e. StackOverflow/ OutOfMemory exception).
* `throw`: Used to explicitly throw an exception.
* `throws`: Declares exceptions in method signature.

> **Exception Hierarchy:** Throwable class → Check for Exception (Checked and Unchecked Exception) → Throw error (StackOverFlowError, VirtualMachineError, OutOfMemoryException)

### `try`, `catch`, `finally` Example
```java
public class Demo {
    public static void main(String[] args) {
        try{    int a[]= new int[5];   a[5]= 30/0; } 
        catch (Exception e){    System.out.println("Exception occurs: "+ e); } 
        finally{    System.out.println("finally block is always executed"); }
        System.out.println("rest of the code");
    }
}
```

### `throw` and `throws` Example
```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchAgeException extends Exception {
    public NoSuchAgeException(String str) {  super(str); }
}
public class Demo {
    void validateAge(int age) throws Exception {
        if(age<18){     throw new NoSuchAgeException("not valid"); }
        else{   System.out.println("welcome to vote"); }
    }
}
```
---

## Definitions:
* **Package:** A namespace that organizes classes and interfaces. It can be called by importing `import pack.A;` or by calling another package `pack.A obj= new pack.A();`.

* **Constructor:** Special method with same name as class name having no return type and invoked automatically on object creation. It can be Default (No args) and Parameterized Constructor.
* **Destructor (Garbage Collector in java):** Cleans up memory, and invokes when object goes out of scope.
* **Copy Constructor:** Performs object copying. It can be: 
  * **Shallow copy:** Copy reference not actual data i.e. Change in one location will affect others
  * **Deep copy:** Create new objects.
* **Constructor/Destructor Hierarchy:** Base class constructor → Derived class constructor → Derived class destructor → Base class destructor


* **Garbage Collection (Memory Management):** Reclaims unused memory automatically by nullifying the object and giving that reference to another object. It improves memory efficiency by prevents direct memory access and reducing memory leaks.

* **Finalize Method:** Perform cleanup tasks before garbage collection.

* **Platform Independence:** JAVA compiles to bytecode, which can run on any platform with JVM. However, this flexibility makes Java slower than Cpp, as Cpp has no intermediate bytecode.

* **Memory Allocation:** It can be:
    * **Heap Memory:** Global memory, used throughout program. Deleted by Garbage Collection. Can throw OutOfMemoryError if full.
    * **Stack Memory:** Local, fast memory used in function calls. Can throw StackOverflowError if full.

* **Memory Model:** Defines rules for how memory is read/written across threads. Java provides `volatile` keyword to ensure visibility of changes across threads, and `synchronized` blocks to control access to shared resources.


* **Process:** A program in execution.
* **Thread:** Lightweight process that shares the same address space.
* **Multithreading:** Allows executing multiple threads concurrently (side by side). Used in games, animations, etc., for better CPU utilization. 
* **Thread Lifecycle States:** New → Runnable → Running → Blocked → Terminated
* **Thread Synchronization:** Controls access to shared resources to prevent conflicts. Techniques: **Mutual Exclusion** (using synchronized keyword), **Inter-thread Communication** (using wait(), notify(), notifyAll())
 

* **Serialization:** Conversion of Object to Byte stream/ json. Example: `@ResponseBody User user   // Object to byte stream`
* **Deserialization:** Conversion of Byte stream to Object. Example: `@RequestBody User user    // byte stream to Object (Deserialization)`


* **ObjectMapper:** Handles JSON **serialization** *(i.e. objectMapper.writeValueAsString(User))* and **deserialization** *(i.e. objectMapper.readValue(str, className.class))*.
* **For adding logger:** Logs data (warn, info, debug, error). Example: Logger logger = LoggerFactory.getLogger(className.class);


* **Java  Database Connectivity (JDBC):** API to connect and execute queries with databases for permanent storage. Steps: Register driver class → Create connection, Statement object → Execute query → Close connection


* **Data Types:** Defines type of data a variable can hold. It can be **Primitive/Builtin/ Interinsic** (int, float, boolean, char) and **Non-primitive/ Derived/ Reference** (arrays, classes, interfaces).

* **Typecasting:** Converting a variable from one datatype to another. Example: `int a=5;`    `float f= (float)a;`


* **Variable Scope:** Defines accessibility of variable. It can be: 
  * **Local:** within method
  * **Instance/ Global:** within class outside method, not static. Doesn't exist in java.
  * **Static/ Class:** within class outside method with static keyword. Retains value throughout the program.
  * **Final:** Used to maintain constant value; as it cannot be reassigned, overridden, or inherited.


* **Static Method:** Can be called directly by the class name. Example: `Math.max()`, `Collections.sort()`.


* **Access Modifiers/ Visibility Specifier:** Specifies scope of variable or method. It can be: 
  * **private:** Within same class
  * **protected:** Within same class and subclass
  * **default:** Within same package (By default specifier in java)
  * **public:** Everywhere accessible


* **Wrapper Classes:** Used to treat primitives as objects (Boxing), and vise-versa (UnBoxing). It is helpful in collections. Example:
    ```java
    int a = 10;
    Integer i = Integer.valueOf(a); // Boxing
    int b = i.intValue(); // Unboxing
    ```

* **Tight Coupling:** Classes having strong dependency on each other, which can lead to issues in maintenance and scalability. 
* **Loose Coupling:** Classes have minimal dependencies on each other, which promotes flexibility and easier maintenance.


* **JAVA Syntax:** `public` (Accessible from anywhere) `static` (No object needed to run) `void` (returns nothing) `main(String[] args)` (cmd-line arguments). It is Java's entry point. Java programs can compile without `main` but fail at runtime.


* **Fail-Fast Iterator:** Throws `ConcurrentModificationException` on structural modification. Examples: `ArrayList`, `HashMap`
* **Fail-Safe/ Weakly-Consistent Iterator:** Works on a copy, hence no exception. Examples: `ConcurrentHashMap` (Designed to be accessed by multiple threads for reading, writing data).


* **Type Safety:** Ensures memory safety by preventing invalid memory access or type mismatches.
* **Thread Safety:** Ensures correct program behavior in multithreaded environments. It can be achieved by using immutable objects, locks, ConcurrentHashMap, and synchronized methods.

* **String Pool:** A collection of strings in JAVA's heap memory.
    - Strings are **immutable** (cannot change value once assigned).
    - Mutable strings are StringBuffer (Thread-safe) and StringBuilder (More efficient, not thread-safe).
    - It can be created through string literal `String s1 = "Hello";` or using new keyword `String s2 = new String("Hello");`.

* **Immutable classes:** `final` class, having `private`, `final` data members, and only getter methods (no setters)


* **instanceof Operator:** Used to check if an object is an instance of a specific class. `if (user instanceof Emp) { ... }`
* **Enum:** User-defined constants. `enum Status { New("New Order"), completed, cancelled };`
* **super():** Calls immediate parent class constructor
* **this:** Refers to current class object
* **@Override Annotation:** Safety check which indicates method intentionally overrides superclass method.
* **Field class:** Represents class member variable (field) at runtime. `Field field= Product.class.getDeclaredField("price");   field.getName();`
* **Pageable:** Returns abstract pagination information. `Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);`
* **Record:** Final immutable class. `record Pair(int x, int y){}`
---

## Git Commands
```bash
git clone <repo-url>
git init
git checkout -b <branch>
git pull origin main
git add <file>
git commit -m "message"
git push origin <branch>
git stash, git stash pop  // for temporarily saving changes in a branch
```
---

## Spring Framework
* **Spring:** An open-source framework designed to create java production ready applications in efficient way with minimal configuration, embedded web servers, and starter dependencies.


* **Spring MVC:** Based on **Model-View-Controller (MVC)** architecture, Primarily used for developing web applications.
  * **hibernate.cfg.xml**: Config for DB details.


* **Spring Boot:** Built on top of Spring, widely used for developing APIs in easier way and lesser development time.
  * **pom.xml:** Contains configuration and dependency details. 

---

⭐ **Spring Boot Application Flow:** `@RestController` → `@Service` → `@Repository` → `@Entity`

⭐ **Inversion of Control (IoC):** Transfer control of objects to a framework.

⭐ **Idempotency:** Making the same API call multiple times has the same effect as making it once, with no extra side effects.

---

### Spring Boot Starter Dependencies
* **spring-boot-starter-web:** For web apps
* **spring-boot-starter-data-jpa:** For JPA DB access
* **spring-boot-starter-actuator:** For production monitoring
* **spring-boot-starter-test:** Includes all neccessary test libraries like JUnit, Mockito, AssertJ, SpringTest, etc.
* `@SpringBootApplication Annotation`: Automatically configures all dependencies added in project. It includes `@Configuration` (Defines bean method), `@ComponentScan` (spring component scanning), `@EnableAutoConfiguration` (Enables auto configs)

---

### Dependency Injection (DI)
Injecting necessary dependencies through IOC.
Example: Using `@Autowired` annotation for injecting service class to controller.

#### 📌 Types of DI
- **Constructor Injection** → Dependencies provided via constructor (✅ preferred: immutable, ensures non-null).
- **Setter Injection** → Dependencies set via setter methods (good for optional).
- **Field Injection** → Dependencies injected directly into fields (⚠️ mutable, not recommended).

```java
// via constructor
@Component
class Client {
  private final ServiceA service;
  @Autowired    public Client(ServiceA service){  this.service = service; }
}

// via setter
@Component
class Client {
  private ServiceA service;
  @Autowired    public void setService(ServiceA service){     this.service = service; }
}

// via field
@Component
class Client {
  @Autowired    private ServiceA service;
}


// Without DI (Violation Example)
// Here the class **creates its own dependency** → tightly coupled, hard to test, violates DI.
@Component
class Client {
  private ServiceA service = new ServiceA();   // ❌ creates dependency itself
  public void call(){  service.serve(); }
}
```
---

### Client-Server Communication medium:
1. **SOAP API (Simple Object Access Protocol):** Protocol-based communication using XML, strict standards, and higher overhead compared to REST.
2. **REST API (Representational State Transfer):** Bi-Directional communication using standard HTTP methods.
3. **Server-Sent Events (SSE):** Handles real-time server-to-client communication. Example: live scores, notifications
4. **WebSockets:** Handles real-time bidirectional communication using `@ServerEndpoint` annotation. Example: Chat or live collaboration.

> **WebSocket Manager:** Manages all active websocket connections between client and backend servers.

---
⭐ **Best Practices for API Design:** Use proper HTTP methods, meaningful endpoints, dependency injection, handle errors, support pagination, rate limiting, secure with HTTPS/auth, document, caching, auditing, and ensure idempotency.

---
### 🔍 Filtering & Pagination
In real system when there is lot of data, it ensures system is not overburdened.
* **Filtering:** Filtering data by location, rating, etc. Example: `GET /products?category=electronics&brand=sony,samsung`
* **Pagination:** Return 10–20 items per API call using `offset/limit` in sql. Example of API: `GET /products?page=2&size=10`
* **Sorting:** Let client sort data based on a parameter. Example: `GET /products?sort=-price,name` (i.e. sort by price in desc and name by asc)
---

### Rest API Annotations
* `@Bean`: Manual Bean creation.
* `@Component`: General purpose bean, base for `@Service`, `@Repository`, `@Controller`
* `@Autowired`: Automatically wires beans.
* `@Embedded`: Embeds one entity/ table into another.


* `@Entity`: Stores table details along with getter/setters
* `@Repository`: Extends JPA for CRUD operations
* `@Service`: Implements business logic
* `@RestController`: Handles HTTP requests through GET/POST mapping. It combines `@Controller` (marks class as MVC model) and `@ResponseBody` (Returns json response using ResponseEntity class)
* `@GetMapping/ @PostMapping/ @PutMapping/ @DeleteMapping/ @PatchMapping`: Handles HTTP get/ post/ put (update whole record)/ delete/ patch (update partial record) method.
* `@PathVariable`: Pass Value (/{id}) in API URL
* `@RequestParam`: Pass query parameter (?id=1) in API URL


* For calling any api, 
  * `HttpEntity`: Obtain HTTP content as inputStream, and is passed through `@RequestBody`, `@RequestHeader`.
  * `ResponseEntity`: Get result of HTTP response (by methods like  getStatusCode(), getBody(), etc.) in form of `@ResponseBody`.
  * `@ResponseStatus`: Custom HTTP status code for API response.
  * `RestTemplate`: Used to consume data from external APIs and give response.

---

### Exception Handling
* `@ControllerAdvice`: Globally handling exceptions
* `@ExceptionHandler`: Handles specific exception
* Can also use try-catch block.
---

### Caching
Spring Boot supports in-built caching — the first API call hits the DB, while subsequent calls return data from cache, avoiding redundant DB access.
* `@EnableCaching`: Enables caching in spring boot application.
* `@Cacheable`: Caches the result of method execution. 
* `@CacheEvict`: Removes cache entry.
* `@CachePut`: Updates cache without interfering with method execution.
* Example: `@Cacheable(cacheNames = "ProductsCache", key = "#id")`
---

### DB Configuration
* **In-memory**: Add `h2database` dependency
* **Outside-memory like MySQL setup**: Add `mysql-connector-java` dependency in `pom.xml`. And configure DB in `application.properties`

---

### Testing
* **Unit Test**: Tests individual modules
* **Integration Test**: Tests combined modules

* `@SpringBootTest(classes = ApiApplication.class)`: Loads context of the main ApiApplication class (with `@SpringBootApplication` annotation) for testing.
* `@Before`, `@BeforeEach`: Run before each method
* `@BeforeClass`,`@BeforeAll`: Runs once before executing all test methods
* `when().thenReturn()`, `when().thenThrow()`: Mock to return specific values.
* `Assertions.assertEquals(expected, actual)`: Checks if both equal.
* `@Test`:	Marks a method as a test case.

---

## For creating new Spring project: 
* Check if java installed: `java --version`
* Check if maven installed: `maven --version`
* Generate sample spring project through: https://start.spring.io/ 
* Add DB.
* Remember to add Exception handling (try-catch blocks, null checks) wherever required.

**Spring Boot Example:** Refer to SpringBootExample package. `pom.xml` updated with dependencies needed for this.

---
