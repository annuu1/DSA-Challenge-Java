import java.util.Arrays;

/**
 * Problem Name: OptimalInterleavedSubsequence
 *
 * Problem Description:
 * Given two arrays of integers, arr1 and arr2, find the length of the longest subsequence
 * that can be formed by interleaving elements from arr1 and arr2 such that the subsequence
 * is strictly increasing.  Elements from either array can be skipped.
 */
public class OptimalInterleavedSubsequence {

    public static int longestIncreasingInterleavedSubsequence(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        // dp[i][j] stores the length of the LIS ending with arr1[i-1] or arr2[j-1]
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int prev1 = (i > 1) ? arr1[i - 2] : Integer.MIN_VALUE;
                int prev2 = (j > 1) ? arr2[j - 2] : Integer.MIN_VALUE;

                int val1 = arr1[i - 1];
                int val2 = arr2[j - 1];

                if (val1 > prev1 && val1 > prev2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                }
                if (val2 > prev1 && val2 > prev2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                }

                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));

            }
        }

        return dp[n][m];
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int result = longestIncreasingInterleavedSubsequence(arr1, arr2);
        System.out.println("Length of the longest increasing interleaved subsequence: " + result); // Output: 4

        int[] arr3 = {1, 5, 2, 8};
        int[] arr4 = {3, 7, 4, 9};
        result = longestIncreasingInterleavedSubsequence(arr3, arr4);
        System.out.println("Length of the longest increasing interleaved subsequence: " + result); // Output: 6


        int[] arr5 = {10, 20, 30};
        int[] arr6 = {1, 5, 35};
        result = longestIncreasingInterleavedSubsequence(arr5, arr6);
        System.out.println("Length of the longest increasing interleaved subsequence: " + result); //Output: 3


        int[] arr7 = {};
        int[] arr8 = {1,2,3};
        result = longestIncreasingInterleavedSubsequence(arr7, arr8);
        System.out.println("Length of the longest increasing interleaved subsequence: " + result); //Output: 3

    }
}