package DSAPrograms;

import java.util.*;


/*
2 pointer Technique: It can be:
    - Opposite direction 2 pointer: Taking 2 pointers at opposite end.
    - Equi-directional: taking 2 pointers in same direction. It can be Sliding window Fixed size, Variable size (based on condition find max/ min, count in substring/ subarray).
*/

public class TwoPointer {

/*--------------------------------------------------------------------------------------------------                   OPPOSITE DIRECTION 2 POINTER APPROACH FOR FIXED SIZE SUBARRAY -------------------------------------------------------------------------------------------------*/

    /* Print 2 numbers in a[] which gives sum k */

    //sort, then check if sum greater than required then dec sum by removing last digit and vise versa.
    static void sumK(int a[], int k){   // TC: O(nlogn)
        int l=0, r= a.length-1;     Arrays.sort(a);
        while(l<r){
            int val = a[l]+a[r];
            if(val==k){     System.out.println(a[l]+ " "+ a[r]); }
            else if(val>k){     r--; }
            else{    l++; }
        }
    }


    /* Print all unique triplets which gives sum as target. */

    // sort, take outer loop, then 2 pointer approach as above.
    static void triplet(int a[], int target){
        int n= a.length;    Arrays.sort(a);
        for(int k=0; k<n; k++){
            int i= k+1, j=n-1;
            while(i<j){
                int val= a[i]+ a[j]+ a[k];
                if(val==target){    System.out.println(a[i]+ " "+ a[j]+ " "+ a[k]); }
                else if(val>target){    j--; }
                else{   i++; }
            }
        }
    }


    /* Return index of 2 numbers which gives sum k. */

    // store (value, index) in map, and check if k-a[i] present in map, then that's the ans.
    static void sumk2(int a[], int k){
        HashMap<Integer, Integer> map= new HashMap<>();
        for(int i=0; i<a.length; i++){
            if(map.containsKey(k-a[i]) ){   System.out.println(map.get(k-a[i])+ " "+ i);    break; }
            map.put(a[i], i);
        }
    }


    /* Find total number of non-empty subsequence having (min+ max) value less than equal to k. */

    // sort the array. Now, min value for any window is a[l], and max is a[r]. Check if sum greater than k, then dec right for fit in window, else l++.
    int mnmxSum(int a[], int k){
        int l=0, r= a.length -1, res=0;

        Arrays.sort(a);
        while(l<=r){
            if(a[l]+a[r] > k){  r--; }
            else{   res+= Math.pow(2, r-l);     l++; }
        }
        return res;
    }



/*--------------------------------------------------------------------------------------------------                        EQUI DIRECTION 2 POINTER APPROACH FOR FIXED SIZE SUBARRAY --------------------------------------------------------------------------------------------------*/


    /* Find max sum in a subarray of size k. */
    // a[]= {1,4,2,10,2,3,1,0,20}, k=3  => 21 i.e. last window (1,0,20)

    // iterate array, make window, such that while moving forward remove last taken element from sum.
    static int subarrSum(int a[], int k){   // O(n)
        int sum=0, i=0, j=0, mx=Integer.MIN_VALUE;
        for(; j<k; j++){    sum+= a[j]; }   // first window
        mx = Math.max(mx, sum);

        while(j<a.length){
            sum+= a[j]- a[i];   j++; i++;
            mx = Math.max(mx, sum);
        }
        return mx;
    }


    /* Print values which gives max in sliding window of size k. */
    // store index which give max till now, and in end print values through index.


    /* Find max earned points by taking k elements either from start or end. */
    // a[]= {3,2,3,4,5,6,1}, k=3    => 12 (1,6,5)

    // Take exactly k elements, so lets take all k elements from start, then dec one by one and take end ones instead to find max.
    static int maxpt(int a[], int k){   // O(n)
        int mx=Integer.MIN_VALUE, sum=0, i=0, j=a.length-1;
        for(; i<k; i++){    sum+= a[i]; }   i--;   // first window
        mx= Math.max(mx, sum);

        while(i>=0){
            sum+= a[j]- a[i];   i--; j--;
            mx= Math.max(mx, sum);
        }
        return mx;
    }


    /* Find median in sliding window of size k. */
    // a[]= {1,3,-1,-3,5,3,6,7}, k=3    => {1,-1,-1,3,5,6}

    // taking min and max heap, each time move element to min heap, then to max heap, then if maxheap size is greater than min heap so move element back to min heap. Store all values outside window in a map, and if top value is in map then remove it, and balance map again.
    class MedianQues {
        static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        static Map<Integer, Integer> removedVals = new HashMap<>();

