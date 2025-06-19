package org.example;
import java.util.*;

/*
Recursion: Each time calling is done at (n-1) or (n+1) for reaching to base condition (Think about smallest valid input).
Explore different ways, and for base condition,
    - for count {l= f(); r= f(); return l+r;} there must be base where we are returning actual value to get added in end.
    - for min/ max {l= f(); r= f(); return Math.max(l, r); }, for base only returning edge cases will work.

Dynamic Programming (DP): Algorithm used to solve problem by taking sequence of decisions for optimal choices (max, min, largest, smallest).
Time complexity (TC) for recursion will always be exponential, below TC are for DP solutions.

1. Recursion
2. Recursion on grid
3. Front Partition
4. Subset

*/
public class Recursion {

/*----------------------------------------------------------------------------------------------------------------------
                                                        RECURSION
----------------------------------------------------------------------------------------------------------------------*/

    /* Print number from 1 to n, using recursion. */
    static void numRec(int i, int n) { // numRec(1, n)
        if (i > n) {
            return;
        }
        System.out.print(i + " ");
        numRec(i + 1, n);
    }


    /* Find sum of 1 to n. */

    // Approach 1: Parameterized recursion: Sending values as parameter
    void sumN(int i, int n, int sum) {    // sumN(1, n, 0)
        if (i > n) {
            System.out.println(sum);
            return;
        }
        sumN(i + 1, n, sum + i);
    }

    // Approach 2: Functional Recursion: Waiting for function to return back from if condition and then return responses.
    int sumN2(int i, int n) {    // sumN2(1, n);
        if (i > n) {
            return 0;
        }
        return i + sumN2(i + 1, n);
    }


    /* Find factorial of n. */
    // n=4  => 24 (i.e. 4*3*2*1)

    int fact(int i) {     // fact(n)
        if (i == 1) {
            return 1;
        }
        return i * fact(i - 1);
    }


    /* Fibonacci Number: F(n)= F(n-1)+ F(n-2) */
    // n=7  => 13 (i.e. 0,1,1,2,3,5,8,13)

    int fib(int i) { // fib(n);  // TC: O(pow(2,n))
        if (i <= 1) {
            return i;
        }
        return fib(i - 1) + fib(i - 2);
    }

