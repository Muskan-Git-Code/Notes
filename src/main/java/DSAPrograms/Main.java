package DSAPrograms;

public class Main {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // If k is large, act like unlimited transactions
        if (k >= n / 2) {
            return maxProfitUnlimited(prices);
        }

        // dp[t][d]: max profit up to day d with at most t transactions
        int[][] dp = new int[k + 1][n];

        for (int t = 1; t <= k; t++) {
            int maxNormal = -prices[0]; // Best "buy" scenario so far
            int maxShort = prices[0];   // Best "short sell" scenario so far

            for (int d = 1; d < n; d++) {
                // Option 1: do nothing
                dp[t][d] = dp[t][d - 1];

                // Option 2: end a normal transaction today
                dp[t][d] = Math.max(dp[t][d], prices[d] + maxNormal);

                // Option 3: end a short sell today
                dp[t][d] = Math.max(dp[t][d], maxShort - prices[d]);

                // Update the maxNormal and maxShort
                maxNormal = Math.max(maxNormal, dp[t - 1][d] - prices[d]);
                maxShort = Math.max(maxShort, dp[t - 1][d] + prices[d]);
            }
        }

        return dp[k][n - 1];
    }

    // For large k, unlimited transactions allowed
    private static int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // Normal or short profit for consecutive days
            profit += Math.abs(prices[i] - prices[i - 1]);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 7, 9, 8, 2};
        int k1 = 2;
        System.out.println("Max Profit: " + maxProfit(k1, prices1)); // Output: 14

        int[] prices2 = {12, 16, 19, 19, 8, 1, 19, 13, 9};
        int k2 = 3;
        System.out.println("Max Profit: " + maxProfit(k2, prices2)); // Output: 36
    }
}
