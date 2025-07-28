package SystemDesign;

import java.util.ArrayList;
import java.util.List;

/*
Hign Level Design (HLD): Tells about overall architecture of a system.
Low Level Design (LLD): Detailed blueprint of system, outlining classes and functions to guide developers in implementation phase.
Pros: Helps in providing abstraction and create reusable, maintainable, loosely-coupled, easily extendable, refactored code which is production ready and can include future changes as well.
*/

public class LLD {
    public static void main(String args[]){}
}



/*----------------------------------------------------------------------------------------------------------------------------------                                                      LOW LEVEL DESIGN ----------------------------------------------------------------------------------------------------------------------------------*/

/*
LLD principles:
    - DRY principle (Don’t repeat yourself): Aim is to reduce repetitive patterns and duplicate code.
    - KISS principle (Keep it Simple, Stupid):  Most systems works best if they are kept simple rather than made complicated.
    - YAGNI principle (You aren’t gonna need it): A programmer should build a feature only when its needed, instead of trying to predict future.
*/

/* MVC (Model(handles data) View(UI Layer) Controller(business logic)) Design Pattern: Useful as loosely coupled, each layer can work independently. Though difficult to maintain for small systems. */
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



/* Design Patterns: Concepts used for solving real world problems using object-oriented design. It is of 3 types based on purpose i.e. Creational, Structural, Behavioural Design pattern. */

/*----------------------------------------------------------------------------------------------------------------------------------                            Creational Pattern: Specifies way of creating object. ----------------------------------------------------------------------------------------------------------------------------------*/

/* Singleton Pattern: Ensure class has only one instance/ object globally. */
class Singleton{
    static Singleton obj;    // static keyword so that we have only one instance.

    public static Singleton mainFn(){
        if(obj==null){  obj= new Singleton(); }
        return obj;     // now use the object for further processing
    }
}


/* Factory Pattern: Create instance using common interface method, letting subclasses decide which class to instantiate. */
interface Shape{    void draw(); }
class Circle implements Shape{  public void draw(){     System.out.println("Circle"); }}
class Square implements Shape{  public void draw(){     System.out.println("Square"); }}

class Factory{
    void mainFn(String type){
        Shape s= new Square();      s.draw();   // gives Square as ans
        Shape s2= new Circle();      s2.draw();   // Circle
    }
}


/* Abstract Factory Pattern: It is factory of factories. Example: On top of shape, there is color as well. Like Square with Blue color. */


/* Prototype Design Pattern: Used for creating copy/ clone of existing object. Example: Graphical design software: for copying or duplicating graphical elements like image. */
interface Shape2{
    void draw();    Shape2 copy();
}
class Circle2 implements Shape2{
    public void draw(){     System.out.println("Drawing Circle"); }
    public Shape2 copy(){   return new Circle2(); }
}
class Square2 implements Shape2{
    public void draw(){     System.out.println("Drawing Square"); }
    public Shape2 copy(){   return new Square2(); }
}

class Prototype{
    void mainFn(){
        Shape2 s= new Circle2();    s.copy().draw();    // Drawing circle.
    }
}


/* Builder design Pattern: Construct a complex object step by step. Instead of having constructor for all optional fields, we can use Builder. Example: StringBuilder (creates string character by character). */

// Normal entity class, Builder class, then set values to mainFn.
class Burger {
    String bread, sauce;    boolean cheese;
    Burger(String bread, String sauce, boolean cheese) {
        this.bread= bread;       this.sauce= sauce;       this.cheese= cheese;
    }

    void display() {    System.out.println("Burger contains: "+ bread+ ", "+ sauce+ ", "+ (cheese ? "cheese" : "") ); }
}

class BurgerBuilder{
    String bread, sauce;    boolean cheese;
    BurgerBuilder setBread(String bread){ this.bread= bread;  return this; }
    BurgerBuilder setSauce(String sauce){ this.sauce= sauce;  return this; }
    BurgerBuilder setCheese(boolean cheese){  this.cheese= cheese;    return this; }

    Burger build() {    return new Burger(bread, sauce, cheese); }
}

class MainBuilder {
    void mainFn() {
        Burger burger= new BurgerBuilder()
                .setBread("Brown")
                .setCheese(true)
                .build();
        burger.display();   // Output: Burger contains: Brown, cheese
    }
}



/*----------------------------------------------------------------------------------------------------------------------------------                     Structural Design Pattern: Specifies way of composing classes and objects. ----------------------------------------------------------------------------------------------------------------------------------*/

/* Adapter design pattern: Allows incompatible interfaces to work together. Example: Charger */

// SwitchBoard, TypeCCharger are 2 incompatible classes, we called charge method, through SwitchBoard using Adapter.
interface SwitchBoard{      void power(); }

class TypeCCharger{
    void charge(){     System.out.println("Charging phone"); }
}

class Adapter implements SwitchBoard{
    TypeCCharger charger;
    Adapter(TypeCCharger charger){  this.charger= charger; }

