import java.util.Arrays;

/**
 * Problem Name: OptimalFuelDistribution
 *
 * Problem Description:
 * You are given an array `distances` representing distances between consecutive fuel stations along a route.
 * You have a truck with initial fuel capacity `initialFuel`.  The truck consumes 1 unit of fuel per unit distance traveled.
 * At each fuel station, you can refill the truck to its maximum capacity.  However, refueling costs `costPerUnit` for each unit of fuel added.
 * Find the minimum total cost to complete the entire route.  If it's impossible to complete the route, return -1.
 *
 * Example:
 * distances = [10, 5, 15, 20]
 * initialFuel = 12
 * costPerUnit = 2
 *
 * Explanation:
 * - Start with 12 fuel. Travel 10 units (remaining fuel = 2).
 * - Refill to 12 (cost = 2 * 10 = 20). Travel 5 units (remaining fuel = 7).
 * - Refill to 12 (cost = 2 * 5 = 10). Travel 15 units (remaining fuel = -3).  This is impossible.
 *
 * distances = [10, 5, 15, 20]
 * initialFuel = 20
 * costPerUnit = 2
 *
 * Explanation:
 * - Start with 20 fuel. Travel 10 units (remaining fuel = 10).
 * - Refill to 20 (cost = 2 * 10 = 20). Travel 5 units (remaining fuel = 15).
 * - Refill to 20 (cost = 2 * 5 = 10). Travel 15 units (remaining fuel = 5).
 * - Refill to 20 (cost = 2 * 15 = 30). Travel 20 units (remaining fuel = 5).
 * Total cost = 20 + 10 + 30 = 60
 */
public class OptimalFuelDistribution {

    public static long minCost(int[] distances, int initialFuel, int costPerUnit) {
        long totalCost = 0;
        long currentFuel = initialFuel;

        for (int i = 0; i < distances.length; i++) {
            if (currentFuel >= distances[i]) {
                currentFuel -= distances[i];
            } else {
                long neededFuel = distances[i] - currentFuel;
                totalCost += neededFuel * costPerUnit;
                currentFuel += neededFuel;
                currentFuel -= distances[i];
            }
            if (currentFuel < 0) return -1; // Impossible to continue
        }
        return totalCost;
    }


    public static void main(String[] args) {
        int[] distances1 = {10, 5, 15, 20};
        int initialFuel1 = 12;
        int costPerUnit1 = 2;
        System.out.println("Min cost for scenario 1: " + minCost(distances1, initialFuel1, costPerUnit1)); // Output: -1

        int[] distances2 = {10, 5, 15, 20};
        int initialFuel2 = 20;
        int costPerUnit2 = 2;
        System.out.println("Min cost for scenario 2: " + minCost(distances2, initialFuel2, costPerUnit2)); // Output: 60

        int[] distances3 = {5, 10, 15};
        int initialFuel3 = 25;
        int costPerUnit3 = 3;
        System.out.println("Min cost for scenario 3: " + minCost(distances3, initialFuel3, costPerUnit3)); // Output: 0


    }
}