import java.util.Arrays;

public class MaxSumNonOverlappingSubarrays {

    /**
     * Problem: Max Sum Non-Overlapping Subarrays
     *
     * Given an array of integers, find the maximum sum that can be obtained by summing the elements of
     * multiple non-overlapping subarrays, where each subarray has a length of at least k.
     *
     * Example:
     * Input: nums = [1, -2, 3, 4, -1, 2, 1, -5, 4], k = 2
     * Output: 11 (3+4-1+2+1 = 9 or 4-1+2+1-5+4 = 7)
     *          (Subarrays can be [3, 4, -1], [2, 1] or [3,4,-1,2,1] etc)
     *
     */

    public static int maxSumNonOverlappingSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0; //Handle edge cases
        }

        int n = nums.length;
        int[] dp = new int[n + 1]; // dp[i] stores the max sum up to index i

        for (int i = k; i <= n; i++) {
            int currentMax = Integer.MIN_VALUE;
            //Find the max sum subarray ending at i with length >= k
            for (int j = i - k; j < i; j++) {
                int currentSum = 0;
                for (int l = j; l < i; l++) {
                    currentSum += nums[l];
                }
                currentMax = Math.max(currentMax, currentSum);
            }


            //Dynamic programming step: consider including or excluding the current subarray
            dp[i] = Math.max(dp[i-1], (i-k >= 0 ? dp[i-k] : 0) + currentMax);

        }
        return dp[n];
    }


    public static void main(String[] args) {
        int[] nums1 = {1, -2, 3, 4, -1, 2, 1, -5, 4};
        int k1 = 2;
        System.out.println("Max sum for nums1 with k = " + k1 + ": " + maxSumNonOverlappingSubarrays(nums1, k1)); //Output should be 11


        int[] nums2 = {-1,-2,-3,-4,-5};
        int k2 = 2;
        System.out.println("Max sum for nums2 with k = " + k2 + ": " + maxSumNonOverlappingSubarrays(nums2, k2)); //Output should be -3


        int[] nums3 = {5, 10, 15, 20, 25};
        int k3 = 3;
        System.out.println("Max sum for nums3 with k = " + k3 + ": " + maxSumNonOverlappingSubarrays(nums3, k3)); //Output should be 60

        int [] nums4 = {};
        int k4 = 2;
        System.out.println("Max sum for nums4 with k = " + k4 + ": " + maxSumNonOverlappingSubarrays(nums4, k4)); //Output should be 0

    }
}