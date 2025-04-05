import java.util.Arrays;

/**
 * Problem Name: OptimalSubsetSumPartitionWithConsecutiveElements
 *
 * Problem Description: Given an array of integers, find the optimal partition of the array into two subsets such that:
 * 1. The absolute difference between the sums of the two subsets is minimized.
 * 2.  Elements in each subset must maintain their original consecutive order from the input array.  You cannot rearrange elements within a subset.
 *
 * For example:
 * Input: [10, 4, 6, 3, 7, 9, 2]
 * Possible Partitions (showing only a few):
 *   Subset 1: [10, 4, 6]  Subset 2: [3, 7, 9, 2]  Diff: |30 - 21| = 9
 *   Subset 1: [10, 4] Subset 2: [6, 3, 7, 9, 2] Diff: |14 - 27| = 13
 *   Subset 1: [10, 4, 6, 3] Subset 2: [7,9,2] Diff: |23 - 18| = 5
 *
 * Optimal Partition would be the one with the minimum difference.
 */
public class OptimalSubsetSumPartitionWithConsecutiveElements {

    public static int minDifference(int[] arr) {
        int n = arr.length;
        int totalSum = Arrays.stream(arr).sum();
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subsets
            int sum1 = 0;
            int sum2 = 0;
            boolean possible = true;
            int lastIndex1 = -1;
            int lastIndex2 = -1;

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) { // Element j is in subset 1
                    if (lastIndex1 == -1 || j == lastIndex1 + 1) {
                        sum1 += arr[j];
                        lastIndex1 = j;
                    } else {
                        possible = false;
                        break;
                    }
                } else { // Element j is in subset 2
                    if (lastIndex2 == -1 || j == lastIndex2 + 1) {
                        sum2 += arr[j];
                        lastIndex2 = j;
                    } else {
                        possible = false;
                        break;
                    }
                }
            }

            if (possible) {
                minDiff = Math.min(minDiff, Math.abs(sum1 - sum2));
            }
        }

        return minDiff;
    }


    public static void main(String[] args) {
        int[] arr1 = {10, 4, 6, 3, 7, 9, 2};
        System.out.println("Min difference for " + Arrays.toString(arr1) + ": " + minDifference(arr1)); // Expected output: 1 or 5 (depending on how the tie is broken)

        int[] arr2 = {1,2,3,4,5};
        System.out.println("Min difference for " + Arrays.toString(arr2) + ": " + minDifference(arr2)); // Expected output: 1

        int[] arr3 = {1,100,2,101};
        System.out.println("Min difference for " + Arrays.toString(arr3) + ": " + minDifference(arr3)); //Expected output: 199 or 1

    }
}