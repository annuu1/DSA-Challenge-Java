import java.util.Arrays;

/**
 * Problem Name:  OptimalLibraryBookSelection
 *
 * Problem Description:
 * A library has N books, each with a unique ID and a given value.  You want to select a subset of books
 * such that the sum of their values is maximized, but with the constraint that no two selected books can
 * have IDs that are consecutive.  Find the maximum possible sum of values.
 */
public class OptimalLibraryBookSelection {

    public static int maxBookValueSum(int[] bookIds, int[] bookValues) {
        if (bookIds.length != bookValues.length || bookIds.length == 0) {
            return 0; // Handle invalid input
        }

        int n = bookIds.length;
        // dp[i][j] represents the maximum sum achievable considering books up to index i,
        // where j is 1 if the last selected book is included, and 0 otherwise.

        int[][] dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            // Option 1: Don't include the current book
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

            // Option 2: Include the current book (if it's not consecutive)
            boolean consecutive = false;
            if (i > 1 && bookIds[i - 1] == bookIds[i - 2] + 1) {
                consecutive = true;
            }
            if (!consecutive) {
                dp[i][1] = bookValues[i - 1] + dp[i - 1][0];
            } else {
                dp[i][1] = dp[i-1][1]; //Can't include, maintain previous value
            }
        }
        return Math.max(dp[n][0], dp[n][1]);
    }


    public static void main(String[] args) {
        int[] bookIds = {1, 2, 3, 4, 5, 7, 8, 9};
        int[] bookValues = {10, 5, 15, 7, 20, 12, 8, 18};

        int maxSum = maxBookValueSum(bookIds, bookValues);
        System.out.println("Maximum sum of book values: " + maxSum); // Output should be 58


        int[] bookIds2 = {1,3,5,7,9};
        int[] bookValues2 = {10,20,30,40,50};
        maxSum = maxBookValueSum(bookIds2, bookValues2);
        System.out.println("Maximum sum of book values: " + maxSum); // Output should be 150

        int[] bookIds3 = {1,2,3,4,5};
        int[] bookValues3 = {10,10,10,10,10};
        maxSum = maxBookValueSum(bookIds3, bookValues3);
        System.out.println("Maximum sum of book values: " + maxSum); // Output should be 30


    }
}