import java.util.Arrays;

/**
 * Problem Name: OptimalCargoUnloadingWithConstraints
 *
 * Problem Description:
 * You are unloading cargo from a ship.  The cargo consists of containers, each with a weight and a fragility level (an integer representing its sensitivity to damage). You have a crane that can lift a maximum weight W.  You must unload all containers. However, there's a constraint:  the total fragility level of containers lifted in a single crane operation cannot exceed F. Design an algorithm to find the minimum number of crane operations required to unload all cargo.
 */
public class OptimalCargoUnloadingWithConstraints {

    public static int minCraneOperations(int[] weights, int[] fragilities, int maxWeight, int maxFragility) {
        int n = weights.length;
        if (n == 0) return 0;

        //Sort containers by weight (descending) - heuristic to improve chance of fitting heavier items first.
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> Integer.compare(weights[i2], weights[i1]));


        int operations = 0;
        boolean[] unloaded = new boolean[n];
        while (true) {
            boolean allUnloaded = true;
            for (boolean b : unloaded) if (!b) allUnloaded = false;
            if (allUnloaded) break;

            operations++;
            int currentWeight = 0;
            int currentFragility = 0;
            for (int i : indices) {
                if (!unloaded[i] && currentWeight + weights[i] <= maxWeight && currentFragility + fragilities[i] <= maxFragility) {
                    currentWeight += weights[i];
                    currentFragility += fragilities[i];
                    unloaded[i] = true;
                }
            }
        }
        return operations;
    }


    public static void main(String[] args) {
        int[] weights = {10, 5, 12, 8, 3, 7, 15};
        int[] fragilities = {2, 5, 1, 3, 4, 2, 6};
        int maxWeight = 20;
        int maxFragility = 8;

        int minOps = minCraneOperations(weights, fragilities, maxWeight, maxFragility);
        System.out.println("Minimum crane operations required: " + minOps);


        //Example with infeasible constraints (illustrative)
        int[] weights2 = {10, 15, 12};
        int[] fragilities2 = {10, 12, 5};
        int maxWeight2 = 10;
        int maxFragility2 = 10;

        int minOps2 = minCraneOperations(weights2, fragilities2, maxWeight2, maxFragility2);
        System.out.println("Minimum crane operations required (infeasible example): " + minOps2);


    }
}