import java.util.Arrays;

/**
 * Problem Name: OptimalBuildingComplexConstruction
 *
 * Problem Description:
 * You are constructing a building complex consisting of several buildings. Each building requires a specific amount of resources to construct, and there are dependencies between the buildings.  Building 'i' can only be started after all its dependent buildings are completed.  Given the resource requirements and dependencies, find the minimum total resources needed to construct the entire complex, while respecting the dependencies.
 */
public class OptimalBuildingComplexConstruction {

    public static int minResources(int[] resources, int[][] dependencies) {
        // Create an adjacency list to represent dependencies.
        java.util.List<java.util.List<Integer>> adjList = new java.util.ArrayList<>();
        for (int i = 0; i < resources.length; i++) {
            adjList.add(new java.util.ArrayList<>());
        }
        for (int[] dependency : dependencies) {
            adjList.get(dependency[1]).add(dependency[0]); //dependency[1] depends on dependency[0]
        }

        int[] inDegree = new int[resources.length];
        for (int[] dependency : dependencies) {
            inDegree[dependency[0]]++;
        }

        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>(java.util.Comparator.comparingInt(i -> resources[i]));
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        long totalResources = 0;
        while (!pq.isEmpty()) {
            int building = pq.poll();
            totalResources += resources[building];

            for (int neighbor : adjList.get(building)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    pq.offer(neighbor);
                }
            }
        }

        //Check for cycles (if totalResources is less than sum of all resources, there's a cycle)
        long sumOfResources = Arrays.stream(resources).sum();
        if(totalResources != sumOfResources){
            throw new IllegalStateException("Cycle detected in building dependencies.  Construction impossible.");
        }

        return (int) totalResources;
    }

    public static void main(String[] args) {
        int[] resources = {10, 5, 15, 20, 8}; // Resource requirements for each building.
        int[][] dependencies = {{0, 2}, {1, 2}, {2, 3}, {1, 4}}; // Building i depends on building j if (j,i) is in dependencies.

        int minResourceNeeded = minResources(resources, dependencies);
        System.out.println("Minimum total resources needed: " + minResourceNeeded);


        int[] resources2 = {10, 5, 15};
        int[][] dependencies2 = {{0, 1}, {1, 2}, {2,0}}; // Example with a cycle
        try{
            int minResourceNeeded2 = minResources(resources2, dependencies2);
            System.out.println("Minimum total resources needed: " + minResourceNeeded2);
        } catch (IllegalStateException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}