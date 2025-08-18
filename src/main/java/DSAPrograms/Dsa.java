package DSAPrograms;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dsa {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        // Reading input from the console can be through Scanner (has built-in parsing for primitive types and strings), BufferedReader (can read only strings, but is faster for larger inputs as it has larger buffer)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());    int b = Integer.parseInt(br.readLine());    Float f= Float.parseFloat(br.readLine());   String s = br.readLine();
        System.out.println(a + " " + s + " " + b);


        // Math Operations
        int x = 10; double y = 20.5;    long z= 10;
        Math.abs(x); Math.max(x, y); Math.min(x, y); Math.sqrt(x); Math.pow(x, y); Math.random();
        x = Integer.MAX_VALUE; x = Integer.MIN_VALUE; y = Double.MAX_VALUE; y = Double.MIN_VALUE;   z= Long.MAX_VALUE;
        Math.round(y); Math.ceil(y); Math.floor(y);   // round off, round up, round down
        BigInteger.valueOf(x).gcd(BigInteger.valueOf((int)y)).intValue(); // GCD of x and y


        /* Permutation and Combination */
        // Combination: nCk = n!/(k! * (n-k)!), Permutation: n!
        //distributing n different objects in k groups = Math.pow(n,k)
        // distributing n identical objects in k groups = (n+k-1)C(k-1)


        // Character Operations
        Character ch = 'a';
        Character.isLetterOrDigit(ch); Character.isLetter(ch); Character.isDigit(ch); Character.isUpperCase(ch); Character.isLowerCase(ch); Character.toUpperCase(ch); Character.toLowerCase(ch); Character.isWhitespace(ch);


        // String Operations (immutable string i.e. can't change value at any specific index)
        s = "Hello, World!";    String s2= "Hello, World!";
        s.length(); s.charAt(0); s.indexOf('o'); s.contains("Hello");   s.toUpperCase(); s.toLowerCase(); s.trim(); s.replace("World", "Java"); s.split(",");  new StringBuilder(s).reverse().toString();
        s.substring(0, 5); //(begin, end index-1)
        s.compareTo("hi"); // Compare two strings lexicographically (returns 0 if equal, negative if less, positive if greater) //<0 if s < "hi"

        // == compares references, .equals() method compares value
        String aa= "hi", b1= new String("hi");
        boolean res= (aa==b1);  // return false
        res= aa.equals(b1);     // return true

        // sorting a string
        char[] ch5 = s.toCharArray();  // convert string to char array
        Arrays.sort(ch5);              // sort characters
        String sorted = new String(ch5);    // char array to string


        // StringBuilder Operations (mutable/ changable string) - Appends Faster than string
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

        int base=2;     Integer.parseInt(s, base); // Integer.parseInt(101, 2);     // Convert binary to int for given base value = binary representation to int for base 2  = (0101 => 5)
        Long.toString(a, base); // Long.toString(5, 2); // get integer to base value i.e. 5 => 0101
        Integer.bitCount(a); // Count number of set bits in a i.e. 5 => 2

        x = a&b;            // AND operator = Intersection (1&1=1)
        x = a|b;	        // OR operator = Union (0|0 = 0)
        x = a^b;	        // XOR operator	{1^0 =0^1 =1,  1^1 =0^0 =0}
        x = ~a;             // Not operator = Toggles all bits (5 => 1010)
        x = a<<1;           // (00001010) Left shift operator = multiply by 2
        x = a>>1;	        // (00000010) Right shift operator = divide by 2

        x = a ^ (1<<i);  	// Toggle/ Flip ith bit of a
        x = a | (1<<i); 	// Set ith bit of a, like a=5, i=4  => 21(10101))
        x = a & (1<<i);	    // Check if ith bit is set (Return 2^i if ith bit is set else 0), like a=5,i=2  => 4 (2^2)
        x = a & ~(1<<i);	// Turn off/ clear ith bit of a, like a=5, i=2  => 1(0001)
        // a^b = (1<<i);   // means only 1 bit is having odd freq between them.

        x = 1<<i;		    // pow(2,i)
        x = (1<<a)-1;	    // checks if all bits in 'a' is set returns true if set.
        x = a & (a-1);      // Returns 1 if 'a' is power of 2; i.e. (=5 & 4  =101 & 100  =1)
        x = a & 1;		    // checks if odd number (returns 0 if even number)

        Integer.compare(a, b);      // a==b then 0, a>b then +ve, a<b then -ve


        // Arrays
        int arr[] = new int[5];     arr[0] = 1; arr[1] = 2;
        Integer[] arr2 = {1, 2, 3, 4, 5};
        int[][] arr3 = new int[3][3];   int[][] arr4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // 2D array
        x = arr.length;

        Arrays.fill(arr, 0);    Arrays.binarySearch(arr, 4);    Arrays.equals(arr, arr);   Arrays.toString(arr);
        x = Arrays.stream(arr).sum();   x = Arrays.stream(arr).max().getAsInt();   x = Arrays.stream(arr).min().getAsInt();

        // For 1D array, sort can be only asc desc (or use list). For 2D array can use comparator function.
        Arrays.sort(arr);
        Arrays.sort(arr, 2, 5);     //sort array from (startIx, endIx-1)
        Arrays.sort(arr3, (p1,p2) -> { return Integer.compare(p2[1], p1[1]); });    // this comparision sorting works only in 2D array for int datatype. Sorting in desc order.
        Arrays.sort(arr2, Collections.reverseOrder()); // Integer array sort in descending order


        //ArrayList (Dynamic array, default capacity is 10  (internally reallocates memory for more values)) and LinkedList (Default capacity is 0) => O(n)
        // Array (search is faster) and LinkedList (insert is faster as no shifting required).
        List<Integer> list = new ArrayList<>(); List<Integer> list2 = new LinkedList<>();   // works as both doubly/ single linkedlist
        List<Integer> list3= new ArrayList<>(list);
        list.add(20); list.add(2, 30);  list.addAll(list2); list.get(2); list.remove(2); list.set(2, 10); // set(ix, ele) replaces ele at that ix.
        list.subList(2, 5); list.contains(30); list.size(); list.isEmpty(); list.clear();
        for(Integer val : list) { System.out.println(val); } // For-each loop
        System.out.println(list);   // print all elements of array

        list = Arrays.stream(arr).boxed().collect(Collectors.toList()); // Convert Array to List
        arr = list.stream().mapToInt(r -> r).toArray(); // Convert List to Array

        List<List<Integer>> list2d= new ArrayList<>();   list2d.add(list);  // modifies the original list, as it points to reference of list
        list2d.add(new ArrayList<>(list));      // its a new copy of list.

        List<List<List<Integer>>> adj= new ArrayList<>();   adj.get(0).add(Arrays.asList(1,6));     // 3D array initialization


        Collections.binarySearch(list, 4);  Collections.max(list);  Collections.min(list);  Collections.frequency(list, 5);
        Collections.rotate(list, -2);   Collections.rotate(list, 2);  // Left / Right rotate by 2
        List<Integer> slot= new ArrayList<>(Collections.nCopies(5, -1));  // return a new array of 5 integer, each with value -1.

        Collections.sort(list);     // asc order

        // sort by comparator function returns int instead of boolean
        List<List<Integer>> jobs= new ArrayList<>();        jobs.sort((p,q) -> { return q.get(2)-p.get(2); } ); // sort by 2nd value desc

        Collections.reverse(list);      Collections.sort(list, (a1,a2)->{ return Integer.compare(a2, a1); });       // desc order


        // HashSet O(1)/ LinkedHashSet O(1)/ TreeSet O(1) (Stores unique elements, maintains no/ insert/ ascending order)
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Apple"); hashSet.remove("Apple"); hashSet.contains("Apple"); hashSet.isEmpty(); hashSet.size();
        for(String key: hashSet){ System.out.println(key); }

        // HashMap O(1)/ LinkedHashMap O(1)/ TreeMap O(logn) (Stores key-value pair, maintains no/ insert/ ascending order)
        // HashMap is implemented using HashTable, internally created by 2 data structures i.e. Array (search is faster) and LinkedList (insert is faster).
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 10);   hashMap.get("Apple");   hashMap.getOrDefault("Apple", 0);   hashMap.remove("Apple");    hashMap.containsKey("Apple");   hashMap.containsValue(10);  hashMap.isEmpty();  hashMap.size();
        hashMap.values(); // return List<Integer> values
        for (String key : hashMap.keySet()) { System.out.println("Key: " + key + ", Value: " + hashMap.get(key)); }  // For each loop

        TreeMap<String, Integer> map1= new TreeMap<>();
        System.out.println(map1.headMap("D"));  // Used to find keys lesser than or equal (upper bound)     // Output: {A=40, B=10}
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16, 0.75f, true); // returns values in accessOrder


        // Stack O(1): LIFO (Last In First Out)
        Stack<String> stack = new Stack<>();
        stack.add("Apple"); stack.pop(); stack.peek(); stack.isEmpty();

        // Queue O(1): FIFO (First In First Out)
        // PriorityQueue O(log n) (min heap i.e. min value at top by default)
        Queue<String> queue = new ArrayDeque<>();   PriorityQueue<Integer> pq = new PriorityQueue<>();  //minHeap
        PriorityQueue<Integer> pq2 = new PriorityQueue<>( (p1,p2) -> { return Integer.compare(p2,p1); } );  // Integer.compare(p2,p1) = p2-p1.  //maxHeap
        queue.add("Apple"); queue.remove(); queue.peek(); queue.isEmpty();

        // Deque O(1): Double Ended Queue (can add/remove from both ends)
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Apple"); deque.addLast("Banana"); deque.removeFirst(); deque.removeLast(); deque.getFirst(); deque.getLast(); deque.isEmpty();


        // Total number of subarray= n*(n+1)/2
        // Total number of subarrays including a specific element of array= (left length)*(rightLength)
        // Length from index i to j = j-i+1
        // Total number of subsequence = Math.pow(2, j-i)


        // In java, passing value in a function is by value, not by reference. While passing an object or list, is pass by reference.

        // Always remember, that even if you are not able to think about sol, think about brute force, atleast give that as an ans.


        // 1e9 is returned instead of Integer.MAX_VALUE, if need to add something further but still needs a bigger value for int calculations.

        // in java for adding one value to another, like List<String> path= new ArrayList<>(); path.add('a'); List<List<String>> ans= new ArrayList<>(); ans.add(new ArrayList<>(path));    always add new ArrayList again, so that it makes new variable instead of changing current one.


        // Directions: Think in term of matrix. For (row, col) -> up, down, left, right is (row-1, col), (row+1, col), (row, col-1), (row, col+1). So directions will be: dx[]= {-1, 1, 0, 0}, dy[]= {0, 0, -1, 1}.

        /*
        Patterns:
        1. Binary Search: Sort, max/min value
        2. 2 pointer/ sliding window/ Prefix sum: subarray/ substring problems.
        3. Stack-Queue: Check if through lifo/ fifo order possible to solve.
        */

        // BackTracking: for number of ways, always use backtracking method, i.e. add value before recursion but remove after recursion, for different paths.



        /*
        ## Sorting Algorithms

| Algorithm | Time Complexity | Space    | Stable |
| --------- | --------------- | -------- | ------ |
| Bubble    | O(n^2)          | O(1)     | Yes    |
| Selection | O(n^2)          | O(1)     | No     |
| Insertion | O(n^2)          | O(1)     | Yes    |
| Merge     | O(n log n)      | O(n)     | Yes    |
| Quick     | O(n log n)      | O(log n) | No     |

> QuickSort is better for arrays, MergeSort for linked lists.



Sorting Algorithms: the different sorting algorithms are bubble sort, selection sort, insertion sort, merge sort and quick sort.
Bubble Sort O(n2)
Selection Sort O(n2)
Insertion Sort O(n2)
Merge Sort O(nlogn)
Quick Sort O(nlogn)
(Sort by comparing 2 elements at a time)
(Sort by taking smallest element)
(Sort by taking
one value at a
time and insert
at correct
place)
(Sort by
merging the
sorted
half’s)
(Sort elements around a particular element by choose last element as pivot. Then set left and right pointer. If value at left pointer is larger than pivot then swap it with right pointer. In this way we get two half’s. And then sort the half’s by again choosing pivot for both halfs).
9 6 3 4
6 9 3 4
6 3 9 4
6 3 4 9
3 6 4 9
3 4 6 9
9 6 3 4
3 6 9 4
3 4 9 6
3 4 6 9
9 6 3 4
9 6 3 4
6 9 3 4
3 6 9 4
3 4 6 9
9 6 3 4
9 6   3 4
9  6  3  4
6 9  3 4
3 4 6 9
9 6 3 4
3 6 9 4
3 6 4 9
3 4 6 9


Quick Sort and merge sort are more efficient for sorting large arrays. Quick sort is preferred for arrays and merge sort for linked list. In merge sort, complete process has to be done whether array is sorted or not, therefore quick sort is better for array. Merge sort is preferred for linked list.
In worst, quick sort takes O(n2) time. And in best case, bubble and insertion takes O(n) time complexity.

Sorting Terminology: In-Place Sorting (when no extra space is required while sorting like in Bubble, Selection, Insertion, Quick Sort) and Stable sorting (Duplicate elements retains their order of occurrence, like in Bubble, Insertion, Merge Sort).

Space Complexity: For sorting, it is O(1) for bubble, selection, insertion sort; O(n) for merge sort and O(logn) for quick sort.
Time Complexity: It can be of 3 types and are simply known as Asymptotic notation (mathematical notation used to bound complexity of an algorithm) i.e. Worst Case/ Upper Bound (O) (Maximum number of operations to be calculated), Average Case(θ), Best Case/ Lower Bound (Ω).
Time Complexity: O(1) < O(log n) < O(n) < O(n logn) < O(n2) < O(n3) < O(2n) < O(n!) < exponential
Corner Cases: Empty cases, underflow/overflow, negative numbers, sequence with 1 or 2 elements, repeated elements.



        */
    }
}