    // DP Top-Down Approach (Recursive)
    int fibTD(int i, int[] dp) { // fib(n, dp);  // TC: O(pow(2,n))
        if (i <= 1) {
            return i;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        return dp[i] = fibTD(i - 1, dp) + fibTD(i - 2, dp);
    }

    // DP Bottom-Up Approach (Tabulation)
    int fibBU(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /* Count ways to reach nth staircase by taking 1,2,3 steps at a time. */
    // n=3  => 4 {(1,1,1), (1,2), (2,1), (3)}

    // each time have choice either 1, 2, 3 steps
    int stairs(int n) {  // O(n)
        if (n == 1 || n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int step1 = stairs(n - 1);
        int step2 = stairs(n - 2);
        int step3 = stairs(n - 3);
        return step1 + step2 + step3;
    }


    /* Find min cost to reach top floor (end of array), such that can take either 1 or 2 steps. */
    // a[]= {1,100,1,1,1,100,1,1,100,1}     => 6

    // initially it can start from 0th or 1st val as allowed to take 1 or 2 steps. Then each time have choice either 1 or 2 steps.
    int reachTop(int i, int a[]) {   // min(fn(0,a), fn(1,a));
        if (i == a.length) {
            return 0;
        }

        int l = reachTop(i + 1, a);
        int r = Integer.MAX_VALUE;
        if (i > 1) {
            r = reachTop(i + 2, a);
        }
        return Math.min(l, r);
    }


    /* Find min cost to reach end of array, such that cost is abs diff between them, and can take either 1 or 2 steps */
    // a[]= {4,8,3,10,4,4}  => 2 {i.e. 4->3->4->4}

    int costReach(int i, int a[]) {  // TC: O(n), SC: O(n)
        if (i == 0) {
            return 0;
        }

        int l = costReach(i - 1, a) + Math.abs(a[i] - a[i - 1]);
        int r = Integer.MAX_VALUE;
        if (i > 1) {
            r = costReach(i - 2, a) + Math.abs(a[i] - a[i - 2]);
        }
        return Math.min(l, r);
    }


    /* Find min cost to reach end of array, given cost is abs diff between them, and can jump at kth step max. */
    // a[]= {4,8,3,10,4,4}, k=2     => 2 (i.e. 4->3->4->4)

    int reachEnd(int i, int a[], int k) {    // fn(n-1, a, k);   // TC: O(n), SC: O(n)
        if (i == 0) {
            return 0;
        }
        int mn = Integer.MAX_VALUE, jump = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j > 0) {
                jump = reachEnd(i - j, a, k) + Math.abs(a[i] - a[i - j]);
            }
            mn = Math.min(mn, jump);
        }
        return mn;
    }


    /* Return min jumps to reach end of array, given from ith index can take max a[i] steps. */
    // a[]= {2,3,1,1,4}     => 2 (i.e. 0, 1)
    int minJumps(int i, int a[]) {   // minJumps(0, a);
        if (i >= a.length - 1) {
            return 0;
        }
        int mn = Integer.MIN_VALUE;
        for (int j = 1; j <= a[i]; j++) {
            int take = 1 + minJumps(i + j, a);
            mn = Math.min(mn, take);
        }
        return mn;
    }


    /* Return max of array if can't take 2 adjacent values. */
    // a[]= {5,3,4,11,2}    => 16 (i.e. 5,11)

    // if taking a[i] then next which can be taken is i+2. If not taken then next val can be used.
    int adj(int i, int a[]) {    // adj(n-1, a); // TC: O(n)
        if (i < 0) {
            return 0;
        }

        int l = a[i] + adj(i - 2, a);
        int r = 0 + adj(i - 1, a);
        return Math.max(l, r);
    }


    /* Find max of array of can't take adjacent values, also array is arrangend in circular manner. */
    // a[]= {5,3,4,11,2}    => 16 (i.e. 5, 11)

    // Circular order i.e. can't pick 1st and last together. So, calculate taking or leaving 1st index.
    // max( adj(n-1, 1, a), adj(n-2, 0, a) );     // O(n)


    /* Return no of strings possible of size n, if only 3 chars i.e. a,b,c where b,c can come atmost 1,2 times resp. */
    // n=2  => 8 (i.e. aa, ab, ac, ba, bc, ca, cb, cc)

    // if length of string = n then valid ans.
    int possStr(int i, int n, int ctB, int ctC) {    // possStr(0,n,0,0);    // TC: O(n)
        if (i == n) {
            return 1;
        }
        int takeA = 0, takeB = 0, takeC = 0;
        takeA = possStr(i + 1, n, ctB, ctC);
        if (ctB < 1) {
            takeB = possStr(i + 1, n, ctB + 1, ctC);
        }
        if (ctC < 2) {
            takeC = possStr(i + 1, n, ctB, ctC + 1);
        }
        return takeA + takeB + takeC;
    }


    /* Find no of ways in which n friends can remain single or paired. */
    // n=4  => 10

    // for single it can be just 1 way, but for pair, a person can pair with (n-1) other persons. So, n-1 ways.
    int friends(int i) {
        if (i <= 2) {
            return i;
        }
        int single = 1 * friends(i - 1);
        int paired = 0;
        if (i >= 2) {
            paired = (i - 1) * friends(i - 2);
        }
        return single + paired;
    }


    /* Return true if player1 can win the game from player2 by collecting max score, if allowed to take either 1st or last val each time, and both players are playing optimally (i.e. trying to win). */
    // a[]= {1,5,233,7}     => true

    // if chance is of p1 then it will try to get maxScore for win. If chance is of p2 then it will take maxScore and return min remaining for p1.

    // int total= Arrays.stream(a).sum();   if(x>=sum-x){   return true; }else{ return false; }
    int players(int i, int j, int p1, int a[]) { // x= players(0, n-1, 1, a); i.e. players(taking 1st val, or taking last val, assuming 1st chance of p1, a)
        if (i > j) {
            return 0;
        }

        int take1 = 0, take2 = 0;
        if (p1 != 0) {
            take1 = Math.max(a[i] + players(i + 1, j, 0, a), a[j] + players(i, j - 1, 0, a));
        } // if chance is of p1
        else {
            take2 = Math.min(players(i + 1, j, 1, a), players(i, j - 1, 1, a));
        }
        return Math.max(take1, take2);
    }


    /* Return min number of squares in which we can cut given paper of m*n length. */
    // m=3, n=4     => 4 { i.e. square of length 2,2,1,1}

    // each time if square is of length x, then remaining is (total-x). Cutting horizontally, and vertically.
    int minSq(int m, int n) {
        if (m == n) {
            return 1;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < m; i++) {     // horizontal cuts
            ans = Math.min(ans, minSq(i, n) + minSq(m - i, n));
        }

        for (int j = 1; j < n; j++) {
            ans = Math.min(ans, minSq(m, j) + minSq(m, n - j));
        }
        return ans;
    }


    /* Print all possible balanced parenthesis */
    // n=3  => 5 {((())), (()()), (())(), ()(()), ()()()}

    // Close bracket can be added only if extra open bracket is already in string, for balanced parenthesis.
    void balancedPr(int open, int close, String ans) {
        if (open == 0 && close == 0) {
            System.out.println(ans);
            return;
        }

        if (open != 0) {
            balancedPr(open - 1, close, ans + '(');
        }
        if (close > open) {
            balancedPr(open, close - 1, ans + ')');
        }
    }


    /* Print all n bit binary number having more 1's than 0's. */
    // n=4  => {1111, 1110, 1101, 1100, 1011, 1010}

    // 0 is added only if number of 1's are more.
    void bitBinary(int n, int one, int zero, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return;
        }
        bitBinary(n - 1, one + 1, zero, ans + '1');
        if (one > zero) {
            bitBinary(n - 1, one, zero + 1, ans + '0');
        }
    }


    /* Return max profit possible if allowed to buy and sell stock only once. */
    // prices[]= {7,1,5,3,6,4}  => 5 (as 6-1)

    // each time buy min possible, and sell at max profit.
    int stocks(int prices[]){    // TC: O(n)
        int mn= (int)1e9, profit=0;
        for(int i=0; i<prices.length; i++){
            mn= Math.min(mn, prices[i]);    profit= Math.max(profit, prices[i]-mn);
        }
        return profit;
    }


    /* Find max profit if allowed to buy and sell stock any number of times. */
    // prices[]= {7,1,5,3,6,4}      => 7 {as (-1+5)+(-3+6)}

    // Initially buy the stock, then allowed to buy next when previously held stock is sold. Now, while buy/sell i have 2 options i.e. either get the profit, or just go on next ele.
    int stocks2(int i, int buy, int prices[]){  // stocks2(0,1,prices);     // TC: O(n*n)
        if(i==prices.length){   return 0; }

        int profit=0;
        if(buy==1){ profit= Math.max(-prices[i]+ stocks2(i+1, 0, prices), stocks2(i+1, buy, prices)); }     // if allowed, (buying or not)
        else{   profit= Math.max(prices[i]+ stocks2(i+1, 1, prices), stocks2(i+1, buy, prices) ); }     // if allowed, (selling or not)
        return profit;
    }


    /* Return max profit if allowed to buy and sell stock at most k times. */

    // one transaction (complete buy/sell process) allowed only k times, rest all is same.
    int stocks3(int i, int buy, int prices[], int k){  // stocks3(0,1,prices, k);     // TC: O(n*n)
        if(i==prices.length || k==0){   return 0; }

        int profit=0;
        if(buy==1){ profit= Math.max(-prices[i]+ stocks3(i+1, 0, prices, k), stocks3(i+1, buy, prices, k)); }
        else{   profit= Math.max(prices[i]+ stocks3(i+1, 1, prices, k-1), stocks3(i+1, buy, prices, k) ); }
        return profit;
    }


