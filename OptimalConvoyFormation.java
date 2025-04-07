import java.util.Arrays;

/**
 * Problem Name: OptimalConvoyFormation
 *
 * Problem Description:
 * You are given a list of vehicles with their speeds.  You need to form a convoy such that the
 * difference between the fastest and slowest vehicle's speed is minimized.  The convoy must
 * have at least k vehicles.  Find the minimum speed difference for a convoy of at least k vehicles.
 *
 * Input: An array of integers representing vehicle speeds, and an integer k.
 * Output: The minimum speed difference for a convoy of at least k vehicles.  Return -1 if no such convoy is possible.
 */
public class OptimalConvoyFormation {

    public static int minSpeedDifference(int[] speeds, int k) {
        if (speeds == null || speeds.length < k) {
            return -1; // Not enough vehicles for a convoy
        }

        Arrays.sort(speeds);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i <= speeds.length - k; i++) {
            int diff = speeds[i + k - 1] - speeds[i];
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }


    public static void main(String[] args) {
        int[] speeds1 = {10, 20, 30, 40, 50};
        int k1 = 3;
        System.out.println("Minimum speed difference for convoy of size >= " + k1 + ": " + minSpeedDifference(speeds1, k1)); // Output: 20

        int[] speeds2 = {5, 15, 25, 35, 45};
        int k2 = 2;
        System.out.println("Minimum speed difference for convoy of size >= " + k2 + ": " + minSpeedDifference(speeds2, k2)); // Output: 10

        int[] speeds3 = {10, 20, 30};
        int k3 = 4;
        System.out.println("Minimum speed difference for convoy of size >= " + k3 + ": " + minSpeedDifference(speeds3, k3)); // Output: -1

        int[] speeds4 = {10};
        int k4 = 1;
        System.out.println("Minimum speed difference for convoy of size >= " + k4 + ": " + minSpeedDifference(speeds4, k4)); //Output: 0

        int [] speeds5 = {};
        int k5 = 1;
        System.out.println("Minimum speed difference for convoy of size >= " + k5 + ": " + minSpeedDifference(speeds5, k5)); //Output: -1

    }
}