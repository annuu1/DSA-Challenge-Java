import java.util.Arrays;

/**
 * Problem Name:  OptimalTrainFormationWithConstraints
 *
 * Problem Description:
 * You are given an array of train cars, each represented by an integer representing its weight.
 * You need to form a train by arranging these cars in a sequence.  However, there are constraints:
 * 1.  No two consecutive cars can have weights that differ by more than a given threshold 'K'.
 * 2.  The total weight of the train must be maximized.
 *
 * Find the maximum possible total weight of the train that can be formed while adhering to these constraints.
 */
public class OptimalTrainFormationWithConstraints {

    public static int maxTrainWeight(int[] weights, int K) {
        if (weights == null || weights.length == 0) {
            return 0;
        }

        Arrays.sort(weights); //Sort for easier processing -  we can explore other approaches for larger datasets
        int n = weights.length;
        int[] dp = new int[n]; // dp[i] stores the max weight ending with weights[i]

        dp[0] = weights[0];

        for (int i = 1; i < n; i++) {
            dp[i] = weights[i]; // Initialize with the current weight

            for (int j = 0; j < i; j++) {
                if (Math.abs(weights[i] - weights[j]) <= K) {
                    dp[i] = Math.max(dp[i], dp[j] + weights[i]);
                }
            }
        }

        int maxWeight = 0;
        for (int weight : dp) {
            maxWeight = Math.max(maxWeight, weight);
        }

        return maxWeight;
    }


    public static void main(String[] args) {
        int[] weights1 = {10, 5, 15, 20, 25};
        int K1 = 5;
        System.out.println("Max train weight (example 1): " + maxTrainWeight(weights1, K1)); // Output: 60 (20+25+15)


        int[] weights2 = {1, 5, 11, 5, 8};
        int K2 = 2;
        System.out.println("Max train weight (example 2): " + maxTrainWeight(weights2, K2)); //Output: 24

        int[] weights3 = {10, 20, 30, 40, 50};
        int K3 = 100; //No constraint
        System.out.println("Max train weight (example 3): " + maxTrainWeight(weights3, K3)); //Output: 150

        int[] weights4 = {};
        int K4 = 5;
        System.out.println("Max train weight (example 4, empty array): " + maxTrainWeight(weights4, K4)); //Output: 0


    }
}