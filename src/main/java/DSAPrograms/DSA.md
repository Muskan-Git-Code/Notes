# 📘 Java DSA Handbook

## 🔹 Input / Output
```java
void inpOutput() {
    // Scanner (simple but slower)
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();   String s = sc.nextLine();

    // BufferedReader (faster for large input)
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int a = Integer.parseInt(br.readLine());    float f = Float.parseFloat(br.readLine());  String s = br.readLine();
    System.out.println(a +" "+s +" "+b);
}
```
---

## 🔹 Character Operations
```java
void fn(){
    Character ch = 'a';
    Character.isLetterOrDigit(ch);  Character.isLetter(ch); Character.isDigit(ch);
    Character.isUpperCase(ch);  Character.isLowerCase(ch);
    Character.toUpperCase(ch);  Character.toLowerCase(ch);
    Character.isWhitespace(ch);
}
```
---

## 🔹 StringBuilder (Mutable String)
```java
void fn(){
    StringBuilder sb = new StringBuilder("Hello");
    sb.append("Hi");    sb.insert(5, ",");  // (index, ele)
    sb.delete(5, 6); // (startIx, endIx)
    sb.length();    sb.toString();  sb.reverse();   sb.indexOf("World");    
    sb.charAt(0);   sb.setCharAt(5, 'r');   sb.deleteCharAt(3); // (index, ele)
}
```
---

## 🔹 String Operations
Immutable (cannot change value at a specific index).
```java
void fn(){
    String s = "Hello, World!";
    s.length(); s.charAt(0);    s.indexOf('o'); s.contains("Hello");    s.toUpperCase();    s.toLowerCase();    
    s.trim();   s.replace("World", "Java"); s.split(",");
    s.substring(0, 5); // beginIndex to endIndex-1
    s.compareTo("hi"); // lexicographic compare
    new StringBuilder(s).reverse().toString();  // reverse of string

    // sorting of a string - storing in another variable is imp as strings are immutable.
    char c[]= s.toCharArray();  Arrays.sort(c); String snew= new String(c);
}
```

**Comparison:**
```java
voif fn(){
    String a = "hi", b = new String("hi");
    a == b;        // false (compares reference/ address)
    a.equals(b);   // true (compares value)
}
```

- In Java, all arguments are **pass-by-value**.
    - For objects/collections: the reference itself is passed by value → behaves like pass-by-reference.
---

## 🔹 Arrays
```java
void fn(){
    int[] arr = new int[5]; arr[3]= 20;
    int[][] arr3 = new int[3][3];   int[][] arr4 = {{1,2,3}, {4,5,6}, {7,8,9}};
    
    Arrays.fill(arr, 0);    System.out.println(Arrays.toString(arr));  // for printing array values
    Arrays.binarySearch(arr, 4);    // search ele 4 in arr.
    
    Arrays.sort(arr);   Arrays.sort(arr, 2, 5); // (arr, startIx, endIx)
    Arrays.sort(arr3, (p1, p2) -> Integer.compare(p2[1], p1[1])); // 2D sort possible with comparator operator as well.

    
    int[] dx = {-1, 1, 0, 0};   int[] dy = {0, 0, -1, 1};   // Directions in matrix (up, down, left, right)
}
```
---

## 🔹 ArrayList & LinkedList
```java
void fn(){
    List<Integer> list = new ArrayList<>();
    list.add(20);   list.contains(30);  list.clear();   list.isEmpty();
    list.add(2, 30);    list.set(2, 10);    // (index, ele) => add/ replace ele at specific ix.
    list.remove(2); // remove from particular index
    for(Integer val : list) {   System.out.println(val); }  // For-each loop
    System.out.println(list);

    List<List<Integer>> list2d = new ArrayList<>();
    list2d.add(new ArrayList<>(list));  // always add with new keyword for different address

    List<List<List<Integer>>> adj= new ArrayList<>();   adj.get(0).add(Arrays.asList(1,6));     // 3D array initialization
    

    Collections.sort(list); Collections.reverse(list);  Collections.max(list);  Collections.min(list);
    Collections.binarySearch(list, 4);
    
    list.sort((p,q) -> { return Integer.compare(p, q); } ); // asc order
    list2d.sort((p,q) -> { return Integer.compare(q.get(2), p.get(2)); } ); // desc order
    list.reversed();    // reverse order
}
```
---

## 🔹 Sets & Maps
- **HashSet O(1)/ LinkedHashSet O(1)/ TreeSet O(logn):** Stores unique elements, maintains no/ insert/ ascending order
- **HashMap O(1)/ LinkedHashMap O(1)/ TreeMap O(logn):** Stores key-value pair, maintains no/ insert/ ascending order


