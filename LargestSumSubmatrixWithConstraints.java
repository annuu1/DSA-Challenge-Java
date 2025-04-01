import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  LargestSumSubmatrixWithConstraints
 *
 * Problem Description: Given a 2D integer array (matrix) and a target sum 'k', find the submatrix with the largest sum that is less than or equal to 'k'.
 * Return the sum of the largest submatrix found.  If no submatrix meets the criteria, return 0.
 *
 * Note:  Submatrices must be rectangular.
 */
public class LargestSumSubmatrixWithConstraints {

    public static int largestSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;

        int maxSum = 0;

        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];
            for (int right = left; right < cols; right++) {
                for (int i = 0; i < rows; i++) {
                    rowSum[i] += matrix[i][right];
                }
                maxSum = Math.max(maxSum, maxSubArraySum(rowSum, k));
            }
        }
        return maxSum;
    }


    //Helper function to find the maximum sum of a subarray <= k using Kadane's algorithm with modification
    private static int maxSubArraySum(int[] nums, int k) {
        int maxSoFar = 0;
        int currentMax = 0;
        for (int num : nums) {
            currentMax += num;
            if (currentMax <= k) {
                maxSoFar = Math.max(maxSoFar, currentMax);
            }
            if(currentMax >k){
                //Reset if we exceed k
                currentMax = 0;
            }

        }
        return maxSoFar;

    }


    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        int k1 = 10;
        System.out.println("Largest submatrix sum <= " + k1 + ": " + largestSumSubmatrix(matrix1, k1)); // Expected: 29


        int[][] matrix2 = {
                {2,2,2},
                {2,2,2},
                {2,2,2}
        };
        int k2 = 10;
        System.out.println("Largest submatrix sum <= " + k2 + ": " + largestSumSubmatrix(matrix2,k2)); //Expected: 10

        int[][] matrix3 = {{-1,-2,-3}};
        int k3 = 0;
        System.out.println("Largest submatrix sum <= " + k3 + ": " + largestSumSubmatrix(matrix3,k3)); //Expected 0

        int[][] matrix4 = {};
        int k4 = 5;
        System.out.println("Largest submatrix sum <= " + k4 + ": " + largestSumSubmatrix(matrix4,k4)); //Expected 0

    }
}