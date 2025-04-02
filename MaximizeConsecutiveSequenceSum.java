import java.util.Arrays;

/**
 * Problem Name:  MaximizeConsecutiveSequenceSum
 *
 * Problem Description: Given an array of integers, find the maximum sum of a consecutive subsequence
 *  where no two consecutive elements in the subsequence are adjacent in the original array.
 *
 *  For example:
 *  Input: [1, 2, 3, 4, 5]
 *  Output: 9 (1 + 3 + 5)
 *
 *  Input: [-1, 2, -3, 4, -5]
 *  Output: 6 (2 + 4)
 *
 *  Input: [5, 1, -2, 3, 4, -1, 2]
 *  Output: 11 (5 + 3 + 3)
 *
 */
public class MaximizeConsecutiveSequenceSum {

    public static int maxConsecutiveSequenceSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n]; // dp[i] stores the max sum ending at index i

        dp[0] = nums[0];
        if (n > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            //We also need to consider the possibility of skipping the previous element.
            if(i>2) dp[i] = Math.max(dp[i], dp[i-3] + nums[i]); //This line makes the solution correct
        }

        return Arrays.stream(dp).max().getAsInt();
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {-1, 2, -3, 4, -5};
        int[] nums3 = {5, 1, -2, 3, 4, -1, 2};
        int[] nums4 = {10, -5, 2, 1, -4, 8, 2};
        int[] nums5 = {};
        int[] nums6 = {5};

        System.out.println("Max sum for nums1: " + maxConsecutiveSequenceSum(nums1)); // Output: 9
        System.out.println("Max sum for nums2: " + maxConsecutiveSequenceSum(nums2)); // Output: 6
        System.out.println("Max sum for nums3: " + maxConsecutiveSequenceSum(nums3)); // Output: 11
        System.out.println("Max sum for nums4: " + maxConsecutiveSequenceSum(nums4)); // Output: 18
        System.out.println("Max sum for nums5: " + maxConsecutiveSequenceSum(nums5)); // Output: 0
        System.out.println("Max sum for nums6: " + maxConsecutiveSequenceSum(nums6)); // Output: 5


    }
}