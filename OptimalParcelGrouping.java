import java.util.Arrays;

/**
 * Problem Name: OptimalParcelGrouping
 *
 * Problem Description:
 * You are given a list of parcels with their weights.  You need to group these parcels into bins,
 * with each bin having a maximum weight capacity. The goal is to minimize the number of bins used.
 *  However, there's a constraint: parcels can only be grouped if their weights differ by at most a given threshold.
 *
 * Input: An array of integers representing parcel weights, the maximum weight capacity of each bin, and the weight difference threshold.
 * Output: The minimum number of bins required to store all parcels.
 */
public class OptimalParcelGrouping {

    public static int minBins(int[] weights, int maxWeight, int weightThreshold) {
        Arrays.sort(weights); // Sort weights for efficient processing

        int binsUsed = 0;
        int[] binWeights = new int[weights.length]; // Initialize bin weights.  Could be optimized with a dynamic array if needed.

        for (int weight : weights) {
            boolean placed = false;
            for (int i = 0; i < binsUsed; i++) {
                if (binWeights[i] + weight <= maxWeight && Math.abs(weight - (binWeights[i] == 0? 0 : weights[i - binsUsed ])) <= weightThreshold) {
                    binWeights[i] += weight;
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                binWeights[binsUsed++] = weight;
            }
        }
        return binsUsed;
    }


    public static void main(String[] args) {
        int[] weights1 = {10, 5, 12, 7, 15, 3, 18};
        int maxWeight1 = 20;
        int weightThreshold1 = 5;
        System.out.println("Minimum bins needed for example 1: " + minBins(weights1, maxWeight1, weightThreshold1)); // Expected: 3


        int[] weights2 = {5, 10, 15, 20, 25, 30};
        int maxWeight2 = 30;
        int weightThreshold2 = 2;
        System.out.println("Minimum bins needed for example 2: " + minBins(weights2, maxWeight2, weightThreshold2)); // Expected: 4

        int[] weights3 = {1,2,3,4,5,6,7,8,9,10};
        int maxWeight3 = 10;
        int weightThreshold3 = 10;
        System.out.println("Minimum bins needed for example 3: " + minBins(weights3, maxWeight3, weightThreshold3)); // Expected: 1

        int[] weights4 = {};
        int maxWeight4 = 10;
        int weightThreshold4 = 5;
        System.out.println("Minimum bins needed for example 4 (empty array): " + minBins(weights4, maxWeight4, weightThreshold4)); // Expected: 0


    }
}