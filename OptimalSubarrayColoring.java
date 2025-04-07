import java.util.Arrays;

/**
 * Problem Name: OptimalSubarrayColoring
 *
 * Problem Description:
 * Given an array of integers representing colors (e.g., 1, 2, 3 represent red, green, blue), find the maximum length of a subarray where no two adjacent elements have the same color.  If no such subarray exists, return 0.
 */
public class OptimalSubarrayColoring {

    public static int maxSubarrayLengthWithNoAdjacentSameColor(int[] colors) {
        if (colors == null || colors.length == 0) {
            return 0;
        }

        int maxLength = 0;
        for (int i = 0; i < colors.length; i++) {
            int currentLength = 1;
            int currentColor = colors[i];
            for (int j = i + 1; j < colors.length; j++) {
                if (colors[j] != currentColor) {
                    currentColor = colors[j];
                    currentLength++;
                } else {
                    break; //Adjacent same color found, break inner loop
                }
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }


    public static void main(String[] args) {
        int[] colors1 = {1, 2, 1, 3, 2, 1};
        int[] colors2 = {1, 1, 1, 1};
        int[] colors3 = {1, 2, 3, 4, 5};
        int[] colors4 = {};
        int[] colors5 = {2,2,3,3,3,1,1};


        System.out.println("Max length for colors1: " + maxSubarrayLengthWithNoAdjacentSameColor(colors1)); // Output: 3
        System.out.println("Max length for colors2: " + maxSubarrayLengthWithNoAdjacentSameColor(colors2)); // Output: 1
        System.out.println("Max length for colors3: " + maxSubarrayLengthWithNoAdjacentSameColor(colors3)); // Output: 5
        System.out.println("Max length for colors4: " + maxSubarrayLengthWithNoAdjacentSameColor(colors4)); // Output: 0
        System.out.println("Max length for colors5: " + maxSubarrayLengthWithNoAdjacentSameColor(colors5)); // Output: 2

    }
}