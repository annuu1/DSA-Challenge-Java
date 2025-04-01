import java.util.Arrays;

/**
 * Problem Name:  MinimalRemovalsForSortedSubsequence
 *
 * Problem Description: Given an array of integers, find the minimum number of elements that need to be removed to obtain a strictly increasing subsequence.  A strictly increasing subsequence is a sequence where each element is strictly greater than the previous element.
 */
public class MinimalRemovalsForSortedSubsequence {

    public static int minRemovals(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Use dynamic programming to find the length of the longest increasing subsequence
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Initialize each element as a subsequence of length 1

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // The minimum removals is the difference between the total number of elements and the length of the LIS
        int maxLength = Arrays.stream(dp).max().getAsInt();
        return nums.length - maxLength;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3, 2, 4, 5};
        System.out.println("Minimum removals for " + Arrays.toString(nums1) + ": " + minRemovals(nums1)); // Output: 1

        int[] nums2 = {5, 4, 3, 2, 1};
        System.out.println("Minimum removals for " + Arrays.toString(nums2) + ": " + minRemovals(nums2)); // Output: 4

        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Minimum removals for " + Arrays.toString(nums3) + ": " + minRemovals(nums3)); // Output: 0

        int[] nums4 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Minimum removals for " + Arrays.toString(nums4) + ": " + minRemovals(nums4)); //Output: 4

        int[] nums5 = {};
        System.out.println("Minimum removals for " + Arrays.toString(nums5) + ": " + minRemovals(nums5)); //Output: 0

        int[] nums6 = null;
        System.out.println("Minimum removals for " + nums6 + ": " + minRemovals(nums6)); //Output: 0

    }
}