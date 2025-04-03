import java.util.Arrays;

/**
 * Problem Name:  MaximalIncreasingSubsequenceProduct
 *
 * Problem Description: Given an array of positive integers, find the maximal product of an increasing subsequence.  An increasing subsequence is a subsequence where each element is strictly greater than the previous element.
 *
 * For example:
 * Input: [1, 5, 2, 4, 3, 6]
 * Output: 180 (5 * 4 * 6)
 *
 * Input: [1, 2, 3, 4, 5]
 * Output: 120
 *
 * Input: [5, 4, 3, 2, 1]
 * Output: 5 (only increasing subsequences of length 1 are possible)
 *
 * Input: [10, 2, 100, 1, 20]
 * Output: 2000 (10 * 100 * 20)
 */
public class MaximalIncreasingSubsequenceProduct {

    public static long maximalIncreasingSubsequenceProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1; // Handle empty input
        }

        long[] dp = new long[nums.length];
        Arrays.fill(dp, 1); // Initialize dp array with 1 (product of a single element)

        long maxProduct = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] * nums[i]);
                }
            }
            maxProduct = Math.max(maxProduct, dp[i]);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 2, 4, 3, 6};
        System.out.println("Maximal product for " + Arrays.toString(nums1) + ": " + maximalIncreasingSubsequenceProduct(nums1)); // Output: 180

        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Maximal product for " + Arrays.toString(nums2) + ": " + maximalIncreasingSubsequenceProduct(nums2)); // Output: 120

        int[] nums3 = {5, 4, 3, 2, 1};
        System.out.println("Maximal product for " + Arrays.toString(nums3) + ": " + maximalIncreasingSubsequenceProduct(nums3)); // Output: 5

        int[] nums4 = {10, 2, 100, 1, 20};
        System.out.println("Maximal product for " + Arrays.toString(nums4) + ": " + maximalIncreasingSubsequenceProduct(nums4)); // Output: 2000

        int [] nums5 = {};
        System.out.println("Maximal product for " + Arrays.toString(nums5) + ": " + maximalIncreasingSubsequenceProduct(nums5)); // Output: 1

        int [] nums6 = {1};
        System.out.println("Maximal product for " + Arrays.toString(nums6) + ": " + maximalIncreasingSubsequenceProduct(nums6)); // Output: 1
    }
}