import java.util.Arrays;

public class  OptimalTrainFormation {

    /**
     * Problem: Optimal Train Formation
     *
     * You are given an array of train cars, each represented by its weight.  You need to form a train by connecting these cars.
     * However, there's a constraint: adjacent cars cannot have weights that differ by more than a specified threshold.
     * Find the maximum length train you can form, adhering to the weight difference constraint.
     *
     * Example:
     * weights = [10, 12, 15, 14, 16, 20, 18] , threshold = 2
     * Output: 4 (e.g., [10, 12, 14, 16] or [14, 15, 16, 18] )
     *
     * weights = [5, 10, 15, 20, 25], threshold = 1
     * Output: 1
     */

    public static int maxLengthTrain(int[] weights, int threshold) {
        if (weights == null || weights.length == 0) return 0;
        Arrays.sort(weights); //Sorting simplifies finding valid sequences
        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < weights.length; i++) {
            if (weights[i] - weights[i - 1] <= threshold) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }
        maxLength = Math.max(maxLength, currentLength); //handle the last sequence
        return maxLength;
    }


    public static void main(String[] args) {
        int[] weights1 = {10, 12, 15, 14, 16, 20, 18};
        int threshold1 = 2;
        System.out.println("Max Train Length (Example 1): " + maxLengthTrain(weights1, threshold1)); // Output: 4


        int[] weights2 = {5, 10, 15, 20, 25};
        int threshold2 = 1;
        System.out.println("Max Train Length (Example 2): " + maxLengthTrain(weights2, threshold2)); // Output: 1

        int[] weights3 = {1,3,5,7,9,11};
        int threshold3 = 10;
        System.out.println("Max Train Length (Example 3): " + maxLengthTrain(weights3, threshold3)); // Output: 6

        int[] weights4 = {};
        int threshold4 = 2;
        System.out.println("Max Train Length (Example 4): " + maxLengthTrain(weights4, threshold4)); // Output: 0

        int[] weights5 = {10};
        int threshold5 = 2;
        System.out.println("Max Train Length (Example 5): " + maxLengthTrain(weights5, threshold5)); //Output: 1


    }
}