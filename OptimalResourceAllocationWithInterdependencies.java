import java.util.Arrays;

/**
 * Problem Name: OptimalResourceAllocationWithInterdependencies
 *
 * Problem Description:  We have 'n' resources with individual costs and interdependencies.  An interdependency is represented by a pair (i, j) meaning resource 'j' cannot be allocated unless resource 'i' is already allocated.  Find the minimum cost to allocate a subset of these resources such that all interdependencies are satisfied and at least 'k' resources are allocated.
 */
public class OptimalResourceAllocationWithInterdependencies {

    public static int minCost(int[] costs, int[][] dependencies, int k) {
        int n = costs.length;
        if (k > n || k <= 0) return -1; // Invalid input

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subsets
            int currentCost = 0;
            int count = 0;
            boolean valid = true;
            boolean[] allocated = new boolean[n];

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) { // Check if j-th resource is allocated
                    allocated[j] = true;
                    currentCost += costs[j];
                    count++;
                }
            }

            if (count < k) continue; // Not enough resources allocated


            for (int[] dependency : dependencies) {
                int resource1 = dependency[0];
                int resource2 = dependency[1];
                if (allocated[resource2] && !allocated[resource1]) {
                    valid = false;
                    break; // Dependency not satisfied
                }
            }

            if (valid) {
                minCost = Math.min(minCost, currentCost);
            }
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost; //Return -1 if no valid subset found
    }

    public static void main(String[] args) {
        int[] costs = {10, 5, 8, 12, 7};
        int[][] dependencies = {{0, 1}, {2, 3}, {1, 4}}; //Resource 1 needs 0, 3 needs 2, 4 needs 1
        int k = 3;

        int minCost = minCost(costs, dependencies, k);

        if (minCost == -1) {
            System.out.println("No valid subset found that satisfies constraints.");
        } else {
            System.out.println("Minimum cost to allocate at least " + k + " resources: " + minCost);
        }


        //Test case 2: No valid solution
        int[] costs2 = {10, 5, 8, 12, 7};
        int[][] dependencies2 = {{0,1},{1,2},{2,0}}; //circular dependency
        int k2 = 3;
        int minCost2 = minCost(costs2, dependencies2, k2);
        if (minCost2 == -1) {
            System.out.println("No valid subset found that satisfies constraints for test case 2.");
        } else {
            System.out.println("Minimum cost to allocate at least " + k2 + " resources: " + minCost2);
        }

    }
}