import java.util.Arrays;

public class MinInsertionsForSortedSequence {

    /**
     * Problem: MinInsertionsForSortedSequence
     *
     * Given an array of integers, find the minimum number of integers that need to be inserted
     * into the array to make it a sorted sequence (non-decreasing).  Insertions can be made
     * anywhere in the array.
     *
     * For example:
     * Input: [1, 3, 2, 5, 4]
     * Output: 2 (Insert 2 after 1 and 4 after 3 to make [1, 2, 3, 4, 5])
     *
     * Input: [5, 1, 2, 3, 4]
     * Output: 4 (Insert many numbers)
     *
     * Input: [1,2,3,4,5]
     * Output: 0
     */

    public static int minInsertions(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1); // Initialize each element as a subsequence of length 1

        int maxLen = 1; // Initialize the maximum length of a sorted subsequence

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return arr.length - maxLen; // Number of insertions = total elements - length of longest sorted subsequence

    }


    public static void main(String[] args) {
        int[] arr1 = {1, 3, 2, 5, 4};
        System.out.println("Min insertions for " + Arrays.toString(arr1) + ": " + minInsertions(arr1)); // Output: 2

        int[] arr2 = {5, 1, 2, 3, 4};
        System.out.println("Min insertions for " + Arrays.toString(arr2) + ": " + minInsertions(arr2)); // Output: 4

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Min insertions for " + Arrays.toString(arr3) + ": " + minInsertions(arr3)); // Output: 0

        int[] arr4 = {};
        System.out.println("Min insertions for " + Arrays.toString(arr4) + ": " + minInsertions(arr4)); // Output: 0

        int[] arr5 = {10,9,8,7,6};
        System.out.println("Min insertions for " + Arrays.toString(arr5) + ": " + minInsertions(arr5)); // Output: 4

    }
}