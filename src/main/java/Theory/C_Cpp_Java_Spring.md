# JAVA NOTES

## CODING (C/C++/JAVA)
### C
* High-level, general-purpose, procedural-oriented programming language.
* Commonly used for developing portable applications, compilers and JVMs.

### C++
* Extended version of C with object-oriented programming (OOP).
* Commonly used for operating software development and embedded systems.

### JAVA
* Object-oriented, platform-independent, dynamic programming language.
* Used in web applications, Android development, enterprise systems, and cloud computing.

---

## POP vs OOP

### Procedural-Oriented programming language (POP):
Divides program into smaller functions, and allows data to move freely into system.

### Object-Oriented programming language (OOP):
Divides program into objects, and is more secure.

---

## Some C++ Concepts

* **Pointers:** Stores memory address of another variable.
  * **Null pointer:** Pointer containing nothing i.e. initialized to `nullptr`
  * **Dangling pointer:** Points to deleted memory location
  * **Void pointer:** Can store address of any data type. Ex: void *ptr;


* **Operator Overloading:** Gives custom meaning to existing operator. Ex: Changing ++ to mean i+2 instead of i+1.
* **Friend Function:** Not a class member, but can access private/protected members, using the friend keyword.


### Function Argument Passing
```cpp
void f1(int a, int b);         // Call by value (Changes made are local to called function)
void f2(int* a, int* b);       // Call by address (called by pointer, and made permanent changes on address)
void f3(int& a, int& b);       // Call by reference (address is passed, and made permanent changes on address)
```

---

## Major Features in Java 8

### 1. Functional Interface:
* Interface with exactly one abstract method.
* @FunctionalInterface annotation prevents multiple abstract methods from being accidentally added.

### 2. Lambda Expressions:
* Anonymous function with no name and identifier, using only ( -> ) symbol.
* Compact and readable
* Example: `public void fn(String s1, String s2){ return s1.equals(s2); }`, can also be written as `(s1,s2)-> s1.equals(s2);`

### 3. Method References:
* Refer method of functional interface to an existing method, using scope resolution operator (::).
* ClassName::functionName, Example: `Demo::fn1 `

### 4. forEach Method:
* Iterates over collection of items, similar to for loop.

### 5. Optional Class:
* Helps avoid null pointer exception.
* Example:

```java
import java.util.Optional;
void fn() {
  Optional<User> user = getUser();
  if (user.isPresent()){    User u= user.get();     return Optional.of(u); }
  return Optional.empty();
}
```

### 6. Try-With-Resources:
* Automatically closes files/ resources at end, without explicitly file.close() statement in finally block.
```java
void fn() {
    try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
        System.out.println(br.readLine());
}}
```

---

## Serialization in Java
* **Serialization:** Conversion of Object to Byte stream
* **Deserialization:** Conversion of Byte stream to Object
```
@RequestBody User user    // JSON to Object (Deserialization)
@ResponseBody User user   // Object to JSON (Serialization)
```

> **For adding logger:** Logs data (warn, info, debug, error). Example: Logger logger = LoggerFactory.getLogger(className.class);
> 
> **ObjectMapper:** Handles JSON **serialization** (java objects to json strings i.e. objectMapper.writeValueAsString(User)) and **deserialization** (json strings to java objects i.e. objectMapper.readValue(str, className.class)).

---

## Data Types
* **Primitive/ Builtin/ Interinsic:** int, float, boolean, char
* **Non-primitive/ Derived/ Reference:** arrays, classes, interfaces

> **Typecasting:** Converting a variable from one datatype to another. Example: `int a=5;`    `float f= (float)a;`

---

## Variable Types
* **Local:** Within a method
* **Instance/ Global:** Within class outside method, not static. Doesn't exist in java
* **Static/ Class:** Within class outside method with static keyword. Retains value throughout the program. 
* **Final:** Used to maintain constant value; as it cannot be reassigned, overridden, or inherited.


**Static Method:** Can be called directly by the class name. Though cannot access non-static methods directly (But via creating object). Example: `Math.max()`, `Collections.sort()`.
```java
class Demo {
    int val = 10;  // non-static variable
    public static void show() {
      Demo d = new Demo();
      System.out.println(d.val);    // accessing non-static member via object  // Output: 10
}}
```

---

## Access/ Visibility Specifier
Specifies scope of variable or method.

| Specifier | Scope                                              |
| --------- |----------------------------------------------------|
| private   | Within same class                                  |
| protected | Within same class and subclass                     |
| default   | Within same package (By default specifier in java) |
| public    | Everywhere accessible                              |

---

## Wrapper Classes and Boxing
Used to treat primitives as objects (helpful in collections, etc.).

| Primitive | Wrapper |
|-----------|---------|
| int       | Integer |
| char      | Character |
| boolean   | Boolean |

