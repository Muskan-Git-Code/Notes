package DSAPrograms;

import java.util.*;

public class BasicPrograms {

    /* Find sum of digits */
    static int sumOfDigits(int n){    // n= 12345 => sum = 15
        int res = 0;
        while(n != 0){
            res = res + n % 10;     // for reverse, res = res*10 + n%10;
            n= n/10;
        }
        return res;
    }


    /* Rotate matrix to 90 degree clockwise */
    // 1 2 3             1 4 7       7 4 1
    // 4 5 6    =>       2 5 8   =>  8 5 2
    // 7 8 9             3 6 9       9 6 3

    // For most of the matrix questions, swap i, j, and then rotate row-wise will work.
    static void rotateMatrix(int[][] mat){
        int n= mat.length;
        for(int i=0; i<n; i++){     // swap i, j
            for(int j=i+1; j<n; j++){
                int temp = mat[i][j];   mat[i][j] = mat[j][i];  mat[j][i] = temp;
        }}

        for (int[] row : mat) { // rotate row-wise
            for (int i = 0, j = row.length - 1; i < j; i++, j--) {
                int temp = row[i];  row[i] = row[j];    row[j] = temp;
        }}
    }


    /* Check if given string is constructed of appending same string multiple times */
    // s="abcabcabc"    => true

    // Append same string twice, remove 1st and last value and check if string still exist
    boolean checkIfAppendedString(String s){
        String s2 = s+ s;   s2 = s2.substring(1, s2.length()-1);

        if(s2.contains(s)){     return true; }
        return false;
    }


    /* Find odd occuring number */
    //  arr[7] = {12, 12, 14, 90, 14, 14, 14};  => 90
    static int oddOcc(int[] arr){      // xor for checking
        int res = arr[0];
        for(int i=1; i<arr.length; i++){    res^= arr[i]; }
        return res;
    }


    /* Find intersection of 2 arrays. */
    //Add arr1[] in map. Iterate arr2, check if already present in arr1 then that's an intersecting value, and dec count

