import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  MaximalPairwiseProductWithConstraints
 *
 * Problem Description: Given an array of integers and a threshold value 'k', find the maximum pairwise product
 * of two distinct elements in the array, such that the sum of the two elements is less than or equal to 'k'.
 *  If no such pair exists, return -1.
 */
public class MaximalPairwiseProductWithConstraints {

    public static long maximalPairwiseProduct(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return -1; // Not enough elements for a pair
        }

        Arrays.sort(arr); //Sort for efficient search

        long maxProduct = -1;
        int left = 0;
        int right = arr.length -1;

        while(left < right){
            if(arr[left] + arr[right] <=k){
                maxProduct = Math.max(maxProduct, (long)arr[left] * arr[right]);
                left++; //Try a larger number on the left
            } else {
                right--; //Try a smaller number on the right
            }
        }


        return maxProduct;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 5, 3, 7, 2};
        int k1 = 10;
        System.out.println("Maximal pairwise product for arr1 with k = " + k1 + ": " + maximalPairwiseProduct(arr1, k1)); // Output: 35 (7*5)


        int[] arr2 = {10, 20, 30, 40, 50};
        int k2 = 25;
        System.out.println("Maximal pairwise product for arr2 with k = " + k2 + ": " + maximalPairwiseProduct(arr2, k2)); // Output: -1


        int[] arr3 = {-1,5,3,-7,2};
        int k3 = 4;
        System.out.println("Maximal pairwise product for arr3 with k = " + k3 + ": " + maximalPairwiseProduct(arr3,k3)); //Output: -3 (-1 * 3)

        int[] arr4 = {5, 5, 5, 5, 5};
        int k4 = 10;
        System.out.println("Maximal pairwise product for arr4 with k = " + k4 + ": " + maximalPairwiseProduct(arr4,k4)); //Output: 25

    }
}