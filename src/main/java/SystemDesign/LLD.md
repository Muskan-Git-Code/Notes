## Low Level Design (LLD): 
Detailed blueprint of system, outlining classes and functions to guide developers in implementation phase.

> **Pros of System Design:** Helps in providing abstraction and create reusable, maintainable, loosely-coupled, easily extendable, refactored code which is production ready and can include future changes as well.

---

### 📌 LLD Principles
- **DRY Principle (Don’t Repeat Yourself)**  
  Aim is to reduce repetitive patterns and duplicate code.

- **KISS Principle (Keep it Simple, Stupid)**  
  Most systems work best if they are kept simple rather than made complicated.

- **YAGNI Principle (You Aren’t Gonna Need It)**  
  A programmer should build a feature only when it’s needed, instead of trying to predict the future.

--- 

### 🎯 MVC (Model(handles data) View(UI Layer) Controller(business logic)) Design Pattern
 Promotes **loose coupling** as each layer can work independently. Though can be **difficult to maintain for small systems** due to added complexity.
```java
class Model{
    String data;
    String getData(){   return data; }
    void setData(String data){ this.data= data; }
}
class View{ void display(String data){
    System.out.println("Data from model: "+ data);
}}
class Controller{
    Model model;    View view;
    Controller(Model model, View view){ this.model= model;  this.view= view; }
    void processData(String data){  model.setData(data);    view.display(model.getData());}
}
class mvcMain{
    void mainFn(){
        Model m= new Model();   View v= new View();     Controller c= new Controller(m, v);
        c.processData("Hello!");   // Data from model: Hello!
    }
}
```

---

## 🏗️ Design Patterns
Concepts used for solving real-world problems using object-oriented design.  
They are categorized into three types: **Creational, Structural, Behavioural**

---

### Creational Patterns (Object Creation)

| Pattern           | Description                                                | Example                                                                 |
|-------------------|------------------------------------------------------------|-------------------------------------------------------------------------|
| **Singleton**     | Ensures a class has only one instance globally.            | Global `DBConnection` instance                                          |
| **Factory**       | Creates objects using a common interface method.           | PaymentFactory → UPI/Card/Cash                                          |
| **Abstract Factory** | Factory of factories; creates families of related objects. | PenFactory → Ball/Gel × Blue/Black/Red pens                             |
| **Builder**       | Step-by-step construction of complex objects, Instead of having constructor for all optional fields.            | OrderBuilder → item, qty, coupon                                        |
| **Prototype**     | Creates copy/clone of existing objects.                    | Clone graphical elements like images                                    |

---

### Structural Patterns (Object Composition)

| Pattern           | Description                                                                 | Example                                                                                                                                                                                      |
|-------------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Adapter**       | Allows incompatible interfaces to work together.                           | Charger cable between switch & mobile                                                                                                                                                        |
| **Decorator**     | Dynamically adds new functionality without changing base class.             | Apply coupons on final price; <br> Decorate text to Uppercase/ lowercase letter.                                                                                                             |
| **Proxy**         | Provides controlled access to an object/service.                           | Proxy server restricting blocked sites; <br> admin vs user access                                                                                                                            |
| **Composite**     | Treats group of objects (tree) uniformly as single entity.                  | File system: folders & files; <br> Office hierarchy                                                                                                                                          |
| **Flyweight**     | Shares common data to reduce memory usage.                                  | Word processor storing (char, font, size, position) for thousands chars <br> Construct 100 circle images, i.e. Store circle details (along with radius) in a list, instead of storing image. |

---

### Behavioural Patterns (Object Communication)

| Pattern           | Description                                                  | Example                                                                                    |
|-------------------|--------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| **Observer**      | Notifies multiple observers of state change.                 | Chat/Notification system: product back in stock                                            |
| **Mediator**      | Central object manages communication among others.           | Air traffic control tower mediating between planes                                         |
| **State**         | Alters object behavior based on internal state.              | Fan speed (low→medium→high); <br> vending machine (insert coin->give juice)                |
| **Strategy**      | Chooses algorithm/behavior at runtime.                       | Payment method: UPI, Card, Cash                                                            |
| **Chain of Responsibility** | Passes request along a chain until one handler processes it. | Logging system (error→warn→debug); <br> ATM withdrawal notes breakdown (560 rs =500+50+10) |
| **Null Object**   | Provides default safe object instead of null.                | Default empty logger / placeholder object                                                  |

