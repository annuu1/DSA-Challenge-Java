import java.util.Arrays;

/**
 * Problem Name:  MaximizeProfitFromStockTransactionsWithCooldown
 *
 * Problem Description: You are given an array of stock prices for consecutive days.  You can buy and sell stock on any day, but you must wait one day after selling before buying again (a "cooldown" period).  Maximize your profit.
 *
 * Example:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: Buy on day 1 (price = 1), sell on day 2 (price = 2), buy on day 4 (price = 0), sell on day 5 (price = 2). Total profit = (2-1) + (2-0) = 3.
 */
public class MaximizeProfitFromStockTransactionsWithCooldown {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        // dp[i][0]: max profit on day i without holding a stock
        // dp[i][1]: max profit on day i holding a stock
        // dp[i][2]: max profit on day i in cooldown period (after selling)

        int[][] dp = new int[n][3];

        dp[0][0] = 0; // No profit on day 0 without holding
        dp[0][1] = -prices[0]; // Profit is negative cost of buying on day 0
        dp[0][2] = 0; // No profit in cooldown on day 0

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]); //Either do nothing or come out of cooldown
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); //Hold or buy
            dp[i][2] = dp[i - 1][1] + prices[i]; //Sell and enter cooldown
        }

        return Math.max(dp[n - 1][0], dp[n - 1][2]); //Max profit is either without holding or after selling and cooldown
    }


    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        int[] prices2 = {1};
        int[] prices3 = {1,2,4};
        int[] prices4 = {3,2,6,5,0,3};


        System.out.println("Max profit for prices1: " + maxProfit(prices1)); // Output: 3
        System.out.println("Max profit for prices2: " + maxProfit(prices2)); // Output: 0
        System.out.println("Max profit for prices3: " + maxProfit(prices3)); // Output: 3
        System.out.println("Max profit for prices4: " + maxProfit(prices4)); // Output: 7

    }
}