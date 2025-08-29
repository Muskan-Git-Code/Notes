package DSAPrograms;

import java.util.*;

/*
Stack (LIFO Order): Example: Piles of books.
Queue (FIFO Order): Example: People waiting in ticket line, data transfer between two processes.
In normal Queue, once queue becomes full, we cannot insert the next element even if there is a space in front of queue.
Circular Queue/ Ring Buffer: Stores element in circular manner, and do proper utilization of memory.
Priority Queue: Stores element according to priority.
*/
public class StackQueue {

    /* Create a min stack. */
    // a={-2,0,-3,2}	=> push(5) = a{-2,0,-3,2,5}, pop() = a{-2,0,-3,2}, min()= -3

    // store pairs {(element, minimum till now)} in stack. For example, here pair in stack becomes.. s1 = {(-2,-2), (0,-2), (-3,-3), (2,-3), (5,-3)}


    /* Find index of next grater element */
    //  a[]={73,74,75,71,69,72,76,73}	=> {1,2,6,5,5,6,-1,-1}

    // Start from right to left. So, all the elements in right side are already stored, so pop till top is less than current.
    static int[] nextGtr(int a[]){   // TC: O(n)
        int n= a.length;    Stack<Integer> s= new Stack<Integer>();
        int ans[]= new int[n];  Arrays.fill(ans, -1);

        for(int i=n-1; i>=0; i--){
            while(!s.empty() && a[s.peek()]<=a[i]){ s.pop(); }  //if top is less than pop, as it can't be next greater ele
            if(!s.empty()){ ans[i]=s.peek(); }
            s.add(i);
        }
        return ans;
    }


    /* Find next greater element in circular array. */
    // Same as above, just add the array twice, and then calc.


    /* Find index of previous greater element. */
    // a[]= {73,74,75,71,69,72,76,73}   => {-1,-1,-1,2,3,2,-1,6}

    // Same as above, just run loop from left to right
    static int[] prevGtr(int a[]){   // TC: O(n)
        int n= a.length;    Stack<Integer> s= new Stack<Integer>();
        int ans[]= new int[n];  Arrays.fill(ans, -1);

        for(int i=0; i<n; i++){
            while(!s.empty() && a[s.peek()] <= a[i]){ s.pop(); }  //if top is less than pop, as it can't be next greater ele
            if(!s.empty()){ ans[i]=s.peek(); }
            s.add(i);
        }
        return ans;
    }


    /* Find max rectangular area in histogram. */
    // a[]= {6,2,5,4,5,1,6}     => 12 (i.e. 4*3)

    // Height/ length for each block is given in a[], breadth/ width max possible is by next and previous smaller elements index -1. Then do max(mx, a[j]*(next[j]-prev[j]-1)) to find maximum area. 	O(n)


    /* Find max area of rectangle in binary matrix. */
    // a[][]= {{0,1,1,0},{1,1,1,1},{0,0,1,1},{1,1,0,0}}	=> 6

    // Take each row as histogram, then find max area for each row. Also, height of each col is sum of all rows above it (Think it like an histogram).


    /* Count square submatrices with all ones. */
    // a[][]= {{0,1,1,1},{1,1,1,1},{0,1,1,1}}   => 15

