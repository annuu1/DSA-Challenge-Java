import java.util.Arrays;

/**
 * Problem Name:  OptimalResourceAllocationWithVaryingCosts
 *
 * Problem Description: You have 'n' resources and 'm' tasks. Each task requires a specific amount of each resource.
 *  Each resource has a varying cost per unit depending on the quantity used. The cost is given as an array.  For example,
 *  the first unit of resource 1 might cost 10, the second 12, the third 15, and so on. Find the minimum total cost to complete all tasks.
 *  If a task cannot be completed (due to insufficient resources), return -1.
 */
public class OptimalResourceAllocationWithVaryingCosts {

    public static long minCostToCompleteTasks(int[][] resourceRequirements, int[] resourcesAvailable, int[][] resourceCosts) {
        int n = resourceRequirements.length; // Number of tasks
        int m = resourcesAvailable.length; // Number of resources

        // Check for invalid inputs
        if (resourceRequirements == null || resourceCosts == null || resourcesAvailable == null ||
                resourceRequirements.length == 0 || resourceCosts.length != m || resourcesAvailable.length != m) {
            return -1;
        }

        long minCost = Long.MAX_VALUE;

        // Iterate through all possible resource allocations (this is a brute-force approach, can be optimized with DP)
        for (int i = 0; i < (int) Math.pow(2, n * m); i++) {
            long currentCost = 0;
            int[] resourcesUsed = new int[m];
            boolean possible = true;

            int temp = i;
            for (int taskIndex = 0; taskIndex < n; taskIndex++) {
                for (int resourceIndex = 0; resourceIndex < m; resourceIndex++) {
                    int required = resourceRequirements[taskIndex][resourceIndex];
                    int allocated = (temp % 2 == 1) ? required : 0;
                    temp /= 2;

                    if (resourcesAvailable[resourceIndex] < resourcesUsed[resourceIndex] + allocated) {
                        possible = false;
                        break;
                    }
                    resourcesUsed[resourceIndex] += allocated;
                    if (allocated > 0) {
                        for(int k=0; k < allocated; k++){
                            currentCost += resourceCosts[resourceIndex][resourcesUsed[resourceIndex]-1-k];
                        }
                    }
                }
                if (!possible) break;
            }
            if (possible) minCost = Math.min(minCost, currentCost);
        }


        return minCost == Long.MAX_VALUE ? -1 : minCost;
    }


    public static void main(String[] args) {
        int[][] resourceRequirements = {{1, 2}, {2, 1}, {3,0}};
        int[] resourcesAvailable = {5, 4};
        int[][] resourceCosts = {{10, 12, 15}, {8, 10, 13}}; //Cost per unit for each resource

        long minCost = minCostToCompleteTasks(resourceRequirements, resourcesAvailable, resourceCosts);
        System.out.println("Minimum cost to complete tasks: " + minCost); //Expected output should be a relatively small number


        int[][] resourceRequirements2 = {{1,1},{2,2},{1,2}};
        int[] resourcesAvailable2 = {2,2};
        int[][] resourceCosts2 = {{1,2},{1,2}};
        long minCost2 = minCostToCompleteTasks(resourceRequirements2, resourcesAvailable2, resourceCosts2);
        System.out.println("Minimum cost to complete tasks: " + minCost2); //Should be -1 because of insufficient resources


    }
}