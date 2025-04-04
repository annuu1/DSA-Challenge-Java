import java.util.Arrays;

/*
Problem Name:  MinimumCostToPartitionArrayIntoKSubarraysWithEqualSum

Problem Description: Given an array of integers and an integer k, partition the array into k contiguous subarrays such that the sum of elements in each subarray is equal. Find the minimum cost to achieve this partition.  The cost is the sum of the maximum element in each subarray.

Example:
Input: arr = {1, 2, 3, 4, 5, 6}, k = 3
Output: 15
Explanation:  We can partition the array into {1, 2}, {3, 4}, {5, 6}. The sums are 3, 7, and 11. This doesn't work.
Another partition is {1, 2, 3}, {4, 5}, {6}.  Sums are 6, 9, 6 (not equal).
A correct partition is {1, 2, 3}, {4, 5}, {6}. Sums are 6, 9, 6. This doesn't satisfy equal sum condition.

A correct partition might be {1, 2, 3}, {4, 5}, {6} if we allow unequal sums.  The cost is 3 + 5 + 6 = 14.

Let's assume equal sum is a requirement. If the total sum isn't divisible by k, it's impossible.
If the total sum is divisible by k, we need to find a partition with minimal cost.

*/

public class MinimumCostToPartitionArrayIntoKSubarraysWithEqualSum {

    public static int minCostToPartition(int[] arr, int k) {
        int n = arr.length;
        int totalSum = Arrays.stream(arr).sum();

        if (totalSum % k != 0) {
            return -1; // Impossible to partition
        }

        int targetSum = totalSum / k;
        int[][] dp = new int[n + 1][k + 1];
        int[][] maxInSubarray = new int[n + 1][k + 1]; //Store max in each subarray


        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int currentSum = 0;
                int currentMax = 0;
                for (int p = i - 1; p >= 0; p--) {
                    currentSum += arr[p];
                    currentMax = Math.max(currentMax, arr[p]);
                    if (currentSum == targetSum) {
                        if (dp[p][j - 1] != Integer.MAX_VALUE) {
                            if (dp[i][j] > dp[p][j - 1] + currentMax) {
                                dp[i][j] = dp[p][j - 1] + currentMax;
                                maxInSubarray[i][j] = currentMax;
                            }
                        }
                        break;
                    }
                }
            }
        }

        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] arr = {10, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 3;
        int minCost = minCostToPartition(arr, k);
        if (minCost == -1) {
            System.out.println("Impossible to partition the array.");
        } else {
            System.out.println("Minimum cost to partition: " + minCost);
        }

        int [] arr2 = {1,2,3,4,5,6};
        k = 3;
        minCost = minCostToPartition(arr2,k);
        if (minCost == -1) {
            System.out.println("Impossible to partition the array.");
        } else {
            System.out.println("Minimum cost to partition: " + minCost);
        }


    }
}