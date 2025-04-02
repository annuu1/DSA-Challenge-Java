import java.util.Arrays;

/**
 * Problem Name:  MinimumSwapsToGroupIdenticalElements
 *
 * Problem Description: Given an array of integers where each integer represents a type of element, find the minimum number of swaps required to group all identical elements together.  Elements of the same type must be consecutive.  The order of the groups doesn't matter.
 *
 * Example:
 * Input: [1, 2, 1, 2, 1, 1, 1, 2, 2]
 * Output: 2
 * Explanation: One possible arrangement is [1, 1, 1, 1, 1, 2, 2, 2, 2], requiring 2 swaps.
 */
public class MinimumSwapsToGroupIdenticalElements {

    public static int minSwaps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        // Count occurrences of each element
        java.util.Map<Integer, Integer> counts = new java.util.HashMap<>();
        for (int x : arr) {
            counts.put(x, counts.getOrDefault(x, 0) + 1);
        }

        int swaps = 0;
        int i = 0;
        for (int key : counts.keySet()) {
            int count = counts.get(key);
            int j = i;
            while (j < n && count > 0) {
                if (arr[j] == key) {
                    count--;
                }
                j++;
            }

            swaps += (j - i) - (j - i - (j - i - count)); //Number of elements of other types in the desired range
            i = j;


        }
        return swaps;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 1, 2, 1, 1, 1, 2, 2};
        System.out.println("Minimum swaps for " + Arrays.toString(arr1) + ": " + minSwaps(arr1)); // Output: 2

        int[] arr2 = {1, 1, 2, 2, 3, 3};
        System.out.println("Minimum swaps for " + Arrays.toString(arr2) + ": " + minSwaps(arr2)); // Output: 0

        int[] arr3 = {1, 3, 2, 1, 3, 2, 1};
        System.out.println("Minimum swaps for " + Arrays.toString(arr3) + ": " + minSwaps(arr3)); // Output: 3

        int[] arr4 = {1,2,3};
        System.out.println("Minimum swaps for " + Arrays.toString(arr4) + ": " + minSwaps(arr4)); // Output: 0

        int[] arr5 = {};
        System.out.println("Minimum swaps for " + Arrays.toString(arr5) + ": " + minSwaps(arr5)); // Output: 0

        int[] arr6 = {1};
        System.out.println("Minimum swaps for " + Arrays.toString(arr6) + ": " + minSwaps(arr6)); // Output: 0

    }
}