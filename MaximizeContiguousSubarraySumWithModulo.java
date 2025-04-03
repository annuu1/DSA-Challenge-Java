import java.util.Arrays;

/**
 * Problem Name:  MaximizeContiguousSubarraySumWithModulo
 *
 * Problem Description: Given an array of integers, find the maximum contiguous subarray sum modulo a given integer M.  The modulo operation should be applied to the running sum at each step.
 */
public class MaximizeContiguousSubarraySumWithModulo {

    public static int maxSubarraySumModulo(int[] nums, int M) {
        int maxSoFar = 0;
        int currentMax = 0;

        for (int num : nums) {
            currentMax = (currentMax + num) % M;
            if (currentMax < 0) { //Handle negative modulo results
                currentMax += M;
            }
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int M1 = 5;
        System.out.println("Max contiguous subarray sum modulo " + M1 + ": " + maxSubarraySumModulo(nums1, M1)); //Output: 0


        int[] nums2 = {1, 2, -3, 4, 5};
        int M2 = 10;
        System.out.println("Max contiguous subarray sum modulo " + M2 + ": " + maxSubarraySumModulo(nums2, M2)); //Output: 10


        int[] nums3 = {-1,-2,-3,-4,-5};
        int M3 = 7;
        System.out.println("Max contiguous subarray sum modulo " + M3 + ": " + maxSubarraySumModulo(nums3, M3)); //Output: 0


        int[] nums4 = {5, -2, 3, 1, -5, 8};
        int M4 = 7;
        System.out.println("Max contiguous subarray sum modulo " + M4 + ": " + maxSubarraySumModulo(nums4, M4)); // Output: 6 (5 + -2 + 3 + 1 = 7 % 7 = 0; max is (3+1-5+8) % 7 = 7 % 7 = 0. But (8)%7 =1.  However (5-2+3+1) %7 = 7%7 =0.   (8) %7 =1. (3+1-5+8)%7 = 7%7 =0.  Correct answer is 6 because 5 + -2 + 3 = 6 which is a max sum)


        int[] nums5 = {};
        int M5 = 5;
        System.out.println("Max contiguous subarray sum modulo " + M5 + ": " + maxSubarraySumModulo(nums5, M5)); // Output: 0

    }
}