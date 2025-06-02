package org.example;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dsa {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        // Reading input from the console
        // Input can be read from Scanner (has built-in parsing for primitive types and strings), BufferedReader (can read only strings, but is faster for larger inputs as it has larger buffer)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int b = Integer.parseInt(br.readLine());
        System.out.println(a + " " + s + " " + b);


        // Math Operations
        int x = 10; double y = 20.5;
        Math.abs(x); Math.max(x, y); Math.min(x, y); Math.sqrt(x); Math.pow(x, y); Math.random();
        x = Integer.MAX_VALUE; x = Integer.MIN_VALUE; y = Double.MAX_VALUE; y = Double.MIN_VALUE;
        Math.round(y); Math.ceil(y); Math.floor(y);   // round off, round up, round down
        BigInteger.valueOf(x).gcd(BigInteger.valueOf((int)y)).intValue(); // GCD of x and y


        /* Permutation and Combination */
        //distributing n different objects in k groups = Math.pow(n,k)
        // distributing n identical objects in k groups = (n+k-1)C(k-1)
        // nCk = n!/(k! * (n-k)!)


        // Character Operations
        Character ch = 'a';
        Character.isLetterOrDigit(ch); Character.isLetter(ch); Character.isDigit(ch); Character.isUpperCase(ch); Character.isLowerCase(ch); Character.toUpperCase(ch); Character.toLowerCase(ch); Character.isWhitespace(ch);


        // String Operations (immutable string i.e. can't change value at any specific index)
        s = "Hello, World!";
        s.length(); s.charAt(0); s.indexOf('o'); s.contains("Hello");   s.substring(0, 5); s.toUpperCase(); s.toLowerCase(); s.trim(); s.replace("World", "Java"); s.split(",");  new StringBuilder(s).reverse().toString();
        s.compareTo("hi"); // Compare two strings lexicographically (returns 0 if equal, negative if less, positive if greater) //<0 if s < "hi"


        // StringBuilder Operations (mutable/ changable string)
        StringBuilder sb = new StringBuilder("Hello");
        sb.append("hi"); sb.insert(5, ","); sb.setCharAt(5, 'r'); sb.delete(5, 6); sb.deleteCharAt(3); sb.reverse(); sb.length(); sb.charAt(0); sb.indexOf("World"); sb.toString();


        // ASCII (American Standard Code for Information Interchange) => A-Z: 65-90, a-z: 97-122, 0-9: 48-57
        System.out.println((char)65);   // A
        System.out.println((int)'A');   // 65
        System.out.println(('j'-'a'));  // 9


        // Type Conversion
        String.valueOf(65); // int to String
        Integer.parseInt("65"); // String to int
        // If problem constraints are larger integers, then use modulo i.e. int M= (int)1e9 + 7 = (ans%M);


        // Bitwise Operations
        a = 5;  b = 9;  int i = 4;  s = "0101"; //a = 5 (00000101), b = 9 (00001001)
        // Binary to decimal: 101101	=>2^5+ 2^3+ 2^2+ 2^0 = 45
        // Decimal to Binary: 13 => 2^3+ 2^2+ 2^0 =1101
        // In binary form, if last digit is 0 means even number, if 1 means odd. Also, can use BigInteger for larger/ longer numbers.

        Integer.parseInt(s, 2); // Convert binary to int i.e. 0101 => 5
        Integer.toBinaryString(a); // Convert int to binary string i.e. 5 => 0101
        Integer.bitCount(a); // Count number of bits in a i.e. 5 => 2

        x = a&b;            // AND operator = Intersection (1&1=1)
        x = a|b;	        // OR operator = Union (0|0 = 0)
        x = a^b;	        // XOR operator	{1*0 =0*1 =1,  1*1 =0*0 =0}
        x = ~a;             //Not operator = Toggles all bits (5 => 1010)
        x = a<<1;           // (00001010) Left shift operator = multiply by 2
        x = a>>1;	        // (00000010) Right shift operator = divide by 2 = removing last bit

        x = a ^ (1<<i);  	// Toggle/ Flip ith bit of a
        x = a | (1<<i); 	// Set ith bit of a, like a=5, i=4  => 21(10101))
        x = a & (1<<i);	    // Check if ith bit is set (Return 2^i if ith bit is set else 0), like a=5,i=2  => 4 (2^2)
        x = a & ~(1<<i);	// Turn off/ clear ith bit of a, like a=5, i=2  => 1(0001)
        // a^b = (1<<i);   // means only 1 bit is having odd freq between them.

        x = 1<<i;		    // pow(2,i)
        x = (1<<a)-1;	    // checks if all bits in a is set returns true if set.
        x = a & 1;		    // checks if odd number (returns 0 if even number)
        x = a & -a;		    // Returns a if number is power of 2



        // Arrays
        int arr[] = new int[5];     arr[0] = 1; arr[1] = 2;
        Integer[] arr2 = {1, 2, 3, 4, 5};
        int[][] arr3 = new int[3][3];   int[][] arr4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // 2D array
        x = arr.length;

        Arrays.sort(arr);  Arrays.sort(arr2, Collections.reverseOrder()); // Sort in ascending, descending order
        Arrays.fill(arr, 0);    Arrays.binarySearch(arr, 4);    Arrays.equals(arr, arr);   Arrays.toString(arr);
        x = Arrays.stream(arr).sum();   x = Arrays.stream(arr).max().getAsInt();   x = Arrays.stream(arr).min().getAsInt();


        //ArrayList (Dynamic array, default capacity is 10  (internally reallocates memory for more values)) and LinkedList (Default capacity is 0) => O(n)
        // Array (search is faster) and LinkedList (insert is faster as no shifting required).
        List<Integer> list = new ArrayList<>(); List<Integer> list2 = new LinkedList<>();
        list.add(20); list.get(2); list.remove(2); list.set(2, 10); list.contains(30); list.size(); list.isEmpty(); list.clear(); list.addAll(list2);
        for(Integer val : list) { System.out.println(val); } // For each loop

        list = Arrays.stream(arr).boxed().collect(Collectors.toList()); // Convert Array to List
        arr = list.stream().mapToInt(r -> r).toArray(); // Convert List to Array

        Collections.sort(list);     Collections.sort(list, Collections.reverseOrder()); // Sort in ascending/ descending order
        Collections.binarySearch(list, 4);  Collections.max(list);  Collections.min(list);  Collections.frequency(list, 5);
        Collections.rotate(list, -2);   Collections.rotate(list, 2);  // Left / Right rotate by 2


        // sort fn returns int instead of boolean
        class Person {
            String name;    int age;
            Person(String name, int age) {  this.name = name;   this.age = age; }
        }
        List<Person> peoplelist = Arrays.asList( new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 25) );

        peoplelist.sort((p1, p2) -> {
            if (p1.age != p2.age)
                return p2.age - p1.age;  // Descending age
            return p1.name.compareTo(p2.name);  // Ascending name   //compareTo() method compares two strings lexicographically
        });



        // HashSet O(1)/ LinkedHashSet O(1)/ TreeSet O(1) (Stores unique elements, maintains no/ insert/ ascending order)
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Apple"); hashSet.remove("Apple"); hashSet.contains("Apple"); hashSet.isEmpty(); hashSet.size();

        // HashMap O(1)/ LinkedHashMap O(1)/ TreeMap O(logn) (Stores key-value pair, maintains no/ insert/ ascending order)
        // HashMap is implemented using HashTable, internally created by 2 data structures i.e. Array (search is faster) and LinkedList (insert is faster).
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 10);   hashMap.get("Apple");   hashMap.getOrDefault("Apple", 0);   hashMap.remove("Apple");    hashMap.containsKey("Apple");   hashMap.containsValue(10);  hashMap.isEmpty();  hashMap.size();
        for (String key : hashMap.keySet()) { System.out.println("Key: " + key + ", Value: " + hashMap.get(key)); }  // For each loop

        TreeMap<String, Integer> map1= new TreeMap<>();
        System.out.println(map1.headMap("D"));  // Used to find keys lesser than or equal (upper bound)     // Output: {A=40, B=10}


        // Stack O(1): LIFO (Last In First Out)
        Stack<String> stack = new Stack<>();
        stack.push("Apple"); stack.pop(); stack.peek(); stack.isEmpty();

        // Queue O(1): FIFO (First In First Out)
        // PriorityQueue O(log n) (min heap i.e. min value at top by default)
        Queue<String> queue = new LinkedList<>();   PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder()); // Max heap
        queue.add("Apple"); queue.poll(); queue.peek(); queue.isEmpty();

        // Deque O(1): Double Ended Queue (can add/remove from both ends)
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("Apple"); deque.addLast("Banana"); deque.removeFirst(); deque.removeLast(); deque.getFirst(); deque.getLast(); deque.isEmpty();




        // In java, passing value in a function is by value, not by reference. While passing an object or list, is pass by reference.

        // Always remember, that even if you are not able to think about sol, think about brute force, atleast give that as an ans.


        /*
        Patterns:
        1. Binary Search: Sort, max/min value
        2. 2 pointer/ sliding window/ Prefix sum: subarray/ substring problems.
        */

    }
}
