import java.util.Arrays;

/**
 * Problem Name: OptimalLibraryShelfArrangement
 *
 * Problem Description:
 * You are given an array of integers representing the widths of books.  You have a bookshelf with a fixed width W.  You want to arrange the books on the shelf such that the number of shelves used is minimized.  Each shelf can hold books with a total width less than or equal to W. Books must be arranged in the order they appear in the input array.  Find the minimum number of shelves needed.
 */
public class OptimalLibraryShelfArrangement {

    public static int minShelves(int[] widths, int w) {
        int n = widths.length;
        // dp[i] stores the minimum number of shelves needed to arrange the first i books.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case: 0 books need 0 shelves

        for (int i = 1; i <= n; i++) {
            int currentWidth = 0;
            // Try placing the current book on the existing shelves.
            for (int j = i - 1; j >= 0; j--) {
                currentWidth += widths[j];
                if (currentWidth <= w) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        int[] widths1 = {6, 5, 7, 10, 4, 8, 2, 1};
        int w1 = 15;
        System.out.println("Minimum shelves needed for widths1: " + minShelves(widths1, w1)); // Expected: 3


        int[] widths2 = {2,3,1,1,1,5};
        int w2 = 5;
        System.out.println("Minimum shelves needed for widths2: " + minShelves(widths2, w2)); // Expected 3

        int[] widths3 = {1,2,3,4,5};
        int w3 = 10;
        System.out.println("Minimum shelves needed for widths3: " + minShelves(widths3, w3)); // Expected 1

        int[] widths4 = {};
        int w4 = 10;
        System.out.println("Minimum shelves needed for widths4: " + minShelves(widths4, w4)); // Expected 0

    }
}