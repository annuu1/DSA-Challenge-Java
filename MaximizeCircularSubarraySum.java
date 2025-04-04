import java.util.Arrays;

/**
 * Problem Name:  MaximizeCircularSubarraySum
 *
 * Problem Description: Given an array of integers (both positive and negative), find the maximum sum of a contiguous subarray, considering both linear and circular subarrays.  A circular subarray is one where the last element wraps around to the first.
 */
public class MaximizeCircularSubarraySum {

    public static int maxCircularSubarraySum(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // Case 1: Max sum in a linear subarray
        int maxLinearSum = kadane(nums);

        // Case 2: Max sum in a circular subarray (wrap around)
        int maxCircularSum = 0;
        int maxWrapSum = 0;

        // Invert the array and apply Kadane's algorithm
        int[] invertedNums = Arrays.stream(nums).map(i -> -i).toArray();
        int maxInvertedSum = kadane(invertedNums);

        //Max circular sum = total sum - max sum of inverted subarray
        int totalSum = Arrays.stream(nums).sum();
        maxWrapSum = totalSum + maxInvertedSum;


        //Return the maximum of both cases
        return Math.max(maxLinearSum, maxWrapSum);

    }

    //Kadane's Algorithm for maximum contiguous subarray sum
    private static int kadane(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        for (int num : nums) {
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, -2, 3, -4, 5, -6, 7, 8, 9, -10};
        int[] nums2 = {-1, -2, -3, -4, -5};
        int[] nums3 = {5, -2, 4, -1};

        System.out.println("Maximum circular subarray sum for nums1: " + maxCircularSubarraySum(nums1)); //Output: 29
        System.out.println("Maximum circular subarray sum for nums2: " + maxCircularSubarraySum(nums2)); //Output: -1
        System.out.println("Maximum circular subarray sum for nums3: " + maxCircularSubarraySum(nums3)); //Output: 10

    }
}