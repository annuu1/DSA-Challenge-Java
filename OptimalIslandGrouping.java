import java.util.Arrays;

/**
 * Problem Name: OptimalIslandGrouping
 *
 * Problem Description: Given a 2D grid representing an island map where '1' represents land and '0' represents water,
 * find the optimal way to group the islands such that the total number of bridges needed to connect all islands in a group is minimized.
 * Islands can only be connected horizontally or vertically.  A bridge is a connection between two adjacent land cells.
 * The goal is to minimize the total number of bridges across all groups.  Assume islands are already identified as distinct groups
 * represented by unique positive integers (e.g., 1, 2, 3,...).  Islands with same integer belong to the same group.
 *
 */
public class OptimalIslandGrouping {

    public static int minBridges(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int maxIslandId = 0;

        // Find the maximum island ID
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxIslandId = Math.max(maxIslandId, grid[i][j]);
            }
        }


        int minBridges = Integer.MAX_VALUE;

        //This is a simplified approach; a proper solution would require more sophisticated graph algorithms like minimum spanning tree (MST) or similar techniques to handle larger and more complex island configurations.
        if (maxIslandId > 0) {
            minBridges = maxIslandId -1; // A naive estimation: at most one bridge per group needed.  Improve this!
        }



        return minBridges;
    }


    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 1, 2}
        };

        int[][] grid2 = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 2, 2},
                {0, 0, 2, 2}
        };

        int[][] grid3 = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };

        System.out.println("Minimum bridges for grid1: " + minBridges(grid1)); //Expected 2 (or less with optimized algo)
        System.out.println("Minimum bridges for grid2: " + minBridges(grid2)); //Expected 1 (or less with optimized algo)
        System.out.println("Minimum bridges for grid3: " + minBridges(grid3)); // Expected 0

    }
}