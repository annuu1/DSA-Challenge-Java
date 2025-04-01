import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  MostFrequentSubarraySum
 *
 * Problem Description: Given an array of integers, find the subarray with the most frequent sum.
 *  If multiple subarrays have the same maximum frequency, return any one of them.
 *  The subarray must have at least one element.
 */
public class MostFrequentSubarraySum {

    public static int[] findMostFrequentSubarray(int[] arr) {
        Map<Integer, Integer> sumFrequency = new HashMap<>();
        Map<Integer, int[]> sumSubarray = new HashMap<>();

        int maxFrequency = 0;
        int[] mostFrequentSubarray = {};


        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                sumFrequency.put(currentSum, sumFrequency.getOrDefault(currentSum, 0) + 1);
                if (sumFrequency.get(currentSum) > maxFrequency) {
                    maxFrequency = sumFrequency.get(currentSum);
                    int[] subarray = Arrays.copyOfRange(arr, i, j + 1);
                    mostFrequentSubarray = subarray;
                    sumSubarray.put(currentSum, subarray);

                }
                //In case of ties, we just keep the first one encountered.
            }
        }

        return mostFrequentSubarray;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, -3, 2, 1, -1};
        int[] result1 = findMostFrequentSubarray(arr1);
        System.out.println("Most frequent subarray sum for " + Arrays.toString(arr1) + ": " + Arrays.toString(result1)); //Expected Output should contain 2


        int[] arr2 = {1, -1, 1, -1, 1, -1};
        int[] result2 = findMostFrequentSubarray(arr2);
        System.out.println("Most frequent subarray sum for " + Arrays.toString(arr2) + ": " + Arrays.toString(result2)); //Expected Output could contain 1, -1 or a single 1 or -1.


        int[] arr3 = {-1, -2, -3, -4, -5};
        int[] result3 = findMostFrequentSubarray(arr3);
        System.out.println("Most frequent subarray sum for " + Arrays.toString(arr3) + ": " + Arrays.toString(result3)); //Expected Output should contain only one element.


        int[] arr4 = {};
        int[] result4 = findMostFrequentSubarray(arr4);
        System.out.println("Most frequent subarray sum for " + Arrays.toString(arr4) + ": " + Arrays.toString(result4)); //Expected Output should be empty array.
    }
}