        static List<Integer> median(int a[], int k) {
            List<Integer> res = new ArrayList<>();
            int i = 0, j = 0;

            for (; j < k; j++) {    addNum(a[j]); } // first window
            res.add(getMedian(k));

            while (j < a.length) {
                addNum(a[j]);   removeNum(a[i]);    i++;    j++;
                res.add(getMedian(k));
            }
            return res;
        }

        static void addNum(int val) {
            minHeap.add(val);
            maxHeap.add(minHeap.remove());
            if (maxHeap.size() > minHeap.size()) {  minHeap.add(maxHeap.remove()); }
        }

        static void removeNum(int val) {
            removedVals.put(val, removedVals.getOrDefault(val, 0) + 1);     cleanHeap(maxHeap);     cleanHeap(minHeap);

            while (minHeap.size() > maxHeap.size()) {   maxHeap.add(minHeap.remove()); }
            while (maxHeap.size() > minHeap.size()) {   minHeap.add(maxHeap.remove()); }
        }

        static void cleanHeap(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty() && removedVals.containsKey(heap.peek())) {
                int top = heap.peek();
                removedVals.put(top, removedVals.get(top) - 1);
                if (removedVals.get(top) == 0) {    removedVals.remove(top); }
                heap.remove();
            }
        }

        static int getMedian(int k) {
            if (k % 2 == 1) {   return minHeap.peek(); }
            else {  return (maxHeap.peek() + minHeap.peek()) / 2; }
        }
    }


    /* Find minimum swaps to group all 1’s together in a circular array. */
    // nums[]= {0,1,1,1,0,0,1,1,0}	=> 2

    // Count number of 1's, that will be size of sliding window. Find max 1's in a sliding window. So, ans= (size of window) - (max 1's). For any circular array problem, append the array twice.



