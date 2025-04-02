import java.util.Arrays;

/**
 * Problem Name:  MaximizeAlternatingSumOfSubsequences
 *
 * Problem Description: Given an array of integers, find the maximum alternating sum
 * that can be obtained by selecting non-overlapping subsequences from the array.
 * The alternating sum is defined as the sum of elements at odd indices minus the sum
 * of elements at even indices.  Subsequences can be of any length.
 *
 * Example:
 * Input: [1, 2, 3, 4, 5]
 * Output: 3  (Explanation: Select subsequences [1,3,5] (1+3+5=9) and [2] (2), and [4].
 * Alternating sum for [1,3,5] is 1 - 3 + 5 = 3. Alternating sum for [2] is 2. Alternating sum for [4] is 4.  Maximum is 3.
 *         Alternatively: [1, 4] (1 - 4 = -3), [2, 5] (2 - 5 = -3), [3] (3). Max is 3)
 * Input: [-1, 1, -1, 1]
 * Output: 2 (Explanation: Select subsequences [-1, -1] and [1,1]. Alternating sum = -1 + 1 - 1 + 1 = 0. Select [1,1]. Alternating sum is 2. )

 */
public class MaximizeAlternatingSumOfSubsequences {

    public static int maxAlternatingSum(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subsequences
            int[] subsequence = new int[0];
            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) {
                    subsequence = Arrays.copyOf(subsequence, subsequence.length + 1);
                    subsequence[subsequence.length - 1] = nums[j];
                }
            }

            if (subsequence.length > 0) {
                maxSum = Math.max(maxSum, calculateAlternatingSum(subsequence));
            }

        }
        return maxSum;

    }

    private static int calculateAlternatingSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                sum += arr[i];
            } else {
                sum -= arr[i];
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        System.out.println("Max alternating sum for " + Arrays.toString(nums1) + ": " + maxAlternatingSum(nums1)); // Output: 3

        int[] nums2 = {-1, 1, -1, 1};
        System.out.println("Max alternating sum for " + Arrays.toString(nums2) + ": " + maxAlternatingSum(nums2)); // Output: 2

        int[] nums3 = {5, -2, 8, -3, 10};
        System.out.println("Max alternating sum for " + Arrays.toString(nums3) + ": " + maxAlternatingSum(nums3)); // Output: 16

        int[] nums4 = {};
        System.out.println("Max alternating sum for " + Arrays.toString(nums4) + ": " + maxAlternatingSum(nums4)); //Output: 0


    }
}