    @Override
    public void power(){
        System.out.println("Adapter converts round socket to Type-C");      charger.charge();
    }
}

class MainAdapFn{
    void mainFn(){
        TypeCCharger charger= new TypeCCharger();
        SwitchBoard s= new Adapter(charger);    s.power();  // Output: Adapter converts round socket to Type-C. Charging phone
    }
}


/* Decorator Design Pattern: Decorate/ Structure to add functionalities dynamically. Example: Pizza Shop, Applying coupons on final price. */

// we created a message, then convert same msg to upper case, then add "world" in end, and so on diff functionalities.
interface Msg{  String content(); }
class SimpleMsg implements Msg{
    public String content(){   return "hello"; }
}
class UpperMsg implements Msg{
    Msg msg;
    UpperMsg(Msg msg){  this.msg= msg; }
    public String content(){    return msg.content().toUpperCase(); }
}
class MainDec{
    void mainFn(){
        Msg msg= new SimpleMsg();   System.out.println(msg.content());      // hello
        msg= new UpperMsg(msg);     System.out.println(msg.content());      // HELLO
    }
}


/* Proxy Design Pattern: Structured to provide controllable access of a service. Example: Proxy Server (list of sites are blocked to be accessed from office laptop), Admin have access to some additional methods as compare to other users. */
// if(client=="Admin"){ return msg.content(); }else{ return "Access denied"; }


/* Fly Weight Design Pattern: Structurally reduces memory usage by sharing related objects.
Example: Construct 100 circle images of diff size. Store circle details (along with radius) in a list. And on user's ask directly create circle at that time, instead of storing images.
 Example: Word Processor: Write 5000 words essay. Store (char, Font, FontSize, Position) in list, so that for each character we are storing only one object.
*/


/* Composite design pattern: Tree structure objects can be handled (like adjacency list). Example: File System (Folder/ file inside another folder), Office hierarchy, Expression solving (like 2+5*7) */



/*----------------------------------------------------------------------------------------------------------------------------------                     Behaviour Design Pattern: Focus on communication between objects. ----------------------------------------------------------------------------------------------------------------------------------*/

/* Null Object design pattern: Provides default objects to avoid null checks and NullPointerException. */

interface User{ void userDetails(); }
class RealUser implements User{
    String name;
    RealUser(String name){  this.name= name; }
    public void userDetails(){     System.out.println(name);}
}
class NullUser implements User{
    public void userDetails(){      System.out.println("Unknown"); }
}
class MainNullObj{
    User getByType(String type, String name){
        if(type.equals("Human")){   return new RealUser(name); }
        return new NullUser();
    }

    void mainFn(){
        User user= getByType("Human", "Muskan");      user.userDetails();   // Muskan
    }
}


/* Observer design pattern: Notify behavioural change of an object to a number of observers. Example: Chat System, Notification System: Notify users when product is again inStock. */

// observers (obs1, obs2) are observing temperature. Manage observer is managing observers and showing result.
interface Observer{     void update(int temp); }
class Obs1 implements Observer{
    public void update(int temp){   System.out.println("Obs1 Temp: "+ temp); }
}
class Obs2 implements Observer{
    public void update(int temp){   System.out.println("Obs2 Temp: "+ temp); }
}

interface ManageObs{
    void addObs(Observer o);    void removeObs(Observer o);     void notifyObs(int temp);
}
class ManageObsImp implements ManageObs{
    List<Observer> observers= new ArrayList<>();
    public void addObs(Observer o){    observers.add(o); }
    public void removeObs(Observer o){  observers.remove(o); }
    public void notifyObs(int temp){    for(Observer i: observers){     i.update(temp); }}
}

class ObsMain{
    void mainFn(){
        Observer o1= new Obs1();    Observer o2= new Obs2();
        ManageObs m= new ManageObsImp();    m.addObs(o1);   m.addObs(o2);
        m.notifyObs(25);    // all observers are updated about temp.
    }
}


/* Mediator Design Pattern: Centralize/ Mediator communication between objects, to reduce complexity and tight coupling. Implementation is same as observer design pattern.
Example: Planes don't talk to each other directly, They coordinates with control tower (acts as a mediator) which tells when to land or take-off.  */


/* Chain of responsibility design pattern: Behaviour of passing a request through chain of objects, until one handler processes it.
Example: Logging System: Different log level messages i.e. error, warning, debug.
Example: ATM Withdrawl (560 rs =500+50+10): Sent amount in order of 5000, 2000, 1000, 500, 100, 50, 20, 10 for the money. */


/* State Design Pattern: Change behaviour based on internal state. Example: Fan speed (low->high->higher), TV Remote, Vending machine (insert coin->give juice). Alter object's behaviour when its internal state changes. Maintain state in variable */

/* Strategy Design Pattern: Allow selecting at runtime. Example: Payment Method (UPI, card, cash) */