    static int[] intersect(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();  Map<Integer, Integer> ctMap = new HashMap<>();
        for(int i: arr1){     ctMap.put(i, ctMap.getOrDefault(i, 0)+1); }

        for(int i: arr2){
            if(ctMap.containsKey(i)){
                res.add(i);     ctMap.put( i, ctMap.get(i)-1 );
        }}
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


    /* Return true if it's possible to split the array in consecutive sequence of >= 3 elements. */
    // nums[] = {1,2,3,3,4,4,5,5}    => true

    // Store all in ctMap. Iterate list, and check if (val, val+1, val+2) exist then add new ans (i.e. ansMap(lastval, ans)), else check if (val-1) exist in ansMap, then change lastval in ansMap. If possible then true, else false.


    /* Return maximum number of points which lies on same line in the X-Y plane */
    // points[x][y]= {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}	=> 4

    // Same line, means slope ((y2-y1)/(x2-x1)) will be same. And check for each point, so that parallel lines won't be counted. denominator can't be zero.
    static int maxPoints(int[][] points){
        int mx = 0, n= points.length;

        for(int i=0; i<n; i++){
            HashMap<Integer, Integer> slopeCt = new HashMap<>();

            for(int j=i+1; j<n; j++){
                int x1 = points[i][0], x2 = points[j][0], y1 = points[i][1], y2 = points[j][1];

                if(x2-x1 == 0){     slopeCt.put(Integer.MAX_VALUE, slopeCt.getOrDefault(Integer.MAX_VALUE, 0)+1); }
                else {      int m = (y2 - y1) / (x2 - x1);  slopeCt.put(m, slopeCt.getOrDefault(m, 0) + 1); }
            }
            for(int key: slopeCt.keySet()){     mx = Math.max(mx, slopeCt.get(key)); }
        }
        return mx+1;    //+1 for point acc to which we are calculating
    }


    /* Minimize the maximum height difference in array arr[], given we need to either inc or dec the height of each value by k exactly once. Also, values can’t be negative. */
    // arr[]= {1,5,8,10}, k=2	=> 5

    // sort the array. For min diff, inc min value and dec max value, till full iteration.
    static int minimizeDiff(int[] arr, int k){
        int ans=0, n= arr.length;

        Arrays.sort(arr);
        int mn = arr[0]+k, mx = arr[n-1]-k;
        for(int i=0; i<n-1; i++){   // min value comes from current, and after dec whatever we get.
            mn = Math.min(mn, arr[i+1]-k);     mx = Math.max(mx, arr[i]+k);
            if(mn<0)    continue;
            ans= Math.min(ans, mx-mn);
        }
        return ans;
    }


    /* Return all possible distinct solutions, after placing n queens on a n*n chessboard such that no two queens attack each other i.e. is in same row, col, diagonal. */
    // n=4	=> [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    // Check if no other queen in same row, col, diagonal, then place the queen


    /* Generate all possible subsequence/ subset. */
    // arr[]= {1,2,3}   => {{1}, {2}, {1,2}, {3}, {1, 3}, {2, 3}, {1,2,3}}

    // In subarray/ substring is continuous values, subset/ subsequence can be of non-continuous values.
    static void subseq(int arr[]){ // TC: O(n*(2^n))
        int n= arr.length;
        for(int i=0; i<Math.pow(2, n); i++){    //Total number of subsequences= pow(2,n)-1 = (1<<n)-1
            for(int j=0; j<n; j++){
                if( (i & (1<<j)) != 0){     System.out.print(arr[j] + " "); }
            }
            System.out.println();
        }
    }


    /* Print all possible substrings that can be made after placing space inbetween. */
    // s="abc"	=> {a_b_c, a_bc, ab_c, abc}
    static void possibleSubstr(String s){
        int n= s.length();
        for(int i=0; i<Math.pow(2, n); i++){
            for(int j=0; j<n; j++){
                System.out.print(s.charAt(j));
                if( j!=n-1 && (i&(1<<j)) !=0 ){   // j!=n-1 condition as don't want to print _ after last letter
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }


    /* Print all permutations of a string keeping the sequence but changing cases */
    // s= “abc”	=> abc Abc aBc ABc abC AbC aBC ABC
    static void permut(String s){
        int n= s.length();
        for(int i=0; i<Math.pow(2, n); i++){
            StringBuilder res = new StringBuilder();
            for(int j=0; j<n; j++){
                res.append(s.charAt(j));
                if( (i&(1<<j)) !=0 ){   res.setCharAt(j, Character.toUpperCase(s.charAt(j))); }
            }
            System.out.println(res);
        }
    }


    /* Count number of inversions, given a pair is inversion if i<j and arr[i]>arr[j]. */
    // a[] = {2,5,1,3,4}    => 4 as {(2,1), (5,1), (5,3), (5,4)}

    // inversion means a[i]>a[j] count. Iterate through end, count each time number of smaller values than current a[i].
    static int countInversion(int arr[]){  // TC: O(nlogn) average, O(n2) worst
        TreeMap<Integer, Integer> ctMap = new TreeMap<>();      int invCt = 0;

        for(int i=arr.length-1; i>=0; i--){
            for(int val: ctMap.headMap(arr[i], false).values()){      invCt += val; }
            ctMap.put(arr[i], ctMap.getOrDefault(arr[i], 0) +1);
        }
        return invCt;
    }


    /* Count number of reverse pairs in array such that i<j and arr[i]>2*arr[j] */
    // a[]={1,3,2,3,1}  => 2

    // Variation of above one i.e. ct values smaller than a[i]/2. Also, a[i]/2 gives incorrect result when a[i] is odd, so, do (a[i]+1)/2 each time for integer result.
    static int countInversion2(int arr[]){
        TreeMap<Integer, Integer> ctMap = new TreeMap<>();      int invCt = 0;

        for(int i=arr.length-1; i>=0; i--){
            for(int val: ctMap.headMap((arr[i]+1)/2).values()){      invCt += val; }
            ctMap.put(arr[i], ctMap.getOrDefault(arr[i], 0) +1);
        }
        return invCt;
    }


    /* Compute Combination Formula i.e. nCr = nC(n-r) */

    // nCr = n!/(r!* (n-r)!) = (n*(n-1)*(n-2)*..(n-r+1)) / (r*(r-1)*(r-2).. 1)
    static long comb(int n, int r){
        if(r<0 || r>n){  return 0; }
        if(n-r < r){   r=n-r; }
        long res=1;
        for(int i=1; i<=r; i++){
            res*= (n-i+1);  res/= i;
        }
        return res;
    }


    /* Generate first n palindromic numbers. */
    // n=30     => {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99, 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, 212}

    // generate 1st half numbers and reverse them. Also, for 2nd half the length is len/2 which handles 1 less than left if odd number of total digits.
    void firstNPal(int n) {     // firstNPal(30);
        int ct=0, len=1;  // len= current length of palindrome

        while(ct<n) {
            int halfLen= (len+1)/2;   // half length to generate
            int start= (int)Math.pow(10, halfLen-1);        int end= (int)Math.pow(10, halfLen);

            for(int i=start; i<end && ct<n; i++){
                String left= Integer.toString(i);       String right= new StringBuilder(left.substring(0, len/2)).reverse().toString();
                String palin = left+ right;     System.out.print(palin + " ");
                ct++;
            }
            len++;
        }
    }


    /* Find the longest subarray with max bitwise AND. */
    // nums[]= {1,2,3,3,2,2}    => 2 (maxAND=3, length 2 i.e. 3,3)

    // AND operation, for two diff numbers it will always be strictly lesser than max of those 2 numbers (as 1*0=0). So, inc length only if ele are same.
    int maxLenAND(int[] nums) {     // O(n)
        int ans=0, curr=0, mx=0;
        for(int i=0; i<nums.length; i++){
            if(mx<nums[i]){     mx= nums[i];    ans= curr=0; }      // making ans=0 for re-calculating as its for subarray, not subset

            if(mx==nums[i]){    curr++; } else{  curr=0; }
            ans= Math.max(ans, curr);
        }
        return ans;
    }


    /* Find smallest subarray length at each index with max bitwise OR. */
    // nums[]= {1,0,2,1,3}  => {3,3,2,2,1}

    // max OR = OR of all ele (as 1*0=1). Iterate array backwards, finding nearest set bit by filling pos[] (nearest position where we found 1). And calc length.
    int[] minLenOR(int[] nums) {
        int n= nums.length;     int ans[]= new int[n];  int pos[]= new int[32]; Arrays.fill(pos, -1);
        for(int i=n-1; i>=0; i--){
            // if bit is set in nums[i] that's the nearest
            for(int j=0; j<32; j++){    if( (nums[i]&(1<<j)) !=0 ){  pos[j]= i; }}

            // check the mx index where bit is set
            int mx=i;   for(int j=0; j<32; j++){    mx= Math.max(mx, pos[j]); }
            ans[i]= mx-i+1;     // length
        }
        return ans;
    }



/*----------------------------------------------------------------------------------------------------------------------                                                    GREEDY METHOD                               ---------------------------------------------------------------------------------------------------------------------*/

    /* Return minimum candies required to distribute n children, given child with higher ratings gets more candies than neighbours, and should get atleast 1 candy. */
    // rating[]= {1,0,2}	=> 5 (2,1,2)
    // rating[]= {1,2,87,87,87,2,1}     => 13 (1,2,3,1,3,2,1)   // for equal there is no boundation

    // allocate 1 candy to each initially. From left to right, check if a[i-1]<a[i]    => a[i]=a[i-1]+1. From right to left (to cover cases of continous dec), a[i]>a[i+1] => a[i]= a[i+1]+1
    public static int candy(int rating[]) {
        int n= rating.length, sum=0;
        int res[]= new int[n];   Arrays.fill(res, -1);

        for(int i=1; i<n; i++){     // from left to right
            if( rating[i-1]<rating[i] ){    res[i]= res[i-1]+1; }
        }

        for(int i=n-2; i>=0; i--){  // from right to left
            if(rating[i] > rating[i+1] ){   res[i]= Math.max(res[i], res[i+1]+1); }
        }

        for(int i=0; i<n; i++){     sum+= res[i]; }     // total candies
        return sum;
    }


    /* Given set of n jobs, where each job have (deadline, profit). Find number of jobs done with max profit */
    // Jobs={(4,20), (1,10), (1,40), (1,30)}	=> 2, 60 i.e. {job 0, 2} is completed

    //Sort the jobs according to their profit and complete every job on last day of their deadline. If new job comes with similar deadline then see if there is space before deadline to be completed.
    static void jobScheduling(int jobs[][]){
        int n= jobs.length, sum=0, ct=0, mx=0;

        for(int i=0; i<n; i++){   mx= Math.max(mx, jobs[i][0]); }     // for knowing max deadline

        int slot[]= new int[n]; Arrays.fill(slot, -1);
        Arrays.sort(jobs, (a,b) -> { return b[1]-a[1]; }); // sort by profit desc

        for(int i=0; i<n; i++){
            int deadline= jobs[i][0], profit= jobs[i][1];
            for(int j=deadline-1; j>=0; j--){   // assign value to latest available slot before deadline
                if(slot[j] == -1){  slot[j]= profit;    sum+= profit;   ct++;   break; }
            }
        }
        System.out.println(ct+ " "+ sum);
    }


    /* Return intersections between given close intervals A and B. */
    // A= {{0,2}, {5,10}, {13,23}, {24,25}}, B= {{1,5}, {8,12}, {15,24}, {25,26}}   => {{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}}

    // A[i]={1,5}, B[i]={3,8} => ans={3,5}, that means common is max from start, and min from end
    static void interval(int A[][], int B[][]){
        List<List<Integer>> res= new ArrayList<>();
        int i=0, j=0;
        while(i<A.length && j<B.length){
            int start= Math.max(A[i][0], B[j][0]);  int end= Math.min(A[i][1], B[j][1]);
            if(start<=end){ res.add( Arrays.asList(start, end) ); }

            if(A[i][1] < B[j][1])   i++;
            else j++;
        }
        System.out.println(res);
    }


    /* Return intervals after merging all operlapping intervals. */
    // a[][]= {[1,3],[2,6],[8,10],[6,7],[15,18]}	=> {[1,7],[8,10],[15,18]}

    // sort interval by start time. Add a[0] in res, and each time check if overlap then update the last index of res, else add curr index in res.
    static void merge(int a[][]){
        Arrays.sort(a, (x,y) -> { return x[0] - y[0]; } );

        List<int[]> res= new ArrayList<>(); res.add(a[0]);
        for(int i=1; i<a.length; i++){
            int[] last= res.get(res.size()-1);
            int[] curr= a[i];

            if( last[1] >= curr[0]){   last[1] = Math.max(last[1], curr[1]); }
            else{   res.add(curr); }
        }
    }


    /* Find maximum number of meetings that can be performed in a meeting room, given start and end time of ith meeting. (meeting is allowed if end, next start is same i.e. [2,3], [3,4])
    * OR
    * Find max number of non-overlapping intervals.
    * */
    // meeting[][]= {{1,2},{0,6},{3,4},{5,7},{8,9},{5,9}}	=> 4

    // sort by meeting end time, and check if (lastEnded <= currStartTime) then meeting possible, so ct++.
    static int maxMeetings(int a[][]){
        Arrays.sort(a, (x,y)->{ return x[1] - y[1]; } );
        int ct=1, lastEnd= a[0][1]; //initially ct=1 for including 1st meeting
        for(int i=1; i<a.length; i++){
            if( lastEnd <= a[i][0] ){
                ct++;   lastEnd= a[i][1];
            }
        }
        return ct;
    }


    /* Find min number of meeting rooms to perform all meetings.
    * OR
    * Find min number of platforms required in railway station.
    * */
    // a[][]= [[0,30], [5,10], [15,20]]  => 2

    // sort by startTime. Take minHeap (storing endTime), if topValue<=currStart that means curr meeting can happen in same room, so change top endTime with new one, else new room is req to be assigned.
    public static int minRooms(int a[][]){
        Arrays.sort(a, (x,y)->{ return x[0] - y[0]; } );

        PriorityQueue<Integer> pq= new PriorityQueue<>();
        for(int i=0; i<a.length; i++){
            if(!pq.isEmpty() && pq.peek()<=a[i][0]){    pq.remove(); }
            pq.add(a[i][1]);
        }
        return pq.size();
    }


    /* Find maximum number of events that can be attended, given you can attend only 1 event at a time, but its not compulsion to attend entire event */
    // events[][]= {{1,1},{1,4},{2,2},{4,4},{3,4}}

    // Sort by start day. At currDay, Process event who is finishing earlier. That is, store all events with same start date, process whoever is finishing earlier first on that day, and do day++.
    int maxEvents(int[][] events) {
        Arrays.sort(events, (p1, p2) -> { return Integer.compare(p1[0], p2[0]); });
        PriorityQueue<Integer> pq= new PriorityQueue<>();   // end date in asc, min heap
        int ct=0, i=0, n=events.length, currDay=events[0][0];

        while(i<n || !pq.isEmpty()){
            if(pq.isEmpty()){   currDay= events[i][0]; }
            while(i<n && events[i][0] == currDay){  pq.add(events[i][1]);   i++; }  // store all the events who can be processed on currDay.

            pq.remove();   ct++;    currDay++;
            while(!pq.isEmpty() && pq.peek()<currDay){  pq.remove(); }
        }
        return ct;
    }


    /* Given n meetings with startTime, EndTime. Find largest gap possible between meetings if allowed atmost 1 meeting to be rescheduled within EventTime. */
    // eventTime=10, startTime[]= {0,3,7,9}, endTime[]= {1,4,8,10}

    // find all gaps between meetings. Find maximum gap in left-1 and right+1. If there is larger value present in either left or right, then possible to reschedule currMeeting, else not.
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n= startTime.length;    int gaps[]= new int[n+1];

        gaps[0]= startTime[0]-0;
        for(int i=1; i<n; i++){     gaps[i]= startTime[i]- endTime[i-1]; }
        gaps[n]= eventTime- endTime[n-1];

        n= gaps.length;
        int left[]= new int[n];    int right[]= new int[n];     Arrays.fill(left, 0);   Arrays.fill(right, 0);
        for(int i=1; i<n; i++){     left[i]= Math.max(left[i-1], gaps[i-1]); }
        for(int i=n-2; i>=0; i--){  right[i]= Math.max(right[i+1], gaps[i+1]); }

        int mx=0;
        for(int i=1; i<n; i++){
            int dur= endTime[i-1]-startTime[i-1];
            if(dur<=left[i-1] || dur<= right[i]){   mx= Math.max(mx, gaps[i-1]+ dur+ gaps[i]); }    // meeting rescheduled
            mx= Math.max(mx, gaps[i-1]+gaps[i]);
        }
        return mx;
    }

    public static void main(String args[]){

//        {{0,2}, {5,10}, {13,23}, {24,25}}, B= {{1,5}, {8,12}, {15,24}, {25,26}}   => {{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}}
        int A[][]= {{0,30},{5,10},{15,20}}, B[][]= {{1,5}, {8,12}, {15,24}, {25,26}};
        System.out.println(minRooms(A));

    }
}
