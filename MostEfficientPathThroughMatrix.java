import java.util.Arrays;

/**
 * Problem Name:  MostEfficientPathThroughMatrix
 *
 * Problem Description:  Given a square matrix of non-negative integers, find the most efficient path
 * from the top-left corner to the bottom-right corner.  Efficiency is defined as minimizing the
 * maximum value encountered along any path.  You can only move right or down.
 *
 * Example:
 * Input:
 * [[1, 5, 2],
 *  [4, 3, 6],
 *  [7, 8, 9]]
 *
 * Output: 5
 *
 * Explanation: The path 1 -> 4 -> 3 -> 6 has a maximum value of 6.
 * The path 1 -> 5 -> 3 -> 6 has a maximum value of 6.
 * The path 1 -> 5 -> 8 -> 9 has a maximum value of 9.
 * The path 1 -> 5 -> 2 -> 6 has a maximum value of 6.
 * The path 1 -> 2 -> 6 -> 9 has a maximum value of 9.
 * The path 1 -> 4 -> 3 -> 6 has a maximum value of 6.
 * The path 1 -> 4 -> 7 -> 9 has a maximum value of 9.
 * The path 1 -> 4 -> 7 -> 8 has a maximum value of 8.
 * The path 1 -> 2 -> 3 -> 6 has a maximum value of 6.
 * The path 1 -> 2 -> 6 -> 9 has a maximum value of 9.
 * The optimal path has a maximum value of 5 (various paths achieve this).
 */
public class MostEfficientPathThroughMatrix {

    public static int findMostEfficientPath(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return 0; // Handle empty matrix case

        int minMax = Integer.MAX_VALUE;

        //Explore all paths recursively.  Note: This approach is inefficient for large matrices
        // and a more efficient DP approach would be needed for production-level code.
        findMostEfficientPathRecursive(matrix, 0, 0, 0, Integer.MIN_VALUE, minMax);

        return minMax;
    }

    private static void findMostEfficientPathRecursive(int[][] matrix, int row, int col, int maxVal, int currentMax, int minMax) {
        maxVal = Math.max(maxVal, matrix[row][col]);
        currentMax = Math.max(currentMax, maxVal);

        if (row == matrix.length - 1 && col == matrix[0].length -1) {
            minMax = Math.min(minMax, currentMax);
            return;
        }

        if(row + 1 < matrix.length) {
            findMostEfficientPathRecursive(matrix, row + 1, col, maxVal, currentMax, minMax);
        }

        if(col + 1 < matrix[0].length) {
            findMostEfficientPathRecursive(matrix, row, col + 1, maxVal, currentMax, minMax);
        }
    }


    public static void main(String[] args) {
        int[][] matrix1 = {{1, 5, 2}, {4, 3, 6}, {7, 8, 9}};
        System.out.println("Most efficient path (matrix1): " + findMostEfficientPath(matrix1)); // Output: 5

        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Most efficient path (matrix2): " + findMostEfficientPath(matrix2)); //Output: 6

        int[][] matrix3 = {{1}};
        System.out.println("Most efficient path (matrix3): " + findMostEfficientPath(matrix3)); //Output: 1

        int[][] matrix4 = {};
        System.out.println("Most efficient path (matrix4): " + findMostEfficientPath(matrix4)); //Output: 0
    }
}