    /* Find max profit if allowed to buy and sell stock any number of times, But cannout buy next day after sell. */

    // after sell, i+2 instead.
    int stocks4(int i, int buy, int prices[]){  // stocks4(0,1,prices);     // TC: O(n*n)
        if(i==prices.length){   return 0; }

        int profit=0;
        if(buy==1){ profit= Math.max(-prices[i]+ stocks4(i+1, 0, prices), stocks4(i+1, buy, prices)); }
        else{   profit= Math.max(prices[i]+ stocks4(i+2, 1, prices), stocks4(i+1, buy, prices) ); }
        return profit;
    }


    /* Find max profit if allowed to buy and sell stock any number of times, given a transaction fees is applied. */

    // apply fees on transaction complete
    int stocks5(int i, int buy, int prices[], int fee){  // stocks4(0,1,prices, fee);     // TC: O(n*n)
        if(i==prices.length){   return 0; }

        int profit=0;
        if(buy==1){ profit= Math.max(-prices[i]+ stocks5(i+1, 0, prices, fee), stocks5(i+1, buy, prices, fee)); }
        else{   profit= Math.max(prices[i] - fee+ stocks5(i+1, 1, prices, fee), stocks5(i+1, buy, prices, fee) ); }
        return profit;
    }



/*----------------------------------------------------------------------------------------------------------------------
                                                    RECURSION ON GRID
----------------------------------------------------------------------------------------------------------------------*/

    /* Return no of ways to reach end of matrix, given can move either right or down. */
    // m=3, n=7     => 28
    // m=3, n=2     => 3 (i.e. RDD, DDR, DRD)

    int reachEnd(int i, int j) {     // fn(m, n)
        if (i == 0 && j == 0) {
            return 1;
        }
        if (i < 0 || j < 0) {
            return 0;
        }

        int up = reachEnd(i - 1, j);
        int left = reachEnd(i, j - 1);
        return up + left;
    }


    /* Return no of ways to reach end of sparse matrix, given can move either right or down. Also, you can move only with path value 1 not 0. */
    // grid[][]= {{1,1,1},{1,0,1},{1,1,1}}  => 2

    int sparse(int i, int j, int a[][], int m, int n) {    // fn(0, 0, a, rowSize, colSize);
        if (i == m && j == n) {
            return 1;
        }
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }
        if (a[i][j] == 0) {
            return 0;
        }     // obstracle condition

        int down = sparse(i + 1, j, a, m, n);
        int right = sparse(i, j + 1, a, m, n);
        return right + down;
    }


    /* Print paths also in above. */
    int sparsePath(int i, int j, String move, List<String> ans, int a[][], int m, int n) {   // sparsePath(0,0,"", {}, a, m, n);
        if (i == m && j == n) {
            ans.add(move);
            return 1;
        }
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }
        if (a[i][j] == 0) {
            return 0;
        }     // obstracle condition

        int down = sparsePath(i + 1, j, move + 'D', ans, a, m, n);
        int right = sparsePath(i, j + 1, move + 'R', ans, a, m, n);
        return right + down;
    }


    /* Find max path sum from 1st to last row in a matrix, given can move down, diagonally left, diagonally right. */
    // a[][]= {{10,2,3},{3,7,2},{8,1,5}}    => 25

    // from 1st to last row, means multiple start and end pts. So exploring all columns of first row as start.
    // for(int j=0; j<a[0].size(); j++){    mx= Math.mx(mx, maxPathSum(0,j,a)); }
    int maxPathSum(int i, int j, int a[][]) {
        if (i < 0 || j < 0 || i > a.length || j > a[0].length) {
            return Integer.MIN_VALUE;
        } // not taking this condition ever

        int down = a[i][j] + maxPathSum(i + 1, j, a);
        int diagLt = a[i][j] + maxPathSum(i + 1, j - 1, a);
        int diagRt = a[i][j] + maxPathSum(i + 1, j + 1, a);
        return Math.max(down, Math.max(diagLt, diagRt));
    }


    /* Find max profit alice and bob can gain from (0,0) to last row in grid. Given they can move down, diagLeft, diagRight, and only one person can pick profit from one cell. */
    // a[][]= {{2,3,1,2},{3,4,2,2},{5,6,3,5}}   => 21 {(2+4+6), (2=2+5)}

    // 2 persons, can go in 2 diff directions.
    // 3d DP used here, i.e. dp[m][n][n], each with value -1 initially.
    int aliceBob(int i, int j1, int j2, int a[][], int m, int n) {
        if (i < 0 || j1 < 0 || j2 < 0 || i >= m || j1 >= n || j2 >= n) {
            return 0;
        }

        int mx = Integer.MIN_VALUE;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = 0;
                if (j1 == j2) {
                    val = a[i][j1] + aliceBob(i + 1, j1 + dj1, j2 + dj2, a, m, n);
                } else {
                    val = a[i][j1] + a[i][j2] + aliceBob(i + 1, j1 + dj1, j2 + dj2, a, m, n);
                }
                mx = Math.max(mx, val);
            }
        }
        return mx;
    }


    /* Find max points possible to collect in n days, given we can perform only 1 activity out of 3 different activities, and you cannout do same activity on next day. */
    // a[][]= {{10,50,1},{5,100,11}}    => 110 {10+100}

    // same task can't be performed next day
    int collectMax(int i, int last, int a[][]) { // fn(n-1, -1, a);
        if (i < 0) {
            return 0;
        }
        int mx = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                mx = Math.max(mx, a[i][task] + collectMax(i - 1, task, a));
            }
        }
        return mx;
    }



