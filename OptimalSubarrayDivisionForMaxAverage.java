import java.util.Arrays;

/**
 * Problem Name:  OptimalSubarrayDivisionForMaxAverage
 *
 * Problem Description: Given an array of integers, divide the array into k subarrays such that the minimum average of any subarray is maximized.  Find this maximum minimum average.
 *
 *  For example:
 *  Input: arr = [1, 2, 3, 4, 5, 6, 7, 8], k = 3
 *  Output: 6.0  (Possible division: [1,2,3], [4,5], [6,7,8].  Averages: 2, 4.5, 7.  Minimum average is 2)
 *
 * Input: arr = [10, 5, 15, 20, 25], k = 2
 * Output: 15.0 (Possible divisions: [10, 5], [15, 20, 25]. Averages: 7.5, 20. Minimum average is 7.5) Another possible division: [10,5,15],[20,25] Averages 10, 22.5. Min 10
 *
 *  Constraints:
 *  - 1 <= arr.length <= 1000
 *  - 1 <= k <= arr.length
 *  - arr[i] >= 1
 */
public class OptimalSubarrayDivisionForMaxAverage {

    public static double maxMinAverage(int[] arr, int k) {
        int n = arr.length;
        if (k > n || k <= 0) {
            return 0; //Invalid input
        }

        double left = Arrays.stream(arr).min().getAsInt(); // Minimum element is the lower bound
        double right = Arrays.stream(arr).average().getAsDouble(); //Average is the upper bound
        double result = 0;

        while (right - left > 1e-6) { // Use a tolerance for floating point comparisons
            double mid = left + (right - left) / 2;
            if (canDivide(arr, k, mid)) {
                result = mid;
                left = mid;
            } else {
                right = mid;
            }
        }
        return result;

    }

    //Helper function to check if we can divide the array into k subarrays with minimum average >= mid
    private static boolean canDivide(int[] arr, int k, double mid) {
        int count = 1;
        double sum = 0;
        for (int num : arr) {
            sum += num;
            if (sum / count < mid) {
                count++;
                sum = num;
            }
        }
        return count <= k;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int k1 = 3;
        System.out.println("Max Min Average for arr1, k1: " + maxMinAverage(arr1, k1)); // Expected: 6.0

        int[] arr2 = {10, 5, 15, 20, 25};
        int k2 = 2;
        System.out.println("Max Min Average for arr2, k2: " + maxMinAverage(arr2, k2)); // Expected: 12.5 or close

        int[] arr3 = {5, 10, 15, 20, 25, 30};
        int k3 = 3;
        System.out.println("Max Min Average for arr3, k3: " + maxMinAverage(arr3,k3)); // Expected: 15 or close

    }
}