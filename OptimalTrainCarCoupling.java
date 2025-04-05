import java.util.Arrays;

/**
 * Problem Name: Optimal Train Car Coupling
 *
 * Problem Description:
 * You are given an array of train cars, each with a weight.  You need to couple these cars into pairs
 * such that the absolute difference in weight between each pair is minimized.  The goal is to find
 * the minimum total absolute difference across all pairs.  If there's an odd number of cars, one car
 * is left unpaired and its weight is not included in the total difference.
 *
 * Example:
 * Input: [10, 2, 5, 8, 15]
 * Output: 6  (Explanation: Pairs (2, 5) and (8, 10) have differences of 3 and 2, totaling 5. 15 is unpaired.)
 * Input: [10, 20, 30, 40, 50, 60]
 * Output: 20 (Explanation: Pairs (10, 20), (30, 40), (50,60) have differences of 10 each, totaling 30)
 */
public class OptimalTrainCarCoupling {

    public static int minTotalDifference(int[] weights) {
        Arrays.sort(weights);
        int n = weights.length;
        int totalDifference = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            totalDifference += Math.abs(weights[left] - weights[right]);
            left++;
            right--;
        }

        return totalDifference;
    }


    public static void main(String[] args) {
        int[] weights1 = {10, 2, 5, 8, 15};
        int[] weights2 = {10, 20, 30, 40, 50, 60};
        int[] weights3 = {5, 10, 15, 20};
        int[] weights4 = {};
        int[] weights5 = {1};


        System.out.println("Minimum total difference for " + Arrays.toString(weights1) + ": " + minTotalDifference(weights1)); // Output: 11
        System.out.println("Minimum total difference for " + Arrays.toString(weights2) + ": " + minTotalDifference(weights2)); // Output: 30
        System.out.println("Minimum total difference for " + Arrays.toString(weights3) + ": " + minTotalDifference(weights3)); // Output: 10
        System.out.println("Minimum total difference for " + Arrays.toString(weights4) + ": " + minTotalDifference(weights4)); // Output: 0
        System.out.println("Minimum total difference for " + Arrays.toString(weights5) + ": " + minTotalDifference(weights5)); // Output: 0

    }
}