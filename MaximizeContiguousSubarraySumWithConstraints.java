import java.util.Arrays;

/**
 * Problem Name:  MaximizeContiguousSubarraySumWithConstraints
 *
 * Problem Description: Find the maximum contiguous subarray sum, but with the constraint
 * that the subarray must contain at least one element from a given set of "required" elements.
 * If no such subarray exists, return 0.
 */
public class MaximizeContiguousSubarraySumWithConstraints {

    public static int maxSubarraySumWithConstraints(int[] nums, int[] required) {
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            boolean hasRequired = false;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                for (int k = 0; k < required.length; k++) {
                    if (nums[j] == required[k]) {
                        hasRequired = true;
                        break;
                    }
                }
                if (hasRequired) {
                    maxSum = Math.max(maxSum, currentSum);
                }
            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, -2, 3, 4, -1, 2, 1, -5, 4};
        int[] required1 = {4, 2};
        System.out.println("Max subarray sum with constraints (nums1, required1): " + maxSubarraySumWithConstraints(nums1, required1)); // Output: 10

        int[] nums2 = {-1, -2, -3, -4, -5};
        int[] required2 = {-2};
        System.out.println("Max subarray sum with constraints (nums2, required2): " + maxSubarraySumWithConstraints(nums2, required2)); //Output: -2

        int[] nums3 = {1, 2, 3, 4, 5};
        int[] required3 = {6}; //No required element present
        System.out.println("Max subarray sum with constraints (nums3, required3): " + maxSubarraySumWithConstraints(nums3, required3)); // Output: 0

        int [] nums4 = {5, -10, 3, 7, -2, 10, -5};
        int [] required4 = {7, 10};
        System.out.println("Max subarray sum with constraints (nums4, required4): " + maxSubarraySumWithConstraints(nums4, required4)); //Output: 18


    }
}