import java.util.Arrays;

/**
 * Problem Name:  MinimumJumpsToReachEndWithVariableJumpLengths
 *
 * Problem Description: Given an array of non-negative integers where each element represents the maximum jump length from that position,
 * find the minimum number of jumps required to reach the end of the array.  Return -1 if it's impossible to reach the end.
 *
 * Example:
 * Input:  jumps = [2, 3, 1, 1, 4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the end is 2.
 * Jump 1 from index 0 to index 1 (jumps[0] = 2).
 * Jump 2 from index 1 to index 4 (jumps[1] = 3).
 */
public class MinimumJumpsToReachEndWithVariableJumpLengths {

    public static int minJumps(int[] jumps) {
        int n = jumps.length;
        if (n <= 1) return 0; // Already at the end or only one element

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue; // Cannot reach this index

            for (int j = i + 1; j <= Math.min(i + jumps[i], n - 1); j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
    }

    public static void main(String[] args) {
        int[] jumps1 = {2, 3, 1, 1, 4};
        int[] jumps2 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        int[] jumps3 = {3, 2, 1, 0, 4}; //Impossible case

        System.out.println("Minimum jumps for jumps1: " + minJumps(jumps1)); // Output: 2
        System.out.println("Minimum jumps for jumps2: " + minJumps(jumps2)); // Output: 3
        System.out.println("Minimum jumps for jumps3: " + minJumps(jumps3)); // Output: -1

    }
}