import java.util.Arrays;

/**
 * Problem Name:  MinimumCostToBuildBinarySearchTreeWithLevelOrder
 *
 * Problem Description: Given an array of integers representing the level-order traversal of a
 * binary search tree (BST), find the minimum cost to construct this BST.  The cost of constructing
 * a node is the value of the node itself.  The goal is to minimize the total cost of all nodes in
 * the constructed BST.  Assume all integers in the array are unique.
 */
public class MinimumCostToBuildBinarySearchTreeWithLevelOrder {

    public static int minCost(int[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0) {
            return 0;
        }

        // Sort the level order traversal to easily construct the BST
        Arrays.sort(levelOrder);

        // Memoization table to store the minimum cost for subproblems
        int[][] dp = new int[levelOrder.length][levelOrder.length];


        return minCostHelper(levelOrder, 0, levelOrder.length - 1, dp);
    }


    private static int minCostHelper(int[] arr, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int cost = arr[i] + minCostHelper(arr, start, i - 1, dp) + minCostHelper(arr, i + 1, end, dp);
            minCost = Math.min(minCost, cost);
        }

        dp[start][end] = minCost;
        return minCost;
    }


    public static void main(String[] args) {
        int[] levelOrder1 = {2, 1, 3};
        int cost1 = minCost(levelOrder1);
        System.out.println("Minimum cost for level order " + Arrays.toString(levelOrder1) + ": " + cost1); // Output: 6

        int[] levelOrder2 = {10, 5, 15, 3, 7, 12, 18};
        int cost2 = minCost(levelOrder2);
        System.out.println("Minimum cost for level order " + Arrays.toString(levelOrder2) + ": " + cost2); // Output: 60

        int[] levelOrder3 = {5,3,1,9,8};
        int cost3 = minCost(levelOrder3);
        System.out.println("Minimum cost for level order " + Arrays.toString(levelOrder3) + ": " + cost3);


    }
}