- HashMap is implemented using HashTable, internally created by 2 data structures i.e. Array (search is faster) and LinkedList (insert is faster).
```java
void fn(){
    HashSet<String> set = new HashSet<>();
    set.add("Apple");   set.contains("Apple");
    for(String key: set){ System.out.println(key); }

    HashMap<String,Integer> map = new HashMap<>();
    map.put("Apple", 10);   map.getOrDefault("Apple", 0);   map.remove("Apple");    map.containsKey("Apple");
    for(String key : map.keySet()){    System.out.println(key + " → " + map.get(key)); }
    
    TreeMap<String,Integer> tmap = new TreeMap<>(); tmap.headMap("D");
}
```
---

## 🔹 Stack, Queue & Deque
```java
void fn(){
    // Stack (LIFO) → Example: Piles of books.
    Stack<String> stack = new Stack<>();
    stack.add("A");    stack.pop();    stack.peek();    stack.isEmpty();
    
    // Queue (FIFO) → Example: People waiting in ticket line, data transfer between two processes.
    Queue<String> queue = new ArrayDeque<>();
    queue.add("A"); queue.remove(); queue.peek();   queue.isEmpty();
    
    // PriorityQueue (Heap) {Methods same as queue} → Stores element according to priority.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((p1,p2) -> { return Integer.compare(p2, p1); });
    
    // Deque → Can add ele from both front and end
    Deque<String> deque = new ArrayDeque<>();
    deque.addFirst("Apple");    deque.addLast("Banana");   deque.removeFirst(); deque.removeLast(); deque.getFirst();   deque.getLast();    deque.isEmpty();
    
    // CircularQueue (Ring Buffer) → Queue where end connects to front, reuses free space (even if in front of queue) for efficient memory use.
}
```
---

## 🔹 Math Operations
```java
void fn() {
    int x = 10; double y = 20.5;    long z= 10;
    Math.abs(x);    Math.max(x, y); Math.min(x, y); Math.sqrt(x);   Math.pow(x, y); Math.random();
    x = Integer.MAX_VALUE; x = Integer.MIN_VALUE;   y = Double.MAX_VALUE; y = Double.MIN_VALUE;     z= Long.MAX_VALUE;
    Math.round(y); Math.ceil(y); Math.floor(y);   // round off, round up, round down
    BigInteger.valueOf(x).gcd(BigInteger.valueOf((int)y)).intValue();   // GCD of x and y
}
```
---

## 🔹 ASCII Values

```java
void fn(){
    System.out.println((char)65);   // A
    System.out.println((int)'A');   // 65 {A-Z = 65-90, a-z = 97-122}
    System.out.println('j' - 'a');  // 9
}
```
---

## 🔹 Type Conversion
```java
void fn() {
    String.valueOf(65);     // int → String
    Integer.parseInt("65"); // String → int
}
```
⚡ Use modulo for large constraints → `int M = (int)1e9 + 7;`
- Use **1e9** (≈10⁹) instead of `Integer.MAX_VALUE` when you may need extra room for calculations.
- `Integer.compare(a,b);` checks if(a==b) then 0, a>b then +ve, a<b then -ve
- Use **BigInteger** for very large numbers

---
## 🔹 Subarray / Subsequence
* Total subarrays = `n*(n+1)/2`
* Total Subarrays containing an element at index `i` = `(leftLen * rightLen)` {including index i}
* Length from index `i` to `j` = `j-i+1`
* Total subsequences = `2^n`  {as for each ele have 2 choices i.e. to include/exclude}

