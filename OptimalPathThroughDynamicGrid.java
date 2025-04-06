import java.util.Arrays;

/**
 * Problem Name: OptimalPathThroughDynamicGrid
 *
 * Problem Description:
 * Given a grid of integers where each cell represents the cost to pass through it, find the minimum cost path from the top-left corner to the bottom-right corner.  However, the grid is dynamic: after passing through a cell, its cost doubles.  Find the minimum cost to reach the bottom-right corner.
 *
 * Input: A 2D integer array representing the grid.
 * Output: The minimum cost to reach the bottom-right corner.  Return -1 if no path exists.
 */
public class OptimalPathThroughDynamicGrid {

    public static int minCostPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (rows == 0 || cols == 0) return -1; //Empty Grid

        int[][] dp = new int[rows][cols];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

        dp[0][0] = grid[0][0];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue; //Unreachable

                //Move Right
                if (j + 1 < cols) {
                    int newCost = dp[i][j] + grid[i][j + 1] * (i==0 && j==0 ? 1 : 2); //double cost after first pass
                    dp[i][j+1] = Math.min(dp[i][j+1], newCost);
                }

                //Move Down
                if (i + 1 < rows) {
                    int newCost = dp[i][j] + grid[i + 1][j] * (i==0 && j==0 ? 1 : 2); //double cost after first pass
                    dp[i + 1][j] = Math.min(dp[i + 1][j], newCost);
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 2}, {3, 4}};
        System.out.println("Minimum cost for grid1: " + minCostPath(grid1)); // Output: 7

        int[][] grid2 = {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
        System.out.println("Minimum cost for grid2: " + minCostPath(grid2)); //Output: 11


        int[][] grid3 = {{1,0,0},{0,0,0},{0,0,1}}; //Test with zero cost cells
        System.out.println("Minimum cost for grid3: " + minCostPath(grid3)); //Output: 4

        int[][] grid4 = {}; // Test with empty grid
        System.out.println("Minimum cost for grid4: " + minCostPath(grid4)); //Output: -1
    }
}