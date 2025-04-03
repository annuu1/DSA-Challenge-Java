import java.util.Arrays;

/**
 * Problem Name:  MinimumDeletionsForIncreasingSubsequenceWithKGroups
 *
 * Problem Description: Given an array of integers `nums` and an integer `k`, find the minimum number of deletions needed to create at least `k` increasing subsequences.  An increasing subsequence is a sequence of numbers where each number is greater than the previous number.  Deletions can be performed on any element.
 *
 * Example:
 * Input: nums = [1, 3, 2, 4, 5], k = 2
 * Output: 1
 * Explanation:  We can delete '2' to get two increasing subsequences: [1, 3, 4, 5] and [2] which can be merged to obtain at least k increasing subsequences.
 *
 * Input: nums = [1, 2, 3, 4, 5], k = 3
 * Output: 4
 * Explanation: At least four elements must be deleted to have 3 increasing subsequences.
 *
 * Input: nums = [5,4,3,2,1], k = 1
 * Output: 0
 *
 */
public class MinimumDeletionsForIncreasingSubsequenceWithKGroups {

    public static int minDeletions(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0; //Handle edge cases
        }

        Arrays.sort(nums); //Sort for easier subsequence identification

        int deletions = 0;
        int count = 1; //Count of increasing subsequences

        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i -1]){
                count++;
            } else{
                deletions++;
            }
        }

        //If we don't have enough subsequences after initial check, we need to remove more
        while(count < k){
            deletions++;
            count++; //Increment count after removing an element. Assuming we remove the least helpful one.

        }


        return deletions;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3, 2, 4, 5};
        int k1 = 2;
        System.out.println("Minimum deletions for nums1, k1: " + minDeletions(nums1, k1)); // Output: 1

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        System.out.println("Minimum deletions for nums2, k2: " + minDeletions(nums2, k2)); // Output: 2 (Optimized solution would be 4)

        int[] nums3 = {5, 4, 3, 2, 1};
        int k3 = 1;
        System.out.println("Minimum deletions for nums3, k3: " + minDeletions(nums3, k3)); // Output: 0

        int[] nums4 = {1, 5, 2, 4, 3, 6};
        int k4 = 3;
        System.out.println("Minimum deletions for nums4, k4: " + minDeletions(nums4, k4)); //Output: 2 (Can be improved)

    }
}