### Example
```java
int a = 10;
Integer i = Integer.valueOf(a); // Boxing
int b = i.intValue(); // Unboxing
```

---

## Language Processing Systems

### C++
> **Source Code** (.cpp) → **Preprocessor** (Processes code before compilation) → **Compiler** (Converts source code to assembly code) → **Assembler** (Changes assembly code to object code i.e. .obj file) → **Linker** (Links object file & library files i.e. .exe file) → **Loader** (Loads into main memory) → **Main Memory** (Program executes)

### Java
> **Code** (written using JDK - Java Development Kit which contains development tools) → **javac** (Compiles .java source code to .class bytecode) → **JAVA** (Interprets bytecode) → **JVM** (Java Virtual Machine executes bytecode as part of JRE - Java Runtime Environment) → **ClassLoader** (Loads required classes/interfaces into JVM)

> **Java is Platform Independent** as it compiles into bytecode, which is platform-independent. This bytecode can run on any platform with JVM. However, this flexibility makes Java slower than C++, as C++ has no intermediate bytecode.

---

## Java `main` Method
```java
public static void main(String[] args) { }
```
- `public`: Accessible from anywhere
- `static`: No object needed to run
- `void`: Returns nothing
- `main`: Entry point, method name
- `String[] args`: Command-line arguments

> Java programs can compile without `main` but fail at runtime.


---

## Garbage Collection (GC)
- **Automatic memory management** that reclaims unused memory, by nullifying the object and giving that reference to another object.
- Improves memory efficiency.
- In languages like C++, manual memory management is required, risking memory leaks if unused memory isn't re-used properly.

### Finalize Method
Perform cleanup tasks before garbage collection.

> Java implicitly uses reference instead of pointers, making it safer and more efficient. As pointers allow direct memory access, posing security and complexity risks.

---

## Memory Allocation

| Memory Type | Description                                                                                |
|-------------|--------------------------------------------------------------------------------------------|
| Heap Memory | Global memory, used throughout program. Deleted by GC. Can throw OutOfMemoryError if full. |
| Stack Memory | Local, fast memory used in function calls. Can throw StackOverflowError if full.           |

---

## String Pool:
A collections of strings in JAVA's heap memory.

- Strings are **immutable** (cannot change value once assigned).
- It can be created through string literal `String s1 = "Hello";` or using new keyword `String s2 = new String("Hello");`.

## Mutable Strings
StringBuffer (Thread-safe)
StringBuilder (More efficient, not thread-safe)

## Immutable Classes
Create with `final` class, having `private` and `final` data members, along with only getter methods (no setters)

---

# SOLID Principles
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

# OOP Concepts

## 1. Class and Objects
* **Object:** Real-world entity with unique properties.
* **Class:** Collection of similiar objects having common attributes. 
* Example: Car class having objects as color, wheel, engine.

## 2. Encapsulation (Data Biding)
* Wraps function call to its appropriate function definition, keeping internal details hidden from outside access.
* Can be achieved through private and public getter/setters.
* It can be **Early/ Static/ Compile time binding** (Wraps at compile time) and **Late/ Dynamic/ Runtime Binding** (Wraps at runtime).

## 3. Abstraction
* Show only relevant details, and hide internal implementation.
* Can be achieved using abstract classes and interfaces.
```java
abstract class Shape {
    abstract void draw(); // abstract method
}
```

## 4. Inheritance
* Creating new class from already existing class.
* Promotes code reusability and polymorphism.
* Types 
  * Single (`Parent → child class` i.e. A class inherits from a parent class)
  * Multilevel (`Parent1 → Parent2 (child of parent1) → Child class`)
  * Hierarchical (`Parent → Child1, Child2`)
  * Hybrid (Combination of multiple inheritance)
  * Multiple (not supported by java `Parent1, Parent2 → Child`)
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

## 5. Polymorphism
* Greek term which means ability to take many forms.
* Example: An animal can be dog or cat.
* It can be **Compile-time/ method overloading** (Same method name with different parameters) and **Runtime/ method overriding** (Same method name in parent and child class)

## 6. Message Passing
* Object communicate via methods.
* Example: `emp.getDetails();    // Object emp calls method getDetails`

---

## 🔍 Abstract Class vs Interface

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

* **Abstract Class:** Declared with abstract keyword, used to get abstraction. It can have local variable also. And require anonymous class during instantiation.
```java
interface Animal {  void makeSound(); }
abstract class Cat implements Animal {
    public void makeSound() {   System.out.println("Meow"); }
}
class Demo{
  public static void main(String args[]){
    Animal a=new Cat(){};     a.makeSound();  //Meow    // Annonymous class {}
}}
```

---

## Package
* Used to group related classes, interfaces, sub-packages.
* It can be done by importing `import pack.A;` or calling another package `pack.A obj= new pack.A();`

