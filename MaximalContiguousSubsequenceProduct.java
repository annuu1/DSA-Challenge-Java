import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  MaximalContiguousSubsequenceProduct
 *
 * Problem Description: Given an array of integers (positive, negative, and zero), find the contiguous subsequence with the maximal product.
 *  The subsequence can contain one or more elements.  Handle cases with all zeros and all negative numbers appropriately.
 */
public class MaximalContiguousSubsequenceProduct {

    public static long maxProductSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // Handle empty input
        }

        long max_so_far = nums[0];
        long min_so_far = nums[0];
        long max_product = nums[0];

        for (int i = 1; i < nums.length; i++) {
            long temp_max = Math.max(nums[i], Math.max(nums[i] * max_so_far, nums[i] * min_so_far));
            min_so_far = Math.min(nums[i], Math.min(nums[i] * max_so_far, nums[i] * min_so_far));
            max_so_far = temp_max;
            max_product = Math.max(max_product, max_so_far);

        }
        return max_product;
    }


    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Max product subsequence for " + Arrays.toString(nums1) + ": " + maxProductSubsequence(nums1)); // Output: 24

        int[] nums2 = {-2, 0, -1};
        System.out.println("Max product subsequence for " + Arrays.toString(nums2) + ": " + maxProductSubsequence(nums2)); // Output: 0

        int[] nums3 = {-2, -3, -1};
        System.out.println("Max product subsequence for " + Arrays.toString(nums3) + ": " + maxProductSubsequence(nums3)); // Output: 6

        int[] nums4 = {-1, -2, -3, 0, 1, 2, 3};
        System.out.println("Max product subsequence for " + Arrays.toString(nums4) + ": " + maxProductSubsequence(nums4)); // Output: 6

        int[] nums5 = {-4,-3,-2,-1,0,1,2,3,4};
        System.out.println("Max product subsequence for " + Arrays.toString(nums5) + ": " + maxProductSubsequence(nums5)); // Output: 24

        int[] nums6 = {0,0,0};
        System.out.println("Max product subsequence for " + Arrays.toString(nums6) + ": " + maxProductSubsequence(nums6)); // Output: 0


    }
}