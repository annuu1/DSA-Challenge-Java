import java.util.Arrays;

/**
 * Problem Name: OptimalSubarrayGroupingWithMinMaxDifference
 *
 * Problem Description: Given an array of integers, divide the array into k non-overlapping subarrays.
 * The goal is to minimize the maximum difference between the minimum and maximum values within any of the k subarrays.
 *
 * Example:
 * Input: arr = {1, 5, 2, 8, 3, 9, 4, 7, 6}, k = 3
 * Output: 4  (Possible groupings: {1, 5, 2}, {8, 3}, {9, 4, 7, 6}.  Max difference within any group is 4 (5-1 for the first group). Other groupings might yield a smaller max difference)
 */
public class OptimalSubarrayGroupingWithMinMaxDifference {

    public static int minMaxDifference(int[] arr, int k) {
        if (k <= 0 || k > arr.length || arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        Arrays.sort(arr); // Sorting simplifies finding min/max within subarrays

        int n = arr.length;
        int minDiff = Integer.MAX_VALUE;

        // Iterate through all possible groupings (this is a brute force approach, can be improved with DP for larger inputs)
        for (int i = 0; i < (1 << (n - 1)); i++) { // Iterate through all possible groupings using bit manipulation.
            if (Integer.bitCount(i) != k - 1) continue; // Only consider groupings with k-1 dividers (k subarrays)

            int[] groupMins = new int[k];
            int[] groupMaxs = new int[k];
            Arrays.fill(groupMins, Integer.MAX_VALUE);
            Arrays.fill(groupMaxs, Integer.MIN_VALUE);

            int groupIndex = 0;
            int start = 0;
            for (int j = 0; j < n - 1; j++) {
                if ((i >> j) % 2 == 1) { // Check if a divider is at index j
                    for (int l = start; l <= j; l++) {
                        groupMins[groupIndex] = Math.min(groupMins[groupIndex], arr[l]);
                        groupMaxs[groupIndex] = Math.max(groupMaxs[groupIndex], arr[l]);
                    }
                    groupIndex++;
                    start = j + 1;
                }
            }
            // Handle the last group
            for (int l = start; l < n; l++) {
                groupMins[groupIndex] = Math.min(groupMins[groupIndex], arr[l]);
                groupMaxs[groupIndex] = Math.max(groupMaxs[groupIndex], arr[l]);
            }

            int maxDiffInGroups = 0;
            for (int j = 0; j < k; j++) {
                maxDiffInGroups = Math.max(maxDiffInGroups, groupMaxs[j] - groupMins[j]);
            }
            minDiff = Math.min(minDiff, maxDiffInGroups);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 8, 3, 9, 4, 7, 6};
        int k = 3;
        int result = minMaxDifference(arr, k);
        System.out.println("Minimum Maximum Difference: " + result); //Output should be 4 or less


        int[] arr2 = {10, 2, 5, 8, 1, 9, 4};
        k = 2;
        result = minMaxDifference(arr2, k);
        System.out.println("Minimum Maximum Difference: " + result); //Output should be 7 or less

        int [] arr3 = {1,2,3,4,5};
        k = 3;
        result = minMaxDifference(arr3,k);
        System.out.println("Minimum Maximum Difference: " + result); //Output should be 2 or less
    }
}