/*----------------------------------------------------------------------------------------------------------------------
                                                     SUBSET (non-continous elements): For solving, Express everything in form of index and target.
----------------------------------------------------------------------------------------------------------------------*/

    /* 0/1 Knapsack: Find maximum profit can carry in a bag of weight W, given n items having weight wt, and value val */

    int knapsack(int i, int target, int wt[], int val[]){   // knapsack(n, W, wt, val)  // O(n*W)
        if(i==0){   return 0; }
        int take=0;     if(wt[i-1]<=target){    take= val[i-1]+ knapsack(i-1, target-wt[i-1], wt, val); }
        int notTake= knapsack(i-1, target, wt, val);
        return Math.max(take, notTake);
    }


    /* Unbounded Knapsack: Find max profit can carry in bag of weight W, given n items having weight wt, value val, and there is infinite supply of each element.
    *                       OR
    * Determine max profit by cutting rod and selling pieces, given total length W, and profit val[i] for i length. (Here wt[] automatically goes as {1,2,3,..n}).
    * */

    // Infinite supply, means if value taken then also can take again.
    int unboundedKpsk(int i, int target, int wt[], int val[]){   // knapsack(n, W, wt, val)  // O(n*W)
        if(i==0){   return 0; }
        int take=0;     if(wt[i-1]<=target){    take= val[i-1]+ unboundedKpsk(i, target-wt[i-1], wt, val); }
        int notTake= unboundedKpsk(i-1, target, wt, val);
        return Math.max(take, notTake);
    }


    /* Subset Sum: Check if there is subset with sum equals target */

    int subSum(int i, int target, int arr[]){   // subSum(n, target, arr);  // TC: O(n*W), SC: O(n*W)
        if(i==0){   return (target==0) ? 1 : 0; }

        int take=0; if(arr[i-1]<=target){   take= subSum(i-1, target-arr[i], arr); }
        int notTake= subSum(i-1, target, arr);
        return (take | notTake);
    }


    /* Count Subset Sum: Count subsets with sum equals target in given array a[n]. */

    // for count, instead of checking if exist, return take+notTake; i.e. count.


    /* Equal sum partition: Find 2 subsets with equal sum, including all elements of array. */

    // check if could get a subset with sum = totalSum/2, i.e. s1= s2= s/2.


    /* Check if given array can be divided into k subsets of equal sum. */
    // nums[]= {4,3,2,3,5,2,1}, k=4     => true i.e. {(5), (1,4), (2,3), (2,3)}

    // sumReq= TotalSum/k. Means if value not taken then allowed to take, and if valid result the move to find another subset.
    boolean ksubsets(int i, int target, int reqSum, int nums[], int k, int vis[]){     // ksubsets(0, totalSum, totalSum, nums, k, vis);
        if(k==0){   return true; }
        if(i==nums.length){    if(target==0){   return ksubsets(0, reqSum, reqSum, nums, k-1, vis); }   return false; }

        boolean take= false, notTake= false;
        if(vis[i]==0 && nums[i]<=target){
            vis[i]=1;
            take= ksubsets(i+1, target-nums[i], reqSum, nums, k, vis);
            vis[i]=0;
        }
        notTake= ksubsets(i+1, target, reqSum, nums, k, vis);
        return take | notTake;
    }


    /* Minimum subset sum difference: Find min difference we can get after partitioning the array a[n]. */
    // a[]= {1,6,11,5}  => 1 as {(1,11)-(6,5)} =12-11 =1.

    // min difference, means sum as close as possible to 0. for(i= sum/2; i>=0; i--){ int x= SubsetSum(i,a); if(x!=0){ return (i)-(sum-i); } }


    /* find no of subsets with given difference. */

    // s1-s2=d, s1+s2=s => s1=(s+d)/2, s2= (s-d)/2. So, find subset with sum= s1.


    /* Target Sum: Find in how many ways we can make target sum by adding or subtracting values of array. */
    // a[]= {2,3,-4,4}, W=5     => 6

    // divide into 2 subsets, such that difference is W.


    /* Return size of largest subset, such that there are at most m 0's and n 1's. */
    // s[]= [10, 0001, 111001, 1, 0], m=5, n=3   => 4 i.e. (10,0001,1,0)

    // count number of 0, 1. Then find max size.
    int subsetmn(int i, String s[], int m, int n){  // subsetmn(0, s, m, n);
        if(i==s.length){    return 0; }

        int zeroes=0, ones=0;   String curr= s[i];
        for(int j=0; j<curr.length(); j++){     // count number of zeroes and ones
            if(curr.charAt(i)=='0'){    zeroes++; }
            else{   ones++; }
        }

        int take=0; if(zeroes<=m && ones<=n){   take= 1+ subsetmn(i+1, s, m-zeroes, n-ones); }
        int notTake= subsetmn(i+1, s, m, n);
        return Math.max(take, notTake);
    }


    /* Find number of ways we can make sum target from infinite supply of coins a[n]. */
    int coins(int i, int target, int a[]){  // coins(n-1, target, a);
        if(i<0){   return (target==0) ? 1 : 0; }

        int take=0; if(a[i]<=target){ take= coins(i, target-a[i], a); }
        int notTake= coins(i-1, target, a);
        return take+ notTake;
    }


    /* Find min number of coins to make sum target from infinite supply of coins a[n]. */
    int minCoins(int i, int target, int a[]){  // coins(n-1, target, a);
        if(i<0){   return (target==0) ? 0 : (int)1e9; }

        int take=0; if(a[i]<=target){ take= minCoins(i, target-a[i], a); }
        int notTake= minCoins(i-1, target, a);
        return take+ notTake;
    }


    /* Find least number of perfect squares that sums to n. */
    // n=13     => 2 as (4+9)

    // take or notTake, and also can take a value infinite times.
    int perfectSq(int i, int target){   // perfectSq(1, n)
        if(target==0){  return 0; }
        if(i*i > target){   return (int)1e9; }

        int take= (int)1e9; if(i*i <= target){  take= 1+ perfectSq(i, target- i*i); }
        int notTake= perfectSq(i+1, target);
        return Math.min(take, notTake);
    }