    // check if (left, up, diagLeftUp) are 1, then valid square.
    public int countSquares(int[][] matrix) {
        int m= matrix.length, n= matrix[0].length;
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][j]==0){    continue; }
                matrix[i][j]= 1+ Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1]));
        }}

        int sum=0;      for(int i=0; i<m; i++){     for(int j=0; j<n; j++){     sum+= matrix[i][j]; }}          return sum;
    }


    /* Find how much water can be trapped in between the walls. */
    // a[]= {0,1,0,2,1,0,1,3,2,1,2,1}	=> 6

    // Find max wall in both left and right side for each cell. And then water trapped between them is (smaller among them - length of curr wall on top of which water is there).
    static int trapp(int a[]){
        int n= a.length, mx=-1, ct=0;
        int vl[]= new int[n], vr[]= new int[n];

        // largest from left
        for(int i=0; i<n; i++){ mx= Math.max(mx, a[i]); vl[i]=mx; }

        // largest from right
        mx= -1;
        for(int i=n-1; i>=0; i--){  mx= Math.max(mx, a[i]); vr[i]=mx; }

        for(int i=0; i<n; i++){     // check max water trap
            if(vl[i]!=-1 && vr[i]!=-1){ ct+= Math.min(vl[i], vr[i]) - a[i]; }
        }
        return ct;
    }


    /* Sum of subarrays min: Find sum of minimum element of each subarray possible in arr[]. */
    // arr[]= {3,1,2,4} => 17 {3*1+ 1*6+ 2*2+ 4*1}

    // find left and right length from nearest next and prev smallest index, and calc number of subarrays.
    public int sumSubarrayMins(int[] arr) {
        int n= arr.length, sum= 0;  int MOD = 1000000007;   // MOD= 1e9+7
        Stack<Integer> stack= new Stack<>();    int[] left= new int[n];     int[] right= new int[n];

        // length from prev nearest smaller (excluding it) till curr ele
        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){   stack.pop(); }
            left[i]= stack.isEmpty() ? i+1 : i-stack.peek();
            stack.push(i);
        }

        // length from curr ele till excluding next nearest smaller element on the right for each element
        stack.clear();
        for (int i=n-1; i>=0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){     stack.pop(); }
            right[i]= stack.isEmpty() ? n-i : stack.peek()-i;
            stack.push(i);
        }

        // Calculate the contribution of each element to the final sum
        for(int i=0; i<n; i++) {
            long ans= (long)arr[i] * (left[i]*right[i]);
            sum= (sum+ (int)(ans%MOD) )%MOD;
        }
        return sum;
    }


    /* Return string after deleting all adjacent duplicates. */
    // s= "DBAABDAB"   => "AB"

    // put in stack, if last ele is also same then pop it and move, else add it into stack.
    static String adj(String s){
        Stack<Integer> stack= new Stack<>();    int n= s.length();    String res="";
        for(int i=0; i<n; i++){
            if(!stack.isEmpty() && s.charAt(i)==s.charAt(stack.peek()) ){ stack.pop(); }
            else{   stack.push(i); }
        }

        // now remaining stack ele are ans
        while(!stack.empty()){  res+= s.charAt(stack.pop()); }
        return new StringBuilder(res).reverse().toString();
    }


    /* Construct smallest number possible for given DI (desc, inc) string, given we can’t reuse the number. */
    // pattern= "IIIDIDDD"	=> "12354876"

    // Push for all operation, Pop from stack when order is I, so that for D we get values in reverse.
    static String diStr(String str){
        Stack<Integer> s= new Stack<>();    int n= str.length(), ct=1;    String res="";
        for(int i=0; i<n; i++){
            s.push(ct); ct++;
            if(str.charAt(i)=='I'){
                while(!s.empty()){  res+= s.pop().toString(); }
            }
        }

        while(!s.empty()){  res+= s.pop().toString(); }
        return res;
    }


    /* Return smallest possible digit after removing k digits. */
    // str= 1432219, k=3  => 1219

    // Store in stack. Pop if s.top()>str[i] and k is there.
    static String num(String str, int k){
        Stack<Integer> s= new Stack<>();  String res= "";
        for(int i=0; i<str.length(); i++){
            if(!s.empty() && str.charAt(i)<str.charAt(s.peek()) && k>0){  s.pop();    k--; }
            s.push(i);
        }

        while(!s.empty()){  res+= str.charAt(s.pop()); }
        return new StringBuilder(res).reverse().toString();
    }


    /* Return lexographical smallest string. Given string s and empty string t, on which 2 operations allowed i.e. removing first character from s append to t, or remove last char from t and add to ans. */
    // s="bacdfghae"    => "aaehgfdcb"

    // if value at t.peek() <= smallest till now from right side. Then include in ans.
    public String robotWithString(String s) {
        StringBuilder ans= new StringBuilder();   int n= s.length();    Stack<Character> t= new Stack<>();
        char str[] = new char[n];   str[n-1]= s.charAt(n-1);

        // smallest in right till now
        for(int i=n-2; i>=0; i--){  str[i]= (char)Math.min(s.charAt(i), str[i+1]); }

        for(int i=0; i<n; i++){
            t.push(s.charAt(i));
            while(!t.isEmpty() && t.peek() <= ( (i+1<n) ? str[i+1] : 'z'+1 ) ){     ans.append( t.pop() ); }
        }

        while(!t.isEmpty()){    ans.append( t.pop() ); }
        return ans.toString();
    }


    /* Return longest valid parenthesis substring. */
    // str= "()(()))))" => "()(())"

    // If '(' then push index else pop it. After poping if stack becomes empty then push current index into stack else count the maximum length.
    static String paranthesis(String str){
        int mx=0, startIx=-1;
        Stack<Integer> s= new Stack<>();    s.push(-1);
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='('){ s.push(i); }
            else{
                s.pop();
                if(s.empty()){  s.push(i); }
                else{   // calc max length
                    if(mx < i-s.peek()){    startIx= s.peek()+1;    mx= i-s.peek(); }
            }}
        }
        return str.substring(startIx, startIx+ mx);
    }


    /* Return if its a valid parenthesis, given a wild character ‘*’ which can have either of (, ), <empty> */
    // s= "()((*)))"    => true

    // take 2 stack, one for paranthesis, other for *. While pop, if any of stack have value pop it. Also, in end check for remaining stack values if pair exist then true.
    static boolean validPar(String s){
//        int n= s.length();
        Stack<Integer> st= new Stack<>();   Stack<Integer> ast= new Stack<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='('){ st.push(i); }
            else if(s.charAt(i)=='*'){  ast.push(i);}
            else{
                if(!st.empty()){    st.pop(); }
                else if(!ast.empty()){  ast.pop(); }
                else{   return false; }
        }}

        while(!st.empty()){
            if(!ast.empty() && st.peek()<ast.peek()){   st.pop();   ast.pop(); }
            else{   return false; }
        }
        return true;
    }



    /* QUEUE */

    /* Return cards deck to reveals cards in increasing order, given we need to pick a card and next card need to go at bottom. */
    // cards[]= \  => {2,13,3,11,5,17,7}

    // sort cards. Take cards in reverse order, such that each time last value comes to front, along with adding new card, for sorted order.
    static int[] deck(int cards[]){
        int n= cards.length;    Deque<Integer> dq= new LinkedList<>();
        int ans[]= new int[n];  Arrays.fill(ans, -1);

        Arrays.sort(cards);
        for(int i=n-1; i>=0; i--){
            if(!dq.isEmpty()){  dq.addFirst(dq.removeLast()); }
            dq.addFirst(cards[i]);
        }

        for(int i=0; i<n; i++){ ans[i]= dq.getFirst(); }
        return ans;
    }


    /* Find kth smallest element */
    // a[]= {12,3,5,7,4,19,26}, k=3     => 5

    // Make maxHeap, if heapSize>k then remove. In end we left with k smallest elements.
    static int kthSmall(int a[], int k){    // TC: O(nlogk)
        PriorityQueue<Integer> pq= new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0; i<a.length; i++){
            pq.add(a[i]);
            if(pq.size()>k){    pq.remove(); }
        }
        return pq.peek();
    }


    /* Find kth largest element. */
    // Same as kth smallest element, just take minHeap instead of max.


    /* Return k largest elements in array. */
    // a[]= {12,3,5,7,4,19,26}, k=3     => {3,4,5}

    // instead of returning top element, return all elements left in queue.


    /* Sort nearly sorted array a[n], in which each element is atmost k distance away from its target position. */
    // a[]= {10,9,8,7,4,70,60,50}, k=4  => {4,7,8,9,10,50,60,70}

    // Make minHeap, after traversing k elements print topmost/ minimum element.


    /* Find k closest element to x. */
    // a[]= {5,6,7,8,9}, k=3, x=7   => {8,6,7}

    // Make max heap(abs diff of x and a[i], a[i]). Remove values if heap size is more than k
    static class closestEle {
        static class Pair {
            int a;  int b;
            Pair(int a, int b) {    this.a = a; this.b = b; }
        }

        static int[] closest(int a[], int k, int x){
            int ans[] = new int[a.length];
            PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
                if (p1.a != p2.a) { return Integer.compare(p2.a, p1.a); }
                return Integer.compare(p2.b, p1.b);
            }); // sorting in desc order
            for (int i = 0; i < a.length; i++) {
                pq.add(new Pair(Math.abs(x - a[i]), a[i]));
                if (pq.size() > k) {    pq.remove(); }
            }

            for (int i = 0; i < pq.size(); i++) {   ans[i] = pq.remove().b; }
            return ans;
        }
    }


    /* Find top k frequent elements. */
    // a[]= {3,1,4,4,5,2,6,1}, k=2  => (4,1)

    // create map for finding freqency of each number, and then store into MaxHeap (freq, value)


    /* Return difference between 1st and last part of array after removing n elements from array of size 3n. */
    // nums[]= {7,9,5,8,1,3}    => 1 as {7,5,8,3}

    // Minimize diff (minStart - maxEnd). For 1st half; Take min from [0,2n] i.e. add [0,n] then calc diff along with removing top n ele from [n,2n]. For 2nd half; Take max from [n,3n] i.e. add [2n,3n] then calc diff along with removing top n ele from [2n,n]. And return min diff as ans.
    public long minimumDifference(int nums[]) {     // O(nlogn)
        int n3= nums.length;    int n= n3/3;    long diff[]= new long[n+1];   long sum=0;
        PriorityQueue<Integer> maxHeap= new PriorityQueue<>( (p1,p2)->{    return Integer.compare(p2,p1); } );   // Max heap
        PriorityQueue<Integer> minHeap= new PriorityQueue<>();  // minHeap

        // Build left min elements from maxHeap
        for(int i=0; i<n; i++){     maxHeap.add(nums[i]);   sum+= nums[i]; }
        for(int i=n; i<=n*2; i++){  diff[i-n]= sum;     maxHeap.add(nums[i]);   sum+= nums[i] - maxHeap.remove(); }   // diff[0], diff[1],..

        // Build right max elements
        sum=0;
        for(int i=2*n; i<3*n; i++){  minHeap.add(nums[i]);      sum+= nums[i]; }
        for(int i=n*2 -1; i>= n-1; i--){    diff[i-(n-1)] -= sum;      minHeap.add(nums[i]);   sum+= nums[i] - minHeap.remove(); }    // diff[n], diff[n-1],..

        long mn= Long.MAX_VALUE;    for(int i=0; i<=n; i++){    mn= Math.min(mn, diff[i]); }
        return mn;
    }


    /* Return min cost to connect n ropes, such that cost of connecting 2 ropes is equal to sum of their lengths. */
    // a[]= {4,3,2,6} => 29 (i.e. heap={2,3,4,6}, 2+3=5, 4+5=9, 6+9=15  => cost=5+9+15 =29 )

    // Each time add 2 ropes which are min. Then pop them and add their sum instead.
    static int ropes(int a[]){
        int n= a.length, res=0;
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        for(int i=0; i<n; i++){ pq.add(a[i]); }

        while(pq.size()>=2){
            int x1= pq.remove();  int x2= pq.remove();
            pq.add(x1+x2);  res= x1+x2;
        }
        return res;
    }


    /* Minimize the max distance between sorted gas stations a[], after placing k more stations between them. */
    // a[]= {1,2,3,4,5}, k=2    => 1 (i.e. {1,1.5,2,2.5,3,4,5})

    // MaxHeap(dis, origDist, ct). Each time pick max dist value i.e. top value of heap, add gasStation there, and modify dist, ct
    static class GasStations{
        static class Pair{
            double dist;    double origDist;    int ct;
            Pair(double dist, double origDist, int ct){  this.dist=dist;   this.origDist=origDist; this.ct=ct; }
        }

        static double maxDist(int a[], int k){
            int n= a.length;
            PriorityQueue<Pair> pq= new PriorityQueue<>( (p1, p2) -> {  return Double.compare(p2.dist, p1.dist); } );

            for(int i=1; i<n; i++){ double gap= a[i]-a[i-1];    pq.add(new Pair(gap, gap, 1)); }

            for(; k>0; k--){
                Pair val= pq.remove();
                val.ct++;   val.dist= val.origDist/ val.ct;
                pq.add(val);
            }
            return pq.peek().dist;
        }
    }


    public static void main(String args[]){
        int a[]= {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] x= nextGtr(a);

        x= prevGtr(a);

        for(int i=0; i<a.length; i++){
            System.out.print(x[i] +" ");
        }

        System.out.println(trapp(a));

        String s="()((*)))"; int k=3;
        System.out.println(validPar(s));

    }
}