---

**Example:**
```java
import java.util.*;

// =======================================
// CREATIONAL PATTERNS
// =======================================

// Singleton → Only one CartService instance for each user
class CartService {
    private static CartService instance;
    private CartService() {}
    public static CartService getInstance() {
        if (instance == null) instance = new CartService();
        return instance;
    }
    public void addItem(String item) {
        System.out.println("Item added to cart: " + item);
    }
}

// Factory → Payment methods (diff pay methods)
interface Payment { void pay(int amount); }
class UpiPayment implements Payment { public void pay(int amt){System.out.println("Paid "+amt+" via UPI");}}
class CardPayment implements Payment { public void pay(int amt){System.out.println("Paid "+amt+" via Card");}}
class PaymentFactory {
    public static Payment getPayment(String type){
        return switch(type){
            case "UPI": new UpiPayment();
            case "CARD": new CardPayment();
            default: throw new IllegalArgumentException("Unknown payment");
        };
    }
}

// Abstract Factory → diff UI components present in Web/Mobile (can be same component for both or different)
interface UIComponent { void render(); }
class WebButton implements UIComponent { public void render(){System.out.println("Web Button");}}
class MobileButton implements UIComponent { public void render(){System.out.println("Mobile Button");}}
interface UIFactory { UIComponent createButton(); }
class WebUIFactory implements UIFactory { public UIComponent createButton(){return new WebButton();}}
class MobileUIFactory implements UIFactory { public UIComponent createButton(){return new MobileButton();}}

// Builder → Build Orders step by step
class Order {
    String item; int qty; String coupon;
    private Order(Builder b){item=b.item; qty=b.qty; coupon=b.coupon;}
    static class Builder {
        String item; int qty; String coupon;
        Builder item(String i){item=i; return this;}
        Builder qty(int q){qty=q; return this;}
        Builder coupon(String c){coupon=c; return this;}
        Order build(){return new Order(this);}
    }
}

// Prototype → Clone existing discount coupons
class Coupon implements Cloneable {
    String code;
    Coupon(String c){code=c;}
    public Coupon clone(){return new Coupon(this.code);}
}

// =======================================
// STRUCTURAL PATTERNS
// =======================================

// Adapter → Third-party shipping API
class ThirdPartyShipping {
    void shipProduct(String item){ System.out.println("3rd Party Shipping for " + item);}
}
class ShippingAdapter {
    ThirdPartyShipping service = new ThirdPartyShipping();
    void ship(String item){ service.shipProduct(item); }
}

// Decorator → Add gift wrap
interface Product { void show(); }
class BasicProduct implements Product {
    String name; BasicProduct(String n){name=n;}
    public void show(){ System.out.println("Product: " + name);}
}
class GiftWrap implements Product {
    private Product product;
    GiftWrap(Product p){product=p;}
    public void show(){ product.show(); System.out.println(" + Gift Wrap");}
}

// Proxy → Cached product images
class ProductImage implements Product {
    String file; ProductImage(String f){file=f;}
    public void show(){ System.out.println("Loading image: " + file);}
}
class ProductImageProxy implements Product {
    private ProductImage real; private String file;
    ProductImageProxy(String f){file=f;}
    public void show(){
        if(real==null) real=new ProductImage(file);
        System.out.println("Proxy cache check...");
        real.show();
    }
}

// Composite → Category hierarchy
class Category implements Product {
    String name; List<Product> items=new ArrayList<>();
    Category(String n){name=n;}
    void add(Product p){items.add(p);}
    public void show(){
        System.out.println("Category: " + name);
        for(Product p:items) p.show();
    }
}

// Flyweight → Shared product icons
class IconFactory {
    private static final Map<String,String> icons=new HashMap<>();
    public static String getIcon(String type){
        return icons.computeIfAbsent(type, t -> "Icon for " + t);
    }
}

// =======================================
// BEHAVIORAL PATTERNS
// =======================================

// Observer → Notify users when item restocked
interface Observer { void update(String product); }
class User implements Observer {
    String name; User(String n){name=n;}
    public void update(String product){System.out.println(name+" notified: "+product+" restocked");}
}
class ProductStock {
    String name; List<Observer> obs=new ArrayList<>();
    ProductStock(String n){name=n;}
    void addObserver(Observer o){obs.add(o);}
    void restock(){obs.forEach(o->o.update(name));}
}

// Mediator → Order processing hub
interface Mediator { void notify(String event); }
class OrderMediator implements Mediator {
    public void notify(String event){System.out.println("Mediator handles: "+event);}
}

// State → Order lifecycle
interface OrderState { void handle(); }
class NewOrder implements OrderState { public void handle(){System.out.println("Order created");}}
class ShippedOrder implements OrderState { public void handle(){System.out.println("Order shipped");}}
class OrderContext {
    private OrderState state;
    void setState(OrderState s){state=s;}
    void update(){state.handle();}
}

// Strategy → Shipping strategies
interface ShippingStrategy { void ship(); }
class StandardShipping implements ShippingStrategy { public void ship(){System.out.println("Standard shipping");}}
class ExpressShipping implements ShippingStrategy { public void ship(){System.out.println("Express shipping");}}
class ShippingContext {
    ShippingStrategy strategy;
    void setStrategy(ShippingStrategy s){strategy=s;}
    void ship(){strategy.ship();}
}

// Chain of Responsibility → Discount application
abstract class DiscountHandler {
    protected DiscountHandler next;
    void setNext(DiscountHandler n){next=n;}
    abstract void apply(int amount);
}
class CouponDiscount extends DiscountHandler {
    void apply(int amt){System.out.println("Applying coupon..."); if(next!=null) next.apply(amt);}
}
class LoyaltyDiscount extends DiscountHandler {
    void apply(int amt){System.out.println("Applying loyalty discount"); if(next!=null) next.apply(amt);}
}

// Null Object → Guest user
interface Customer { void buy(); }
class RealCustomer implements Customer { public void buy(){System.out.println("Purchase successful!");} }
class NullCustomer implements Customer { public void buy(){System.out.println("Guest user, limited features");} }

// =======================================
// DEMO
// =======================================
public class EcommerceDemo {
    public static void main(String[] args) {
        // Singleton
        CartService cart = CartService.getInstance();   cart.addItem("Laptop");

        // Factory
        Payment payment = PaymentFactory.getPayment("UPI");     payment.pay(1000);

        // Abstract Factory
        UIFactory factory = new WebUIFactory();     factory.createButton().render();

        // Builder
        Order order = new Order.Builder().item("Phone").qty(1).coupon("NEW50").build();

        // Prototype
        Coupon c1 = new Coupon("SALE20"); Coupon c2 = c1.clone();

        // Adapter
        new ShippingAdapter().ship("Shoes");

        // Decorator
        Product gift = new GiftWrap(new BasicProduct("Watch"));
        gift.show();

        // Proxy
        Product img = new ProductImageProxy("laptop.png");  img.show();

        // Composite
        Category electronics = new Category("Electronics");
        electronics.add(new BasicProduct("Camera"));    electronics.add(new BasicProduct("TV"));
        electronics.show();

        // Flyweight
        System.out.println(IconFactory.getIcon("Phone"));

        // Observer
        ProductStock ps = new ProductStock("iPhone");
        ps.addObserver(new User("Muskan"));     ps.restock();

        // Mediator
        Mediator mediator = new OrderMediator();    mediator.notify("Payment Success");

        // State
        OrderContext ctx = new OrderContext();
        ctx.setState(new NewOrder()); ctx.update();
        ctx.setState(new ShippedOrder()); ctx.update();

        // Strategy
        ShippingContext sc = new ShippingContext();
        sc.setStrategy(new ExpressShipping()); sc.ship();

        // Chain of Responsibility
        DiscountHandler h1=new CouponDiscount(); DiscountHandler h2=new LoyaltyDiscount();
        h1.setNext(h2); h1.apply(1000);

        // Null Object
        Customer cust = new NullCustomer(); cust.buy();
    }
}

```

---

### Approach for Interview:
- Step 1: Requirement Analysis, Ask a lot of questions => 10 min
- Step 2: Ask for time to think about approach, and draw Use case and class diagram => 10 min
- Explain all components in detail, along with SOLID principles, design pattern, and databases = 15 min
- Step 3: Design API, databases, and Spring Boot Application components => 20 min
- Step 4: Edge cases => 10 min

