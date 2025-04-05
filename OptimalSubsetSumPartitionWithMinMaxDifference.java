import java.util.Arrays;

/**
 * Problem Name: OptimalSubsetSumPartitionWithMinMaxDifference
 *
 * Problem Description: Given an array of positive integers, partition the array into two subsets such that
 * the absolute difference between the maximum element in each subset is minimized.  Return the minimized difference.
 * If a partition is not possible, return -1.
 */
public class OptimalSubsetSumPartitionWithMinMaxDifference {

    public static int minMaxDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1; // Handle empty input
        }

        Arrays.sort(nums);
        int n = nums.length;

        // Check for trivial cases
        if (n == 1) return 0; //Only one element
        if (n == 2) return nums[1] - nums[0]; //Only two elements


        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subsets
            int[] subset1 = new int[0];
            int[] subset2 = new int[0];

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) {
                    subset1 = Arrays.copyOf(subset1, subset1.length + 1);
                    subset1[subset1.length - 1] = nums[j];
                } else {
                    subset2 = Arrays.copyOf(subset2, subset2.length + 1);
                    subset2[subset2.length - 1] = nums[j];
                }
            }

            if(subset1.length == 0 || subset2.length == 0) continue; //Skip invalid partitions

            int max1 = Arrays.stream(subset1).max().getAsInt();
            int max2 = Arrays.stream(subset2).max().getAsInt();
            minDiff = Math.min(minDiff, Math.abs(max1 - max2));
        }

        return minDiff == Integer.MAX_VALUE ? -1 : minDiff; // Return -1 if no valid partition found

    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        System.out.println("Min Max Difference for " + Arrays.toString(nums1) + ": " + minMaxDifference(nums1)); //Expected 1

        int[] nums2 = {10, 5, 15, 20, 25};
        System.out.println("Min Max Difference for " + Arrays.toString(nums2) + ": " + minMaxDifference(nums2)); //Expected 5

        int[] nums3 = {1, 100};
        System.out.println("Min Max Difference for " + Arrays.toString(nums3) + ": " + minMaxDifference(nums3)); //Expected 99

        int[] nums4 = {1};
        System.out.println("Min Max Difference for " + Arrays.toString(nums4) + ": " + minMaxDifference(nums4)); //Expected 0


        int[] nums5 = {1, 3, 5, 7, 9, 11};
        System.out.println("Min Max Difference for " + Arrays.toString(nums5) + ": " + minMaxDifference(nums5)); //Expected 2

        int[] nums6 = {};
        System.out.println("Min Max Difference for " + Arrays.toString(nums6) + ": " + minMaxDifference(nums6)); //Expected -1
    }
}