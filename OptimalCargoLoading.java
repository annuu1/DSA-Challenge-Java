import java.util.Arrays;

/**
 * Problem Name: OptimalCargoLoading
 *
 * Problem Description: You are given a list of cargo items, each with a weight and a value.  You have a ship with a maximum weight capacity.
 * Determine the combination of cargo items that maximizes the total value loaded onto the ship without exceeding the weight capacity.
 * This is a variation of the 0/1 Knapsack problem.  However, unlike a traditional knapsack, we'll add a constraint:  no more than 3 items of any single type can be loaded.
 *
 */
public class OptimalCargoLoading {

    public static int[] optimalCargo(int[] weights, int[] values, int capacity, int numItems) {
        int n = weights.length;
        int[][][] dp = new int[n + 1][capacity + 1][4]; // dp[i][w][c] max value with i items, w weight, c count of current item

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                for (int c = 0; c <= 3; c++) {
                    //Don't include item i
                    dp[i][w][c] = dp[i - 1][w][c];

                    //Include item i if possible
                    if (w >= weights[i - 1] && c < 3) {
                        dp[i][w][c + 1] = Math.max(dp[i][w][c + 1], dp[i - 1][w - weights[i - 1]][c] + values[i - 1]);
                    }
                }
            }
        }

        //Find the maximum value and reconstruct the solution
        int maxValue = 0;
        int maxWeight = 0;
        int[] maxCombination = new int[numItems];
        Arrays.fill(maxCombination,0);

        for (int w = 0; w <= capacity; w++) {
            for (int c = 0; c <= 3; c++) {
                if (dp[n][w][c] > maxValue) {
                    maxValue = dp[n][w][c];
                    maxWeight = w;
                }
            }
        }

        int currentWeight = maxWeight;
        for(int i = n; i>=1; i--){
            for(int c = 3; c>=0; c--){
                if(dp[i][currentWeight][c] != dp[i-1][currentWeight][c]){
                    maxCombination[i-1] = c;
                    currentWeight -= weights[i-1];
                    break;
                }
            }
        }

        return maxCombination;
    }

    public static void main(String[] args) {
        int[] weights = {10, 20, 30, 15};
        int[] values = {60, 100, 120, 75};
        int capacity = 50;
        int numItems = weights.length;

        int[] bestCombination = optimalCargo(weights, values, capacity, numItems);

        System.out.println("Optimal Cargo Combination (Number of each item):");
        for (int i = 0; i < numItems; i++) {
            System.out.println("Item " + (i + 1) + ": " + bestCombination[i]);
        }

    }
}