/*--------------------------------------------------------------------------------------------------                        VARIABLE SIZE WINDOW --------------------------------------------------------------------------------------------------*/

    /* Kadane's Algorithm: Find max sum subarray. */

    // Iterate through array, add values in current sum, if sum goes -ve then make cs=0, else continue counting sum.
    static int maxSum(int a[]){
        int mx= Integer.MIN_VALUE, cs=0;
        for(int i=0; i<a.length; i++){
            cs+= a[i];      mx= Math.max(mx, cs);
            if(cs<0)    cs=0;
        }
        return mx;
    }


    /* Find max product subarray. */
    // a[]= {2,3,-2,4}  => 6 (2,3)

    // for product, we need to consider from both sides for -ve scenarios. For 0 values, subarray starts again from there.
    static int product(int a[]){
        int mx= Integer.MIN_VALUE, pref=1, suf=1, n= a.length;
        for(int i=0; i<n; i++){
            pref*= a[i];    suf*= a[n-1 -i];
            mx= Math.max(mx, Math.max(pref, suf));
            if(pref==0) pref=1;     if(suf==0)  suf=1;
        }
        return mx;
    }



    /*--------------------------------------------------------------------------------------------------VARIABLE SIZE WINDOW with (Condition <=k): For it follow pattern {operation, violated condition, calculate ans} --------------------------------------------------------------------------------------------------*/

    /* Return max no of consecutive ones, if can flip utmost k zeroes.
       OR
       Return max substring length having no of zeroes <= k.
    */
    // a[]= {1,1,1,0,0,0,1,1,1,1,0}, k=2    => 6

    // length = currentIx - (an index before till i need to ct) =j-(i-1)
    static int consOnes(int a[], int k){    // O(n)
        int i=0, j=0, mx=0, zeroesCt=0;
        for(;j<a.length;j++){
            if(a[j]==0) zeroesCt++;

            while(zeroesCt>k && i<=j){
                if(a[i]==0) zeroesCt--;
                i++;
            }
            mx= Math.max(mx, j-(i-1));
        }
        return mx;
    }


    /* Collect max no of fruits from continous trees in 2 baskets, given you can collect only 1 type of fruit in a basket.  */
    // a[]= {0,1,2,2}   => 3

    // pluck from continous trees, fruits i.e. k <= 2
    static int maxFruits(int a[]){
        int mx=0, i=0, j=0, fruitsCt=0;
        Map<Integer, Integer> map= new HashMap<>();

        for(;j<a.length; j++){
            map.put(a[j], map.getOrDefault(a[j], 0)+1);

            while(map.size()>2 && i<=j){
                map.put(a[i], map.get(a[i])-1);
                if(map.get(a[i])==0){    map.remove(a[i]); }
                i++;
            }

            mx= Math.max(mx, j-(i-1));
        }
        return mx;
    }


    /* Return total subarrays having product less than k. */
    // a[]= {10,5,2,6}, k=100    => 8 as {{10}, {5}, {2}, {6}, {10,5}, {5,2}, {2,6}, {5,2,6}}

    // product<k
    static int prodSubarrays(int a[], int k){
        int res=0, i=0, prod=1;
        for(int j=0; j<a.length; j++){
            prod*= a[j];

            while(prod>=k && i<=j){     prod/= a[i];    i++; }

            res+= j-(i-1);  // //ct+= (j-i)+1, as to calculate ct for each individual array and a combined complete array from j till i.
        }
        return res;
    }


    /* Return longest substring length without repeating characters. */
    // s= "abcabcbb"    => 3

    // charCt<2
    static int repeatChar(String s){
        int mx=0, i=0, j=0, repeatCt=0;     Map<Character, Integer> map= new HashMap<>();

        for(; j<s.length(); j++){
            if(map.containsKey(s.charAt(j))){   repeatCt++; }
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1);

            while(repeatCt!=0 && i<=j){
                if(map.get(s.charAt(i))>1){ repeatCt--; }
                map.put(s.charAt(i), map.get(s.charAt(i))-1);
                i++;
            }
            mx= Math.max(mx, j-(i-1));
        }
        return mx;
    }


    /* Return length of longest same char substring, if allowed to replace max k characters. */
    // s= "ABABBA", k=1     => 4 (i.e. BABB)

    // character is either same, or k diff allowed. Means maxFreq element+ k values
    static int sameChar(String s, int k){   // TC: O(n) as i, j move only forward once.
        int mx=0, i=0, j=0, maxf=0;
        Map<Character, Integer> map= new HashMap<>();

        for(; j<s.length(); j++){
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j),0)+1);
            maxf= Math.max(maxf, map.get(s.charAt(j)) );

            while((j-(i-1))-maxf > k  && i<=j){
                map.put(s.charAt(i), map.get(s.charAt(i))-1);
                if(map.get(s.charAt(i))==0){    map.remove(s.charAt(i)); }
                i++;
            }

            mx= Math.max(mx, j-(i-1));
        }
        return mx;
    }


    /* Find kth smallest distance in nums. Given distance among any two pairs is abs diff between them. */
    // a[]= {1,2,4,6,7}, k=5    => 3

    // Binary Search on ans+ 2 pointers
    // required result is between (min, max) dist. Each time if countPairs<=mid, then that's a possible ans, else found in left side.
    static int pairFn(int a[], int k){
        Arrays.sort(a);
        int res=0, mn= Integer.MAX_VALUE, n=a.length;   int mx= a[n-1]-a[0];
        for(int i=1; i<n; i++){ mn= Math.min(mn, a[i]-a[i-1]); }
        int l=mn, r= mx;
        while(l<=r){
            int mid= (l+r)/2;
            if(isPossible(mid, a, k)){   res= mid; r= mid-1; }
            else{   l= mid+1; }
        }
        return res;
    }

    // no of small dist <= mid, and that ct>=k
    static boolean isPossible(int mid, int a[], int k){
        int i=0, ct=0;
        for(int j=0; j<a.length; j++){
            int dist= a[j]-a[i];
            while(dist>mid){    i++;    dist= a[j]-a[i]; }
            ct+= j-i;   // pairs with lesser dist
        }

        if(ct>=k)   return true;
        return false;
    }


    /* Count subarrays which contains window in which min/ max value is mink, maxk. */
    // a[]= {1,3,5,2,7,5}, mink=2, maxk=5   => 2 {as pairs (1,3,5), (1,3,5,2)}

    // if have req window, then count no of subarrays.
    static int partWindow(int a[], int mink, int maxk){
        int i=0, mini=-1, maxi=-1, ct=0;
        for(int j=0; j<a.length; j++){
            if(a[j]==mink){ mini=j; }   if(a[j]==maxk){ maxi=j; }

            if((a[j]<mink || a[j]>maxk) && i<=j){   mini=-1; maxi=-1;   i=j+1; }

            if(mini!=-1 && maxi!=-1){   ct+= Math.min(mini, maxi) -i +1; }  //i.e. only value till both of them are present can be ans
        }
        return ct;
    }


    /* Min operations to reduce x to 0, given we can reduce either leftmost or rightmost value. */
    // a[]= {1,1,4,2,3}, x=5    => 2

    // Check for longest subarray which have sum total-x. So remaining is the shortest to sum x.
    static int minOper(int a[], int x){ // TC: O(n)
        int i=0, mx=-1, sum=0, n= a.length, total=0;
        for(int j=0; j<n; j++){ total+= a[j]; }

        for(int j=0; j<a.length; j++){
            sum+= a[j];
            while(sum> total-x && i<=j){    sum-= a[i]; i++; }
            if(sum== total-x){  mx= Math.max(mx, j-(i-1)); }
        }
        return (mx==-1) ? -1 : n-mx;
    }



    /*--------------------------------------------------------------------------------------------------VARIABLE SIZE WINDOW with (count==k) condition: This is for count only. return subarray(<=k) - subarray(<=k-1)--------------------------------------------------------------------------------------------------*/

    /* Return number of subarrays having exactly k distinct elements. */
    // a[]= {1,2,1,2,3}, k=2    => 7 {(1,2), (2,1), (1,2), (2,3), (1,2,1), (2,1,2), (1,2,1,2)}

    // subarrays having distinct element <= k. Then, ct+= (j-(i-1))


    /* Return number of subarrays having k odd number in it. */
    // a[]= {1,1,2,1,1}, k=3    => 2

    // Exactly k odd i.e. subarray(<=k odd)- subarray(<=k-1 odd). Check if odd number then do the count.



    /*--------------------------------------------------------------------------------------------------VARIABLE SIZE WINDOW with (condition > k): ans= all who contains min window {operation, (ct,shrink)} --------------------------------------------------------------------------------------------------*/

    /* Return number of substrings containing atleast 3 characters i.e. a, b,c */
    // s= "abcabc"  => 10

    // ct+= n-j; as all who contains min window is ans.
    static int charSubstring(String s){
        int ct=0, i=0, n= s.length();
        Map<Character, Integer> map= new HashMap<>();

        for(int j=0; j<n; j++){
            if(s.contains("a") || s.contains("b") || s.contains("c") ){ map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1); }

            while(map.containsKey('a') && map.containsKey('b') && map.containsKey('c')){
                ct+= n-j;   map.put(s.charAt(i), map.get(s.charAt(i))-1);
                if(map.get(s.charAt(i))==0) map.remove(s.charAt(i));
                i++;
            }
        }
        return ct;
    }


    /* Return total subarrays where largest element in array appears atleast k times. */
    // a[]= {1,3,2,3,3}, k=2    => 6

    // all subarrays (i.e. n-j) having (mxEleCt>=k)
    static int maxEle(int a[], int k){
        int i=0, ct=0, mxCt=0, n= a.length, mxFreq= Arrays.stream(a).max().getAsInt();

        for(int j=0; j<n; j++){
            if(a[j]==mxFreq){  mxCt++; }

            while(mxCt>=k && i<=j){
                ct+= n-j;
                if(a[i]==mxFreq){  mxCt--; }
                i++;
            }
        }
        return ct;
    }

    /* Find min window substring of s which includes t. */
    // s= "ADOBECODEBANC", t= "ABC"     => "BANC"

    //add values of t in map, and for s dec same. If found all t values (managed by ct variable), then calc min.
    static String window(String s, String t){
        int ct=0, mn=Integer.MAX_VALUE, i=0, mini=-1;
        Map<Character, Integer> map= new HashMap<>();

        for(int j=0; j<t.length(); j++){    map.put(t.charAt(j), map.getOrDefault(t.charAt(j), 0)+1); }

        for(int j=0; j<s.length(); j++){
            if(map.getOrDefault(s.charAt(j), 0)>0){ ct++; }
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j),0)-1);

            while(ct>=t.length() && i<=j){
                if((j-(i-1))<mn){   mn= j-(i-1); mini=i; }
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
                if(map.get(s.charAt(i))>0){ ct--; }
                i++;
            }
        }
        return s.substring(mini, mini+mn);
    }


    /*  Find the smallest range that can include atleast 1 number from each of the given lists. */
    // a[][]= {{4,10,15,24,26},{0,9,12,20},{5,18,22,30}}	=> {20,24}

    // Add all in single list as (value, listNo). Then find min window having each list number.



    /* Prefix sum: Concept used if require recompilation/ previous sum, or not able to find window directly. {operation, calculate answer, add new prefix value in mp}. */

    /* Count subarrays whose sum equals k, negative values allowed. */

    // sumOfSubarray=k, means sum-k=0. So, count subarrays with sum-k values. Iterate through array, check if sum-k value already exist through map, and add the new sum.
    static int subarrCt(int a[], int k){
        int ct=0, sum=0;
        Map<Integer, Integer> map= new HashMap<>(); map.put(0, 1); // (sum, ct)
        for(int i=0; i<a.length; i++){
            sum+= a[i];     ct+= map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return ct;
    }


    /* Count subarray whose sum is divisible by k, negative values allowed. */

    // sum%k=0. Also, for -ve sum, modulo is still same, so while storing in map, we will consider its +ve value i.e. adding k more in it (rem%k = (rem+k)%k).
    static int divis(int a[], int k){
        int sum=0, ct=0, rem=0;
        Map<Integer, Integer> map= new HashMap<>(); map.put(0,1);
        for(int i=0; i<a.length; i++){
            sum+= a[i]; rem= (sum+k)%k;     ct+= map.getOrDefault(rem, 0);
            map.put(rem, map.getOrDefault(rem, 0)+1);
        }
        return ct;
    }


    /* Return max size subarray whose sum equals k, negative values allowed. */

    // this time in map store (sum, index), and return max instead of ct. This time its index, so we can't store 0 if not found. Also, storing only 1st occurence of a sum, so that we have max length in case if sum occurs again.
    static int maxSubarr(int a[], int k){
        int ix=0, sum=0, res=0;
        Map<Integer, Integer> map= new HashMap<>();     map.put(0, -1); // if sum 0, then max index can be from starting.
        for(int i=0; i<a.length; i++){
            sum+= a[i];
            if(map.containsKey(sum-k))  res= Math.max(res, i- map.get(sum-k));  // (currentIndex - index of sum-k)
            if(!map.containsKey(sum))    map.put(sum, i);
        }
        return res;
    }


    /* Return max length binary subarray with equal number of 0 and 1. */

    // no of 0= no of 1, means noOfZero - noOfOne =0
    static int binaryLength(int a[]){
        int mx=0, one=0, zero=0;
        Map<Integer, Integer> map= new HashMap<>(); map.put(0, -1);  //(diff, index)
        for(int i=0; i<a.length; i++){
            if(a[i]==0){    zero++; }else{  one++; }
            int diff= zero-one;

            if(map.containsKey(diff))   mx= Math.max(mx, i-map.get(diff));

            if(!map.containsKey(diff))  map.put(diff, i);
        }
        return mx;
    }


    /* Check if there is a subarray whose sum of elements is multiple of k, and length is atleast 2. */

    // multiple of k, means rem=0 i.e. sum%k=0. Also, length should be >=2.
    static boolean multiple(int a[], int k){
        int rem=0, sum=0;
        Map<Integer, Integer> map= new HashMap<>();

        for(int i=0; i<a.length; i++){
            sum+= a[i]; rem= (sum)%k;
            if(map.containsKey(rem) && i-map.get(rem)>=2){  return true; }
            if(!map.containsKey(sum)){  map.put(rem, i); }
        }
        return false;
    }


    /* Return total subarrays having xor k. */
    // a[]= {4,2,2,6,4}, k=6    => 4

    // xor of subarray=k. So, xor^k=0;
    static int xor(int a[], int k){
        int ct=0, xor=0;
        Map<Integer, Integer> map= new HashMap<>(); map.put(0,1);

        for(int i=0; i<a.length; i++){
            xor^= a[i];
            ct+= map.getOrDefault(xor^k, 0);
            map.put(xor, map.getOrDefault(xor, 0)+1);
        }
        return ct;
    }


    /* Count triplet that can form two subarray (i.e. i to j-1, and j to k) of equal xor. */
    // a[]= {2,3,1,6,7} => 4

    // xor1=xor2, means xor1^xor2=0. Means xor of (i to k)=0, and ctOfTriplets= len = currentIndex - IndexHavingSameXor. And if that same xor came more than once, then len= (currIx - i1)+ (currIx-i2) = ctXor*currIx - (sumOfprevIx).
    static int xorTriplets(int a[]){
        int res=0, xor=0;
        Map<Integer, Integer> ctMap= new HashMap<>();   ctMap.put(0, 1);   // (xor, ct)
        Map<Integer, Integer> ixMap= new HashMap<>();   ixMap.put(0, 0);   // (xor, SumOfIx)
        for(int i=0; i<a.length; i++){
            xor^= a[i];

            if(ctMap.containsKey(xor)){     res+= ctMap.get(xor)*(i) - ixMap.get(xor); }

            ctMap.put(xor, ctMap.getOrDefault(xor, 0)+1);   ixMap.put(xor, ixMap.getOrDefault(xor, 0)+1+i); //+1 as for ix=0 also we need to store.
        }
        return res;
    }


    /* Find number of substrings with atmost one letter occurring odd number of times. */
    // s="aba"  => 4 {a, b, aba, a}

    // no of elements occuring odd no of times <= 1. We can't leave a value directly as further there might be 1 more occurence, so prefix sum.
    // bitmask for bits from a to z, if bit=1 means odd freq. So, 2 valid cases i.e. (prevMask^currMask)=0 means all even, and Integer.bitCount(prevMask^currMask)=1 means one odd.
    static int occOdd(String s){
        int ct=0, curr=0;   Map<Integer, Integer> map= new HashMap<>(); map.put(0,1);
        for(int i=0; i<s.length(); i++){
            curr^= (1<<(s.charAt(i)-'a'));  // toggle curr bit, i.e. found one more occ.

            ct+= map.getOrDefault(curr, 0); // all even case i.e. found curr means prev=curr i.e. prev^curr=0
            for(int j=0; j<26; j++){
                ct+= map.getOrDefault(curr^(1<<j), 0);  // 1 odd i.e. 1 bit set, i.e. curr^prev=(1<<j) => curr^(1<<j) = prev i.e. present in map
            }

            map.put(curr, map.getOrDefault(curr, 0)+1);
        }
        return ct;
    }


    /* Find total submatrix having sum k in 2D matrix. */
    // a[][]= {{0,1,0},{1,1,1},{0,1,0}}, k=0  => 4

    //Find prefix sum row-wise. Then, similar to 1D, check for each column subarray for the count
    static int submatrixSum(int a[][], int k){
        int m= a.length, n= a[0].length, ct=0;

        for(int i=0; i<m; i++){
            for(int j=1; j<n; j++){
                a[i][j]= a[i][j] + a[i][j-1];
            }}

        //Now, for each col start to end, check for all rows and apply find "No. of subarrays with sum k" logic
        for(int colSt=0; colSt<n; colSt++){
            for(int colEnd=colSt; colEnd<n; colEnd++){

                Map<Integer, Integer> map= new HashMap<>(); map.put(0, 1);  int sum=0;
                for(int row=0; row<m; row++){
                    sum+= a[row][colEnd] - (colSt>0 ? a[row][colSt-1] : 0); //(curr)-(prev calculated prefixSum)
                    ct+= map.getOrDefault(sum-k, 0);
                    map.put(sum, map.getOrDefault(sum, 0)+1);
                }
            }}
        return ct;
    }


    // Here, (0, -1) like that cond is added for counting values i.e. from starting till now sum.
    public static void main(String args[]){
        List<Integer> a = Arrays.asList(2,3,1,6,7);
        int k=6, c=2, b=5;
        String s= "aba", t="ABC";
        List<List<Integer>> mat= Arrays.asList(Arrays.asList( 0,1,0), Arrays.asList(1,1,1), Arrays.asList(0,1,0));
        System.out.println(occOdd(s));
    }
}


