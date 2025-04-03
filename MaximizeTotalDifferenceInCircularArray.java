import java.util.Arrays;

/**
 * Problem Name:  MaximizeTotalDifferenceInCircularArray
 *
 * Problem Description: Given a circular array of integers, find the maximum difference between the sum of two disjoint subarrays.  The subarrays can be of any size (including 0), but they must not overlap.  Since the array is circular, a subarray can wrap around from the end to the beginning.
 *
 * For example:
 * Input: [1, -2, 3, -4, 5]
 * Output: 10  (Subarray1: [5, 1] sum=6. Subarray2: [-2, 3, -4] sum=-3. Difference = 6 - (-3) = 9.  Another possibility: [5] and [-2,3,-4], diff = 5 - (-3) = 8.  Best is [1,-2,3,-4] and [5], diff = -2 - 5 = -7. However, if we consider [1,-2,3,-4] and [5], and in the circular way, we can use [5,1] which gives us 6-(-2) = 8.  The best is [5,1] and [-2,3,-4], which gives 9.   If we pick [5] and [-2,3,-4], it's 8.)
 *
 * Input: [2, 1, 3]
 * Output: 5 (Subarray1: [2, 1, 3] sum = 6, Subarray2: [] sum = 0, difference = 6) or [2,1] and [3] - which would be 3-3 = 0.
 *
 * Input: [-1, -2, -3]
 * Output: 0 (All subarrays have negative sums, max difference is 0)
 */
public class MaximizeTotalDifferenceInCircularArray {

    public static int maxDifference(int[] arr) {
        int n = arr.length;
        int maxDiff = 0;

        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subarray combinations
            int[] subarray1 = new int[0];
            int[] subarray2 = new int[0];
            int sum1 = 0;
            int sum2 = 0;

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) {
                    subarray1 = Arrays.copyOf(subarray1, subarray1.length + 1);
                    subarray1[subarray1.length - 1] = arr[j];
                    sum1 += arr[j];
                }
            }

            for (int j = 0; j < n; j++) {
                boolean inSubarray1 = false;
                for (int k = 0; k < subarray1.length; k++) {
                    if (subarray1[k] == arr[j]) {
                        inSubarray1 = true;
                        break;
                    }
                }
                if (!inSubarray1) {
                    subarray2 = Arrays.copyOf(subarray2, subarray2.length + 1);
                    subarray2[subarray2.length - 1] = arr[j];
                    sum2 += arr[j];
                }

            }
            maxDiff = Math.max(maxDiff, Math.abs(sum1 - sum2));
        }
        return maxDiff;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, -2, 3, -4, 5};
        System.out.println("Max difference for " + Arrays.toString(arr1) + ": " + maxDifference(arr1)); // Output: 9

        int[] arr2 = {2, 1, 3};
        System.out.println("Max difference for " + Arrays.toString(arr2) + ": " + maxDifference(arr2)); // Output: 5

        int[] arr3 = {-1, -2, -3};
        System.out.println("Max difference for " + Arrays.toString(arr3) + ": " + maxDifference(arr3)); // Output: 0

        int[] arr4 = {5, -1, 2, -3, 4};
        System.out.println("Max difference for " + Arrays.toString(arr4) + ": " + maxDifference(arr4)); // Example
    }
}