> **Default java packages** like `java.lang` and `java.util`.

---

## Constructor
* Special method invoked automatically on object creation; with same name as class and having no return type.
* It can be Default (No args) and Parameterized Constructor

## Destructor (Garbage Collector in java)
* Cleans up memory, and invokes when object goes out of scope.

## Copy Constructor
- Performs object copying.
- It can be 
  - Shallow copy: Copy reference not actual data. Change in one location will affect others.
  - Deep copy: Create new objects.

> Strings are immutable so copying them behaves like deep copy. It mainly affects derived datatype.

> **Constructor/Destructor Hierarchy:** Base class constructor → Derived class constructor → Derived class destructor → Base class destructor

---

## Some more Concepts
1. **instanceof Operator:** Used to check if an object is an instance of a specific class. `if (user instanceof Emp) { ... }`

2. **Enum:** User-defined constants. `enum Status { New("New Order"), completed, cancelled };`

3. **super():** Calls immediate parent class constructor

4. **this:** Refers to current class object

5. **@Override Annotation:** Safety check which indicates method intentionally overrides superclass method.

6. **Field class:** Represents class member variable (field) at runtime.
```java
// Get 'price' field from 'Product' class at runtime (even if it's private member)
Field field= Product.class.getDeclaredField("price");

// get field values from Object
Comparable f1= (Comparable)field.get(p1);   Comparable f2= (Comparable)field.get(p2);

// Compare fields without knowing datatype
int x= f1.compareTo(f2);
```

---

## Exception Handling
A mechanism used to handle errors.
It can be: 
* **Checked Exceptions**: Detected at compile time, like `ClassNotFoundException`, `IOException`, `SQLException`.
* **Unchecked Exceptions**: Detected at runtime, like `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`

> **Exception Hierarchy:** Throwable class → Check for Exception (Checked and Unchecked Exception) → Throw error (StackOverFlowError, VirtualMachineError, OutOfMemoryException)

### Exception Handling Keywords
* `try`: Main code block, always followed by catch block to test errors.
* `catch`: Handle the exception.
* `finally`: Executes after `try`/`catch`, regardless of exception.
* `throw`: Used to explicitly throw an exception.
* `throws`: Declares exceptions in method signature.

> `finally` block is **not executed** if code contains `System.exit()` or if there is a system crash (i.e. StackOverflow/ OutOfMemory exception).

### Exception Example
```java
public class Demo {
    public static void main(String[] args) {
        try {
            int a[]= new int[5];   a[5]= 30/0;  // ArithmeticException
        } catch (Exception e) {
            System.out.println("Exception occurs: "+ e);
        } finally {
            System.out.println("finally block is always executed");
        }
        System.out.println("rest of the code");
    }
}
```

### `throw` and `throws` Example
```java
public class Demo {
    static void validate(int age) throws Exception {
        if(age<18){     throw new ArithmeticException("not valid"); }
        else{   System.out.println("welcome to vote"); }
    }
    public static void main(String[] args) throws Exception {
        try {   validate(16); } // This will throw exception
        catch(Exception e){    System.out.println("Exception caught: " + e); }
    }
}
```

---

## Java Database Connectivity (JDBC)
JDBC is an API to connect and execute queries with databases for permanent storage.

#### Steps for Database Connectivity
Register the driver class → Create the connection, Statement object → Execute the query → Close the connection

---

## Multithreading in Java
Allows executing multiple threads (lightweight processes) concurrently (side by side). Used in games, animations, etc, for better CPU utilization.

### Thread Lifecycle States
1. **New**: Thread is created
2. **Runnable**: Ready to run
3. **Running**: Currently executing
4. **Blocked**: Waiting/suspended
5. **Terminated**: Execution completed

### Example
```java
class A extends Thread {
    public void run() { System.out.println("Thread runs..."); }
    public static void main(String args[]) {
        A obj = new A();    obj.start();
    }
}
```

---

## Synchronous vs Asynchronous
* **Synchronous:** (Single thread) One task at a time, blocking
* **Asynchronous:** Multiple tasks in parallel, non-blocking

---

## Thread Synchronization
It controls access to shared resources to prevent conflicts.

### Techniques
* **Mutual Exclusion**: Prevents multiple threads from accessing critical code together using `synchronized` keyword.
* **Inter-thread Communication**: Pause a thread and allow another thread to run in critical code by 
  * `wait()`: Pauses thread
  * `notify()`: Wakes one waiting thread
  * `notifyAll()`: Wakes all waiting threads

---

## Fail-Fast vs Fail-Safe Iterators
**Fail-Fast Iterator:** Throws `ConcurrentModificationException` on structural modification. Examples: `ArrayList`, `HashMap`

**Fail-Safe/ Weakly-Consistent Iterator:**
Works on a copy, hence no exception. Examples: `ConcurrentHashMap` (Designed to be accessed by multiple threads for reading, writing data).