/*----------------------------------------------------------------------------------------------------------------------
                                                     Longest Common Subsequence (LCS): Think in form of (i,j).
----------------------------------------------------------------------------------------------------------------------*/

    /* Find LCS in string x and y. */
    // x= abcdgh, y= abedfhr    => 4 (as "abdh")

    int lcs(int i, int j, String x, String y){  // lcs(x.length(), y.length(), x, y)    // TC: O(m*n)
       if(i==0 || j==0){  return 0; }

       int take=0;  if( x.charAt(i-1)==y.charAt(j-1) ){ take= 1+ lcs(i-1, j-1, x, y); }
        int notTake= Math.max(lcs(i-1, j, x, y), lcs(i, j-1, x, y));
       return Math.max(take, notTake);
    }


    /* Print the LCS in string x, y. */

    // During lcs traversal, we stored values in dp table. Now checking those values i.e. if equal then take, else move up or left based on value which is more.
    String lcsPrint(int i, int j, String x, String y){ // lcsPrint(x.length(), y.length(), x, y);
        int dp[][]= new int[x.length()+1][y.length()+1];    int ix=0;   //ix= lcs(i, j, x, y, dp);
        String s= "";
        while(i>=0 && j>=0){
            if(x.charAt(i-1)==y.charAt(j-1)){   s+= x.charAt(i-1);  i--;    j--; }
            else if(dp[i-1][j] > dp[i][j-1]){   i--; }
            else{   j--; }
        }
        return s;
    }


    /* Find length of longest common substring. */
    // x= abcdgh, y= abedfhr    => 2 as "ab"

    // if continue, then ct+1, else either current or new substring
    int commonStr(int i, int j, int ct, String x, String y){    // commonStr(x.length(), y.length(), 0, x, y);
        if(i==0 || j==0){   return ct; }

        int take=0; if(x.charAt(i-1)==y.charAt(i-1)){   take= commonStr(i-1, j-1, ct+1, x, y); }
        int notTake= Math.max(ct, Math.max(commonStr(i-1, j, 0, x, y), commonStr(i, j-1, 0, x, y) ));
        return Math.max(take, notTake);
    }


    /* Find length of shortest common subsequence such that on merging we can get both strings from it. */
    // a= "geek", b= "eke"  => geeke

    // basically just count common only once.
    int scs(String x, String y){
        int m= x.length(), n= y.length();   int ix= lcs(m, n, x, y);
        return m+ n- ix;
    }


    /* Find min number of insertion and deletion to convert string a into b. */
    // a=heap, b=pea    => insertion=1, deletion=2 { common= ea }

    // find lcs, then delete extra values from a (i.e. a.length()- lcsVal), and convert to b, by adding extra required (i.e. b.length()-lcsVal).
    void minInsertDelete(String a, String b){
        int m= a.length(), n= b.length();   int ix= lcs(m, n, a, b);
        System.out.println("Number of deletion: "+ (m-ix) );
        System.out.println("Number of insertion: "+ (n-ix) );
    }


    /* Find length of longest palindromic subsequence. */
    // x= agbcba    => abcba

    int palindrom(String x){
        String y= new StringBuilder(x).reverse().toString();
        return lcs(x.length(), y.length(), x, y);
    }


    /* Find length of longest repeating subsequence. */
    // x= aabebcdd      => 3 {abd}

    // return lcs(x.length(), x.length(), x,x); just while checking repetition, for not taking same element if(x[i-1]==y[j-1] && i!=j ){take=..}


    /* Check if x is subsequence of string y. */

    // if(lcs(m,n,x,y)==m) then yes, else no.


    /* Find distinct number of ways in which string s2 can occur in s1. */
    // s1= babgbag, s2= bag     => 5

    // if possible to take then can take or not. And ct ways
    int distinctSq(int i, int j, String s1, String s2){   // distinctSq(m, n, s1, s2)
        if(j==0){   return 1; }     // found an ans
        if(i==0){   return 0; }     // reached at end without finding ans

        int take=0; if(s1.charAt(i-1)==s2.charAt(j-1)){ take= distinctSq(i-1, j-1, s1, s2); }
        int notTake= distinctSq(i-1, j, s1, s2);
        return take+ notTake;
    }


    /* Find min number of operations required to convert string s1 into s2, given 3 operations insert, delete, replace. */
    // s1= horse, s2= ros   => 3

    // min operations i.e. either take or apply any of 3 operation in case of notTake.
    int minOpr(int i, int j, String s1, String s2){ // minOpr(m, n, s1, s2);
        if(i==0){   return j; }     // insert remaining values
        if(j==0){   return i; }    // remove remaining values

        int take= (int)1e9;     if(s1.charAt(i-1)==s2.charAt(j-1)){ take= 0+ minOpr(i-1, j-1, s1, s2); }
        int ins= 1+ minOpr(i, j-1, s1, s2);
        int del= 1+ minOpr(i-1, j, s1, s2);
        int rep= 1+ minOpr(i-1, j-1, s1, s2);
        return Math.min(take, Math.min(ins, Math.min(del, rep)));
    }


    /* Find if string s1, s2 are equal, given s1 can have 2 wild characters i.e. '?' equals any 1 char, and '*' equals zero or more string of chars. */
    // s1= "ba*a?", s2= "baaabab"   => true

    boolean wildChars(int i, int j, String s1, String s2){
        if(i==0){   return (j==0) ? true : false; }
        if(j==0){   for(int k=0; k<i; k++){ if(s1.charAt(k)!='*'){  return false; }}    return true; }  // s2 empty, s1 can be all *

        boolean take1= false, take2= false;
        if(s1.charAt(i-1)==s2.charAt(j-1) || s1.charAt(i-1)=='?'){  take1= wildChars(i-1, j-1, s1, s2); }
        if(s1.charAt(i-1)=='?'){    take2= wildChars(i, j-1, s1, s2); }
        return take1 | take2;
    }


    /* LIS: Find length of longest increasing subsequence. */
    // a[]= {10,9,2,2,5,3,7,101,18}     => 4 (as {2,5,7,101})

    // only condition is next ele should be greater than prev in sequence
    int lis(int i, int prev, int a[]){
        if(i==a.length){    return 0; }

        int take=0;     if(prev==-1 || a[i]>a[prev]){   take= 1+ lis(i+1, i, a); }
        int notTake= 0+ lis(i+1, prev, a);
        return Math.max(take, notTake);
    }


    /* Find maximum sum you can get in any increasing subsequence. */
    // instead of +1, do take= nums[i]+ lis(i+1, i, a);


    /* Find number of longest increasing subsequence. */
    // a[]= {1,3,5,4,7}     => 2

    // Same as lis, though instead of returning directly check if length equal then ct, else take max length
    class noOfLongestIncSeq{
        class Pair{
            int len;    int ct;
            Pair(int len, int ct){  this.len= len;  this.ct= ct; }
        }

        Pair noOfSeq(int i, int prev, int a[]){ // noOfSeq(0, -1, a);
            if(i==a.length){    return new Pair(0, 1); }

            Pair take= new Pair(0, 0);  if(prev==-1 || a[i]>a[prev]){   take= noOfSeq(i+1, i, a);   take.len++; }
            Pair notTake= noOfSeq(i+1, prev, a);

            if(take.len==notTake.len){  return new Pair(take.len, take.ct+ notTake.ct); }
            else if(take.len > notTake.len){    return take; }
            else{   return notTake; }
        }
    }


    /* Find length of largest subset in which each value is divisible by other. */

    // sort a[], check if curr is divisible by last/ prev val then take.
    int divSubset(int i, int prev, int a[]){    // Arrays.sort(arr);    return dibSubset(0,-1, a);
        if(i==a.length){    return 0; }

        int take=0; if(prev==-1 || a[i]%a[prev]==0){    take= 1+ divSubset(i+1, i, a); }
        int notTake= 0+ divSubset(i+1, prev, a);
        return Math.max(take, notTake);
    }


    /* Print largest divisible subset. */

    // each time on completing check if currList is largest then that's the ans.
    void divPrint(int i, int prev, int a[], List<Integer> currList, List<Integer> resList){     // divPrint(0, -1, a, [], []);  return resList;
        if(i==a.length){    if(currList.size() > resList.size()){   resList= currList; } return; }

        if(prev==-1 || a[i]%a[prev]==0){    currList.add(a[i]);     divPrint(i+1, i, a, currList, resList);     currList.removeLast(); }
        divPrint(i+1, prev, a, currList, resList);
    }


    /* Find length of longest increasing string chain, in which ith string have only 1 letter diff from last string letters. */
    // a[]= {xbc, pcxbcf, xb, cxbc, pcxbc}  => 5 as (xb, xbc, cxbc, pcxbc, pcxbcf)

    // sort list, check if possible then take it.
    class incLetterChain {
        int incChain(int i, int prev, String words[]) {     // sort; return(0, -1, words);
            if (i >= words.length) {    return 0; }

            int take = 0;
            if (prev == -1 || letterDiff(words[i], words[prev])) {  take = 1 + incChain(i + 1, i, words); }
            int notTake = incChain(i + 1, prev, words);
            return Math.max(take, notTake);
        }

        boolean letterDiff(String s1, String s2) {
            if (s1.length() + 1 != s2.length()) {   return false; }

            int i = 0, j = 0;
            while (j < s2.length()) {
                if (s1.charAt(i) == s2.charAt(j)) { i++;    j++; }
                else {  j++; }
            }
            if (i == s1.length() && j == s2.length()) {     return true; }
            return false;
        }
    }


    /* Find length of longest bitonic subsequence. */
    // a[]= {1,2,1,2,1}     => 3

    // bitonic means first inc then dec. inc can be when prev is lesser, dec when prev is more. Also, inc sequence till we haven't taken any ele for dec (i.e. next=0).
    int bitonic(int i, int prev, int next, int a[]){
        if(i==a.length){    return 0; }

        int take1=0, take2=0;
        if(next==0 && (prev==-1 || a[i]>a[prev]) ){ take1= 1+ bitonic(i+1, i, next, a); }   // increasing
        if(prev==-1 || a[prev]>a[i] ){  take2= 1+ bitonic(i+1, i, 1, a); }  // decreasing
        int notTake= 0+ bitonic(i+1, prev, next, a);
        return Math.max(take1, Math.max(take2, notTake));
    }