## 🔹Permutation & Combination:
* Combination = Choosing k elements from n (order doesn't matter): `nCk = n! / (k! * (n-k)!)`
* Permutation = Arranging n elements (order matters): `n!`
* Distribute `n` different objects in `k` groups → `n^k`
* Distribute `n` identical objects in `k` groups → `(n+k-1)C(k-1)`
---

## 🔹 Bitwise Operations
- **Binary → Decimal:** `101101 => 2^5 + 2^3 + 2^2 + 2^0 = 45`
- **Decimal → Binary:** `13 => 2^3 + 2^2 + 2^0 = 1101`

```java
void fn(){
    int a=5, b=9, i=4, base=2;    // a=5 (00000101), b=9 (00001001)
    
    int x = a & b;   // AND = Intersection (1&1=1)
    x = a | b;       // OR = Union (0|0=0)
    x = a ^ b;       // XOR {1^0 =0^1 =1,  1^1 =0^0 =0}
    x = ~a;          // NOT = Toggle all bits (010)
    x = a << 1;      // Left shift = multiply by 2 (00001010)
    x = a >> 1;      // Right shift = divide by 2 (00000010)
    
    x = a ^ (1<<i);  // Toggle ith bit
    x = a | (1<<i);  // Set ith bit
    x = a & (1<<i);  // Check ith bit
    x = a & ~(1<<i); // Clear ith bit

    Integer.parseInt("101", base);   // Convert binary to decimal ("101" => 5)
    Long.toString(5, base);     // Convert decimal to binary (5 => "101")
    Integer.bitCount(a); // Count set bits (for 5 => 2 set bits)

    x = a & 1;  // checks if a is odd/ even (1=odd, 0=even)
    // In binary format, if Last digit is `0` → number is **even** else odd
    x = 1 << i;     // returns 2^i (power of 2)
    x = i & (i-1);  // returns 0 if 'i' is power of 2 {(4&3) =(100 & 011) =0)}
    x = (1 << i) - 1;   // create mask with i bit set =(2^4 -1) =15 =1111)
}
```
---

## 🔹 Key DSA Concepts
1. **Binary Search** → Search min / max in sorted arrays
2. **Two Pointers / Sliding Window / Prefix Sum** → Subarray / substring problems
3. **Stack & Queue** → LIFO / FIFO problems
4. **Backtracking** → Paths / permutations; add before recursion, remove after
5. **Recursion** → Break problem into smaller subproblems
6. **Dynamic Programming (DP)** → Recursion + memoization (top-down) or tabulation (bottom-up)
7. **Trees** → Non-linear Data Structure with `n` nodes, `n-1` edges; no cycles
    - **Binary Tree** → Each node has ≤ 2 children
    - **BST** → Sorted binary tree. Height of balanced BST ≈ `O(log n)`
    - **Heap** → Max-heap (parent ≥ children), Min-heap (parent ≤ children)
    - **Trie** → Prefix tree for dynamic strings
8. **Graphs** → Non-linear Data Structure of nodes + edges (directed / undirected). Example: Path in a city, Telephonic/ Circuit network.
    - Representations:
        - **Adjacency Matrix** → `mat[x][y] = 1` if edge x→y
        - **Adjacency List** → `list[x] = {y1, y2...}`
    - Degree of Vertex = InDegree + OutDegree
    - **Dijkstra’s Algorithm** → Shortest path in weighted (non-negative) graphs, O((V+E) log V)
    - **Topological Sort** → Order vertices in DAG (Directed Acyclic Graph)
---

## 🔹 Sorting Algorithms
| Algorithm | Time Complexity | Space    | Stable (Duplicate elements retain their order of occurence) | Explanation |
| --------- | --------------- | -------- |-------------------------------------------------------------| ----------- |
| Bubble    | O(n²)           | O(1)     | ✅                                                           | Repeatedly swap adjacent elements if out of order; largest element moves to the end in each pass. |
| Selection | O(n²)           | O(1)     | ❌                                                           | Repeatedly select the smallest element from unsorted part and put it in the front. |
| Insertion | O(n²)           | O(1)     | ✅                                                           | Take elements one by one and insert them into their correct position in the sorted part. |
| Merge     | O(n log n)      | O(n)     | ✅                                                           | Split array into halves, sort them recursively, then **merge** sorted halves. |
| Quick     | O(n log n)      | O(log n) | ❌                                                           | Choose a **pivot**, partition array into smaller/greater parts, then sort partitions recursively. |

➡ **QuickSort** → better for arrays (in-place, cache friendly)  
➡ **MergeSort** → better for linked lists (needs no extra random access)
---

## 🔹 Traversals (Trees & Graphs)
- **DFS (Depth-First Search)**
    - Explore deep paths
    - Variants (for trees):
        - Inorder: Left → Root → Right (BST gives sorted order)
        - Preorder: Root → Left → Right
        - Postorder: Left → Right → Root
    - **Predecessor** → Node just before a given node in inorder traversal
    - **Successor** → Node just after a given node in inorder traversal 
    - Uses: Topological sort, cycle detection, path finding
    - Complexity: O(V+E), Space: O(V)

- **BFS (Breadth-First Search / Level-order)** 
    - Explore neighbors level-wise
    - Uses: Shortest path in unweighted graphs, tree level traversal
    - Complexity: O(V+E), Space: O(V)
---

## 🔹 Time & Space Complexity
Mathematical notation used to bound complexity of an algorithm
* Worst Case: `O()` (upper bound)
* Average Case: `θ()`
* Best Case: `Ω()` (lower bound)

**Order of Growth:**
```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(n³) < O(2^n) < O(n!)
```

⚡ **Always check corner cases:** empty inputs, negatives, overflow/underflow, duplicates.

---
## 🔹 Interview Approach
1. **Clarify** → Rephrase the question, confirm understanding (~2-3 min)
2. **Think Aloud** → Ask for time, brainstorm approaches, share thought process (~5 min)
3. **Plan** → Outline solution steps, dry-run with example, Tell Timme Complexity (~10 min)
4. **Code** → Write clean, working solution (~10 min)
5. **Optimize & Validate** → Discuss complexity, handle edge cases, suggest improvements (~5 min)  