--- 

## Type Safety
Ensures memory safety by preventing invalid memory access or type mismatches.

## Thread Safety
Ensures correct program behavior in multithreaded environments. It can be achieved by: 
* Using immutable objects
* Using `synchronized` blocks/methods
* Using locks
* Using thread-safe collections like `ConcurrentHashMap`

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

### Docker
* Lightweight containers for packaging applications with dependencies. It uses less memory and has sharable containers.

### Kubernetes
* Manages containerized applications (like docker) across clusters through APIs.

---

## Spring Framework
* **Spring:** An open-source framework designed to create java production ready applications in efficient way with minimal configuration, embedded web servers, and starter dependencies.


* **Spring MVC:** Based on **Model-View-Controller (MVC)** architecture, Primarily used for developing web applications.
  * **hibernate.cfg.xml**: Config for DB details.


* **Spring Boot:** Built on top of Spring, widely used for developing APIs in easier way and lesser development time.
  * **pom.xml:** Contains configuration and dependency details. 

---

### Spring Boot Starter Dependencies
* **spring-boot-starter-web:** For web apps
* **spring-boot-starter-data-jpa:** For JPA DB access
* **spring-boot-starter-actuator:** For production monitoring
* **spring-boot-starter-test:** Includes all neccessary test libraries like JUnit, Mockito, AssertJ, SpringTest, etc.
* `@SpringBootApplication Annotation`: Automatically configures all dependencies added in project. It includes `@Configuration` (Defines bean method), `@ComponentScan` (spring component scanning), `@EnableAutoConfiguration` (Enables auto configs)
 
### Inversion of Control (IoC) 
Transfer control of objects to a framework. 

### Dependency Injection (DI)
Injecting necessary dependencies through IOC.
Example: Using `@Autowired` annotation for injecting service class to controller.

---

### Rest API (Representational State Transfer)
Client-server communication using standard HTTP methods. It is easy to test/debug and is language agnostic.
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
  * `RestTemplate`: Used to consume data from external APIs and give response.

> **Spring Boot Application Flow:** `@RestController` → `@Service` → `@Repository` → `@Entity`

---

### Web Socket API
An API designed for continuous data streams instead of single request. It can be done through `@ServerEndpoint` annotation. Example: A subscription service where the restaurant streams you a live cooking video until your meal arrives.

> **WebSocket:** Enables real-time communication between client and server. Example: A phone line between you and the restaurant where both can talk anytime.
> 
> **WebSocket Manager:** Manages all active websocket connections between client and backend servers.

---

### Exception Handling
* `@ControllerAdvice`: Globally handling exceptions
* `@ExceptionHandler`: Handles specific exception
* Can also use try-catch block.

---

### DB Configuration
* **In-memory**: Add `h2database` dependency
* **Outside-memory like MySQL setup**: Add `mysql-connector-java` dependency in `pom.xml`. And configure DB in `application.properties`

---

### Testing
* **Unit Test**: Tests individual modules
* **Integration Test**: Tests combined modules

Mockito: Tests by clone/mock objects of java application.
* `@SpringBootTest(classes = ApiApplication.class)`: Loads context of the main ApiApplication class (with `@SpringBootApplication` annotation) for testing.
* `@AutoConfigureMockMvc`: Enables MockMvc auto-configuration for testing without starting server.
* `MockMvc`: Mocks HTTP calls for REST API testing.
* `@Before`, `@BeforeEach`: Run before each method
* `@BeforeClass`,`@BeforeAll`: Runs once before executing all test methods
* `when().thenReturn()`, `when().thenThrow()`: Mock to return specific values.
* `Assertions.assertEquals(expected, actual)`: Checks if both equal.
* `@Test`:	Marks a method as a test case.
* `MvcResult`: Captures result of MockMvc request for further assertions.


#### Example: 
mockMvc to perform get url with given params, and expect given status/ path to return.
```java
MvcResult result= mockMvc.perform(get("/api/products/"+ saved.getId()))
        .param("name", "Keyboard")
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Mouse")))
        .andReturn();
```

mockMvc to perform post/put url, with given header, contentType to expect a specific result and return.
```java
MvcResult result = mockMvc.perform(put("/api/products/" + saved.getId())
                .header("X-Custom-Header", "my-header-value")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name", is("Tablet Pro")))
          .andExpect(jsonPath("$.price", is(500.0)))
          .andReturn();
```

---

## For creating new Spring project: 
* Check if java installed: `java --version`
* Check if maven installed: `maven --version`
* Generate sample spring project through: https://start.spring.io/ 
* Add DB.
* Remember to add Exception handling (try-catch blocks, null checks) wherever required.

**Spring Boot Example:** Refer to SpringBootExample package. `pom.xml` updated with dependencies needed for this.

---