/* Deque

/* Find shortest subarray length with sum atleast k, negative values allowed.
// a[]= {2,-1,2}, k=2   => 3

//sum>=k, and len is min. Add values in deque to store (prefixSum, ix) inc order maintained, as we have first ele as least prefixSum value till now. If that satisfied then can check for higher index for lesser length.

static class Pair{
    int sum;   int ix;
    Pair(int sum, int ix){ this.sum=sum;   this.ix=ix; }
}
static int shortSubLen(List<Integer> a, int k){ // TC: O(n), SC: O(n)
    int sum=0, mn=Integer.MAX_VALUE;
    Deque<Pair> dq= new LinkedList<>(); dq.addLast(new Pair(0, -1));
    for(int i=0; i<a.size(); i++){
        sum+= a.get(i);

        // take min index till satisfied.
        while(!dq.isEmpty() && sum-dq.getFirst().sum >= k){     mn= Math.min(mn, i- (dq.getFirst().ix));    dq.removeFirst(); }

        // maintain inc order
        while(!dq.isEmpty() && sum<=dq.getLast().sum){  dq.removeLast(); }
        dq.addLast(new Pair(sum, i));
    }
    return mn;
}

* */

/*
  Recursion

  https://leetcode.com/problems/distribute-candies-among-children-ii/?envType=daily-question&envId=2025-06-01

 */


