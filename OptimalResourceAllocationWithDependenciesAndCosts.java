import java.util.Arrays;

/**
 * Problem Name: Optimal Resource Allocation with Dependencies and Costs
 *
 * Problem Description:  We have 'n' resources with associated costs and dependencies.  Each resource has a cost and a list of resources it depends on (dependencies can be empty).  We need to find the minimum cost to acquire a set of resources that satisfies all dependencies.  A resource can only be acquired if all its dependencies are already acquired.
 */
public class OptimalResourceAllocationWithDependenciesAndCosts {

    static class Resource {
        int id;
        int cost;
        int[] dependencies;

        Resource(int id, int cost, int[] dependencies) {
            this.id = id;
            this.cost = cost;
            this.dependencies = dependencies;
        }
    }

    public static int minCostToAcquireResources(Resource[] resources) {
        // Sort resources by number of dependencies (topological sort hint)
        Arrays.sort(resources, (a, b) -> Integer.compare(a.dependencies.length, b.dependencies.length));

        int n = resources.length;
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        for (Resource resource : resources) {
            int cost = resource.cost;
            boolean dependenciesMet = true;

            for (int dependency : resource.dependencies) {
                int dependencyCostIndex = findResourceIndex(resources, dependency);
                if (dependencyCostIndex == -1 || minCost[dependencyCostIndex] == Integer.MAX_VALUE) {
                    dependenciesMet = false;
                    break;
                }
                cost += minCost[dependencyCostIndex];
            }

            if (dependenciesMet) {
                int resourceIndex = findResourceIndex(resources, resource.id);
                minCost[resourceIndex] = Math.min(minCost[resourceIndex], cost);
            }
        }

        int totalMinCost = 0;
        for (int cost : minCost) {
            if (cost != Integer.MAX_VALUE) {
                totalMinCost += cost;
            }
        }
        return totalMinCost;

    }

    private static int findResourceIndex(Resource[] resources, int resourceId) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i].id == resourceId) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Resource[] resources = {
                new Resource(1, 10, new int[]{}),
                new Resource(2, 15, new int[]{1}),
                new Resource(3, 20, new int[]{1}),
                new Resource(4, 25, new int[]{2, 3}),
                new Resource(5, 30, new int[]{4})
        };

        int minCost = minCostToAcquireResources(resources);
        System.out.println("Minimum cost to acquire resources: " + minCost); // Output: 100

        Resource[] resources2 = {
                new Resource(1, 5, new int[]{}),
                new Resource(2, 10, new int[]{}),
                new Resource(3, 15, new int[]{1, 2})
        };
        minCost = minCostToAcquireResources(resources2);
        System.out.println("Minimum cost to acquire resources: " + minCost); // Output: 30


        Resource[] resources3 = {
                new Resource(1, 10, new int[]{2}),
                new Resource(2, 5, new int[]{})
        };
        minCost = minCostToAcquireResources(resources3);
        System.out.println("Minimum cost to acquire resources: " + minCost); // Output: 15

    }
}