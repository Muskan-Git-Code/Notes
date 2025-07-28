package DSAPrograms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    static int binarySearch(int a[], int l, int r, int x){      // O(logn)

        while(l<=r){
            int m= (l+r)/2;
            if(a[m]==x){    return m; }
            else if(a[m]<x){    l= m+1; }
            else{   r= m-1; }
        }
        return -1;
    }


    /* Find 1st occurrence of an element in a sorted array. */

    // if found an occurrence then store index, and move left to find if there is any other index
    static int firstOccurrence(int a[], int x){

        int l=0, r=a.length-1, res=-1;
        while(l<=r){
            int m= (l+r)/2;
            if(a[m]==x){    res=m;  r=m-1; }
            else if(a[m] > x){   r= m-1; }
            else{   l = m+1; }
        }
        return res;
    }


    /* Find lower bound of a number */
    // Element just >= currentNumber is lower bound. Upper bound > currentNumber. So, check if a[m]>=x store value and move left, else right


    /* Find count of an element in a sorted array. */
    // Find 1st and last index of occurrence of element. Then subtract to find count.


    /* Find element x in sorted infinite array. */
    // take r till x is not included
    static int infiniteArr(int a[], int x){
        int l=0, r=1;
        while( r< a.length && a[r] < x ){   l=r;    r= r*2; }

        if(r>= a.length){   r = a.length-1; }
        return binarySearch(a, l, r, x);
    }


    /*  Return index of min element in a rotated sorted array.
        or
        Find number of times array is rotated. */
    // a[]= {4,5,6,7,0,1,2}     => 4

    // min element is always present in unsorted half
    static int rotatedSort(int a[]){
        int l=0, r= a.length-1, res=-1;
        while(l<r){
            int m= (l+r)/2;
            if(a[m]>a[r]){  l = m+1; }
            else{   r=m;  res=m; }
        }
        return res;
    }


    /* Find an element in rotated sorted array */
    // find min element as array is sorted before and after min element. Then implement binary search for that element.


    /*  Return peak element (element which is strictly greater than its neighbor elements).
        OR
        Find maximum element in bitonic (monotonically increasing then decreasing) array.  */
    // a[] = {1,3,8,12,4,2}     => 12

    // Each time compare mid with neighbors, if greater then that's the ans, else if inc order then l = m+1, else r=m-1
    static int bitonic(int a[]){
        int l=0, r= a.length-1;
        while(l<=r){
            int m= (l+r)/2;
            if(a[m] > a[m+1] && a[m] > a[m-1]){ return a[m]; }
            else if(a[m-1] < a[m] && a[m] < a[m+1] ){   l = m+1; }
            else{   r = m-1; }
        }
        return -1;
    }


    /* Find an element in bitonic array */
    // Array is sorted before and after peak element.


    /* Find max no of 1, in a row in a 0-1 matrix, given rows are sorted. */
    // a[]= {{0,0,1,1},{0,0,0,0},{0,1,1,1}}     => 3

    //Rows are sorted, that means firstly zero then one. If at ith row, we found 1, then after that all are one. Also, no need to process all columns as col with more zeroes are already rejected.
    static int sortedMatrix(int arr[][]){      // TC: O(m+n)
        int mx=0, m= arr.length, n= arr[0].length, indexj=Integer.MAX_VALUE;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j] == 1){ mx = Math.max(mx, n-j);     indexj=j;   break; }
                if(j > indexj){     break; }
        }}
        return mx;
    }


    /*  Find a target in a row, column wise sorted matrix. */
    // a[]= {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, target=13	=> true {target found at (3,2)}

    // Row sorted means, if(row[1st col] <= target <= row[last col]), apply binary search to check if target exist. Column sorted means, if for a row[1st col] > target then below all rows will also be greater and target can't be found below.


    /* Find a median in a row wise sorted matrix. */
    // a[]= {{1,5,7,9,11}, {2,3,4,5,10}, {9,10,12,14,16}}	=> 9 {make 1D and then see middle value}

    // Median exist between 1-16 i.e. lowest in row[1st col] and highest in row[last col]. Now, each time find mid, apply binary search in each row to get ele smaller or larger than mid. If middle value then that's the ans, else if number of smaller values are more then r=mid-1, else right.

    /* Binary Search on answers: If we are asked to find min or max value, and we know a definite range answer might lie and after/before a particular value all are answers from which we need to find min/max value.
     */

    /* Find nth root of a number m. */
    // m=16, n=4    => 2 {16^(1/4)}

    //nth root lie between 1 to m. Binary search with midValue = pow(mid, n), and make range smaller each time till we find ans.


    /* Find smallest divisor of elements in a[], also sum of all elements dividing by that integer is less than threshold t. */
    // a[]= {8,4,2,3}, t=10     => 2

    // A divisor can be between 2 to maxElement. Then binary search.


    /* Find kth missing element in a sorted array. */
    // a[]={2,3,4,7,11}, k=5    => 9 (as missing elements are 1,5,6,8,9,10)

    // Without any number missing, each index contains i+1 value (i.e. 1, 2, 3..). Till, a[3], missing numbers are a[3]-(i+1) =7-4 =3. Similiarly till a[4] missing numbers are a[4]-(i+1) =11-5 =6. So, 5th missing number is a[3]+2 =9.
    // Find 2 numbers among which answer lie through binary search. Then, l + (k - no of missing till l) is ans.


    /* A monkey is given a[i] piles of bananas, having i bananas in each pile. Return minimum number of bananas a monkey need to eat for finishing all the bananas in h hours. Given that in a particular hour a monkey can eat only 1 pile of bananas. */
    // a[]= {3,6,7,11}, h=8  => 4 (1+2+2+3)

    // In 1 hr, monkey can eat all bananas of pile i.e. max 11 bananas, and as slow as 1 banana in a hr. So, apply binary search, see min bananas need to eat, such that time shouldn't exceed h.


    /* Given a[] roses, each blooming at ith day. Return min days required to make m bouquet with k adjacent bloomed roses */
    // a[] = {7,7,7,7,13,11,12,7}, k=3, m=2     => 12

    // Check if we have sufficient roses i.e. k*m<=a.size(). All roses will be bloomed at max day i.e. 13, and min is 7 (when even a single rose is bloomed). So, again binary search from 7-13, checking if possible to make bouque then find lesser day range.


    /* Return minimum capacity the ship should take in order to deliver total weight a[] within d days. */
    // a[]= {5,4,5,2,3,4,5,6}, d=5	=> 9

    // min one parcel ship will carry at a time, or max all (i.e. sum weight) in 1 day i.e. 6-34. Now, binary search to check min required capacity.


    /* Aggressive Cows: Find max possible distance between any two cows, if given an array of n stalls and k cows. */
    // stalls[]= {0,3,4,7,10,9}, k=4    => 3

    // Max-min distance between 2 cows can be (4-3, 10-0) =(1, 10). Sort the stalls, and implement binary search, to find max distance possible to fit k cows.
    static int aggCows(int stalls[], int k){
        Arrays.sort(stalls);
        int l = 1, r= (Arrays.stream(stalls).max().getAsInt() - Arrays.stream(stalls).min().getAsInt()), res=-1;
        while(l<=r){
            int m= (l+r)/2;     // check if possible to place k cows at this distance
            if(isPossible(stalls, k, m)){   res= m;     l= m+1; }
            else{   r= m-1; }
        }
        return res;
    }

    static boolean isPossible(int stalls[], int k, int dist){
        int distSum=0, ctCows=1;    // 1 as keeping 1st cow at a[0]
        for(int i= 1; i<stalls.length; i++){
            distSum+= stalls[i]-stalls[i-1];
            if(distSum>=dist){  ctCows++;   distSum= 0; }
        }
        if(ctCows>=k){  return true; }
        return false;
    }


    /* Book Allocation: Find maximum of minimum number of pages allocated to a student. Given books array a[n] where books are allocated in continuous manner, between m students. */
    // a[]={12,34,67,90}, m=2	=> 113 {2 students can have {(12+34+67), (90)} so that minimum required pages will be allocated to students, and among them maximum pages that will be allocated is 113}

    // Min 1 book will be allocated, max all books can be allocated i.e. ans is between (max ele, sum). Apply binary search, to check ifPossible() to allocate then store ans and find smaller range.


    /* Painter’s Partition: Given n walls[], taking i hrs to be painted. Find maximum of minimum time required by k painters to paint all walls in continuous manner. */
    // walls[]= {10,20,30,40}, k=2      => 60 {make partition as (10, 20, 30), (40) }

    // Exactly same as Book Allocation


    /* Largest Subarray Sum Minimized: Split array a[] into k non-empty subarrays such that the largest sum of any subarray is minimized. */
    // a[]= {10,20,30,40}, k=2      => 60 {partition as (10,20,30), (40)}

    // Exactly same as Book Allocation problem. Range= (max ele, sum)


    public static void main(String args[]){

    }
}