/*--------------------------------------------------------------------------------------------------------------------------------------FRONT PARTITION: Starting from begin, and check for partition each time. Used when we need to solve complete, but in some specific sequence. {Start with entire array, run loop k to try possible partition, ans}. Like for ABCD, we can solve like (A)(BCD), (AB)(CD), (ABC)(D) -----------------------------------------------------------------------------------------------------------------------------*/

    /* Find number of palindromic substrings in given string s. */
    // s= aaa   => {a,a,a,aa,aa,aaa}

    // check if palindrome, for each substring
    int ctPal(int i, String s, int dp[], int dpPal[][]){    // ctPal(0,s,dp,dpPal); TC: O(pow(n,2))
        if(i==s.length()){  return 0; }
        if(dp[i]!=-1){  return dp[i]; }

        int ct=0;
        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s, dpPal)==1){
                ct+= 1+ ctPal(j+1, s, dp, dpPal);
            }
        }
        return dp[i]=ct;
    }

    int isPalindrome(int i, int j, String s, int dp[][]) {   // TC: O(1) as for all n2 substrings only one time it need to find.
        if(i >= j) {   return dp[i][j] = 1; }
        if(dp[i][j] != -1) {   return dp[i][j]; }

        if(s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = isPalindrome(i + 1, j - 1, s, dp);
        }
        return dp[i][j] = 0;
    }


    /* Find largest palindromic substring. */
    // s= babad     => bab

    // if palindrome then check length and store max length instead of count.
    // int mx= new int[1], ans= new int[1];  mx= Integer.MIN_VALUE; maxPal(0, s, mx, ans, dpPal);
    void maxPal(int i, String s, int mx[], String ans[], int dpPal[][]){
        if(i==s.length()){  return; }

        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s, dpPal)==1){
                int len= j-i+1;
                if(len>mx[0]){ mx[0]= len;    ans[0]= s.substring(i, j+1); }
                maxPal(j+1, s, mx, ans, dpPal);
            }
        }
    }


    /* Return minimum number of partition required to make each substring palindrome. */
    // s= aab   => 1 (i.e. aa, b)

    // each time check if length (i, k) is palindrome then ct+1, and iterate remaining (k+1, j) string for checking.
    int minPart(int i, String s, int dp[], int dpPal[][]){    // minPart(0, s, dp, dpPal);    //dpPal is for palindrome dp    // TC: O(n*n)
        if(i==s.length()){  return 0; }
        if(dp[i]!=-1){  return dp[i]; }

        int mn= Integer.MAX_VALUE;
        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s, dpPal)==1 ) {      // O(1)
                mn = Math.min(mn, 1+ minPart(j+1, s, dp, dpPal));
            }
        }
        return dp[i]= mn;
    }


    /* Find all possible palindrome partitioning of given string. */
    // s= aab   => [[a,a,b],[aa,b]]

    //After checking if palindrome, then add it to ans, and iterate for next j elements.
    void possPal(int i, String s, List<String> path, List<List<String>> ans, int dpPal[][]){    // possPal(0, s, path, ans, dpPal);  // TC: O(n*n)
        if(i==s.length()){  ans.add(path);  return; }

        for(int j=0; j<s.length(); j++){
            if(isPalindrome(i, j, s, dpPal)==1) {
                path.add(s.substring(i, j+1));  // as substr takes value from startix till endix-1.
                possPal(j+1, s, path, ans, dpPal);
                path.remove(path.size()-1);
            }
        }
    }


    /* Find maximum number of non-overlapping palindromic substrings having length atleast k. */
    // s= “abaccdbbd”, k=3	=> 2 as (aba, dbbd)

    //if palindrome, then check if length>=k then include in ans.
    int maxKLenSubstr(int i, String s, int k, int dp[], int dpPal[][]){	// return fn(0, s, s.length(), k, dp);
        if(i==s.length()){  return 0; }
        if(dp[i]!=-1)   return dp[i];

        int mx= 0;
        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s, dpPal)==1){
                int cost=  maxKLenSubstr(j+1, s, k, dp, dpPal);
                if((j-i+1)>=k){  cost++; }
                mx= Math.max(mx, cost);
            }
        }
        return dp[i]= mx;
    }


    /* Return true if it is possible to make string s from words[]. */
    // s= "catsandog", words[] = ["cats","dog","sand","and","cat"]	=> false

    // add all words in a set. Check if substr present in set, then possible ans, else not.
    boolean possWordsStr(int i, String s, Set<String> wordSet, int dp[]){   // Set<String> wordSet= new HashSet<>(words);   return possWordsSet(0,s, wordSet, dp);
        if(i==s.length()){  return true; }
        if(dp[i]!=1){  return dp[i]==1; }

        for(int j=i; j<s.length(); j++){
            if( wordSet.contains(s.substring(i, j+1)) ){
                if(possWordsStr(j+1, s, wordSet, dp)){  dp[i]=1;    return true; }
            }
        }
        dp[i]=0;    return false;
    }


    /* Print all the possible combinations to make string s from words[]. */

    //instead of returning store the values.
    void possAllStr(int i, String s, Set<String> wordSet, List<String> path, List<List<String>> ans){   // Set<String> wordSet= new HashSet<>(words);   return possAllStr(0,s,wordSet,path,ans);
        if(i==s.length()){  ans.add(path); return; }

        for(int j=i; j<s.length(); j++){
            String sub= s.substring(i, j+1);
            if( wordSet.contains(sub) ){
                path.add(sub);
                possAllStr(j+1, s, wordSet, path, ans);
                path.removeLast();
            }
        }
    }


    /* Find largest sum of given array a[] by partition the array into groups of atmost size k, such that each partition value will be changed to maximum value in that partition. */
    // a[]={1,15,7,9,2,5,10}, k=3	=> 84 as (15,15,15,9,10,10,10)

    // each time take max value till what we have iterated, calc sum, and ans.
    int partArr(int i, int a[], int k, int dp[]){
        if(i==a.length){    return 0; }
        if(dp[i]!=-1){  return dp[i]; }

        int mx=0, len=0, ans=0;
        for(int j=i; j<Math.min(i+k, a.length); j++){
            mx= Math.max(mx, a[j]); len++;
            int sum= len*mx + partArr(j+1, a, k, dp);
            ans= Math.max(ans, sum);
        }
        return dp[i]= ans;
    }


    /* Matrix Chain Multiplication: Find minimum cost to multiply all matrices given in array a[n], where dimension of ith matrix is a[i-1]*a[i] and cost to multiply a*b, b*c is a*b*c. */
    // a[]={40, 20, 30, 10, 30}     => 26000 { (A(BC))D =20*30*10+ 40*20*10+ 40*10*30 =26000 }

    // Given, need to multiply a*b*c, means 2 partitions together. So, partition dp across (i,j). And both partitions should have atleast 1 value, so iterate till j-1.
    int mcm(int i, int j, int a[], int dp[][]){ // return mcm(1, n-1, a, dp);
        if(i==j){   return 0; }
        if(dp[i][j]!=-1){   return dp[i][j]; }

        int mn= Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int cost= a[i-1]*a[k]*a[j]+ mcm(i, k, a, dp)+ mcm(k+1, j, a, dp);
            mn= Math.min(mn, cost);
        }
        return dp[i][j]= mn;
    }


    /* Return max number of coins by bursting all balloons, where ith balloon can get a[i-1]* a[i]* a[i+1] coins. For less than 3 balloons take 1 as a[i-1] or a[i+1]. */

    // we are multiplying values, so multiply 1 in start and end already, and then brust all.
    int mcmBal(int i, int j, int a[], int dp[][]){ // return mcmBalloon(0, n+1, a, dp); //adding 1 in start and end.
        if(i+1==j){   return 0; }
        if(dp[i][j]!=-1){   return dp[i][j]; }

        int mx= Integer.MIN_VALUE;
        for(int k=i+1; k<=j-1; k++){  // trying to burst every balloon in (i+1 ... j-1)
            int cost= a[i]*a[k]*a[j]+ mcmBal(i, k, a, dp)+ mcmBal(k+1, j, a, dp);
            mx= Math.max(mx, cost);
        }
        return dp[i][j]= mx;
    }


    /* Find min cost to cut a stick of length n at each a[i], and the cost is length of stick which we are cutting. */
    // a[]={3,5,1,4}, n=7	=> 16

    // Add 0 in start, n in end. Sort the cuts a[]. return minCostStick(0, m+1, cuts, dp) by taking cost i.e. len (j-i), and move further.
    int minCostStick(int i, int j, int a[], int dp[][]){
        if(i+1==j){ return 0; }
        if(dp[i][j]!=-1){   return dp[i][j]; }

        int ans= Integer.MAX_VALUE;
        for(int k=i+1; k<=j-1; k++){
            int cost= (a[j]-a[i]) + minCostStick(i, k, a, dp)+ minCostStick(k, j, a, dp);
            ans= Math.min(ans, cost);
        }
        return dp[i][j]= ans;
    }


    /* Find min cost to merge all stones. Given can merge exactly val consecutive stones in a move, and cost is sum of stones merged.   */
    // stones[]={3,5,1,2,6}, val=3    => 25 as {(5,1,2),(3,8,6)}

    // Precalculate cost by prefix sum. Possible to merge all only if ((n-1)%(k-1))==0. At a time can merge exactly val stones, so if taken (i,k) then can merge. Let's have t=1 when we want to merge.
    class mergeStoneSum{
        int mergeStones(int[] stones, int val) {
            int n = stones.length;
            if((n-1)%(val-1) != 0) {    return -1; }

            // prefix sum
            int[] prefix = new int[n + 1];
            for(int i = 0; i < n; i++) {    prefix[i+1]= prefix[i]+ stones[i]; }

            // dp[i][j][t] → memoization
            int[][][] dp = new int[n][n][val+1];
            for(int[][] mat : dp) {     for(int[] row : mat){   Arrays.fill(row, -1); }}

            return fn(0, n-1, 1, stones, val, prefix, dp);
        }

        int fn(int i, int j, int t, int stones[], int val, int prefix[], int dp[][][]) {
            if(i==j) {      return (t==1) ? 0 : (int)1e9/2; }
            if(dp[i][j][t] != -1) {     return dp[i][j][t]; }

            int ans= (int)1e9;
            if(t==1){
                ans= prefix[j+1]- prefix[i]+ fn(i, j, val, stones, val, prefix, dp);
            }
            else {      // try splitting at k. If
                for(int k=i; k<j; k+=(val-1)) {
                    ans = Math.min(ans, fn(i, k, 1, stones, val, prefix, dp)+ fn(k+1, j, t-1, stones, val, prefix, dp));
                }
            }
            return dp[i][j][t]= ans;
        }
    }


    /* Count number of ways we can parenthesize the expression (containing T, F, &, |, ^) so that the value of expression evaluates to true. */
    // s= "T^F&T"   => 2 as {(T^F)&T, T^(F&T)}

    // Check for operators, which lie from (1, last-1) after every adjacent value. Now, from each partition check if we are getting T/F. And then apply operator on it. And initially sent isTrue as 1, as we need to calculate ways who yields result as true.
    int countWays(int i, int j, boolean isTrue, String s) {     // countWays(0, n-1, 1, s);
        if(i>j){    return 0; }

        if(i==j){
            if(isTrue){ return (s.charAt(i)=='T') ? 1 : 0; }
            else{   return (s.charAt(i)=='F') ? 1 : 0; }
        }

        int ans=0;
        for(int k=i+1; k<=j-1; k+=2){   //only check f
            char op = s.charAt(k);

            int lt= countWays(i, k-1, true, s);                     int rt= countWays(k+1, j, true, s);

            int lf= countWays(i, k-1, false, s);        int rf= countWays(k+1, j, false, s);

            if(op == '&') {
                if(isTrue){ ans+= lt*rt; }
                else{   ans+= lf*rf+ lt*rf+ lf*rt; }
            }
            else if(op == '|') {
                if(isTrue){ ans+= lt*rt+ lt*rf+ lf*rt; }
                else{   ans+= lf*rf; }
            }
            else if(op == '^') {
                if(isTrue){ ans+= lt*rf+ lf*rt; }
                else{   ans+= lt*rt+ lf*rf; }
            }
        }
        return ans;
    }




    public static void main(String args[]){
        Recursion r= new Recursion();
        r.numRec(0,5);  r.sumN(0,5,0);
    }

}

/* Trees

10. Find number of balanced binary trees possible with height h i.e. abs(left- right subtree)<=1.
 */
        /* n=3	=> 15

        //Cases when left, right subtree having same height, or diff of one. And multiply to include all combinations.
        int fn(int h){
            if(h==0 || h==1)    return 1;

            int t1= fn(h-1) * fn(h-1);	int t2= fn(h-1) * fn(h-2);	int t3= fn(h-2) * fn(h-1);
            return t1+ t2+ t3;
        }
*/
