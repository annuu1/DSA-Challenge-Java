import java.util.Arrays;

/**
 * Problem Name: OptimalCircularTour
 *
 * Problem Description: Given a circular route with gas stations at each stop,
 * where each station has a certain amount of gas and a certain distance to the next station.
 * Determine if there exists a starting point such that a vehicle can complete the entire circular route
 * without running out of gas.  The vehicle starts with 0 gas.
 */
public class OptimalCircularTour {

    public static int canCompleteTour(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 0) return -1; // Handle empty input

        // Check for insufficient total gas
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalGas < totalCost) return -1; // Impossible to complete the tour

        int currentGas = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            currentGas += gas[i] - cost[i];
            if (currentGas < 0) {
                start = i + 1;
                currentGas = 0;
            }
        }
        return start;
    }


    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int startingPoint = canCompleteTour(gas, cost);

        if (startingPoint != -1) {
            System.out.println("Circular tour is possible starting at index: " + startingPoint);
        } else {
            System.out.println("Circular tour is not possible.");
        }


        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        startingPoint = canCompleteTour(gas2, cost2);

        if (startingPoint != -1) {
            System.out.println("Circular tour is possible starting at index: " + startingPoint);
        } else {
            System.out.println("Circular tour is not possible.");
        }

        int[] gas3 = {};
        int[] cost3 = {};
        startingPoint = canCompleteTour(gas3, cost3);

        if (startingPoint != -1) {
            System.out.println("Circular tour is possible starting at index: " + startingPoint);
        } else {
            System.out.println("Circular tour is not possible.");
        }
    }
}