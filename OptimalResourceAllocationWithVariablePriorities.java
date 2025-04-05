import java.util.Arrays;

/**
 * Problem Name:  OptimalResourceAllocationWithVariablePriorities
 *
 * Problem Description:  You have N resources and M projects. Each project requires a certain number of resources of a specific type.  Each resource type has a priority.  You want to allocate resources to projects to maximize the total priority points earned, subject to resource constraints.
 */
public class OptimalResourceAllocationWithVariablePriorities {

    static class Project {
        int id;
        int resourceType;
        int resourcesNeeded;
        int priority;

        public Project(int id, int resourceType, int resourcesNeeded, int priority) {
            this.id = id;
            this.resourceType = resourceType;
            this.resourcesNeeded = resourcesNeeded;
            this.priority = priority;
        }
    }

    public static int maxPriorityPoints(Project[] projects, int[] resourceCounts, int numResourceTypes) {
        Arrays.sort(projects, (a, b) -> b.priority - a.priority); //Sort projects by priority descending

        int totalPriorityPoints = 0;
        int[] resourcesAvailable = Arrays.copyOf(resourceCounts, resourceCounts.length);

        for (Project project : projects) {
            int resourcesAllocated = Math.min(project.resourcesNeeded, resourcesAvailable[project.resourceType]);
            if (resourcesAllocated > 0) {
                totalPriorityPoints += resourcesAllocated * project.priority;
                resourcesAvailable[project.resourceType] -= resourcesAllocated;
            }
        }
        return totalPriorityPoints;
    }


    public static void main(String[] args) {
        // Example usage:
        Project[] projects = {
                new Project(1, 0, 5, 10), //Project 1 needs 5 resources of type 0, priority 10
                new Project(2, 1, 3, 5),  //Project 2 needs 3 resources of type 1, priority 5
                new Project(3, 0, 2, 8),  //Project 3 needs 2 resources of type 0, priority 8
                new Project(4, 1, 4, 7),  //Project 4 needs 4 resources of type 1, priority 7
                new Project(5, 2, 1, 2)   //Project 5 needs 1 resource of type 2, priority 2
        };

        int[] resourceCounts = {10, 7, 3}; //Available resources of type 0, 1, and 2
        int numResourceTypes = resourceCounts.length;

        int maxPoints = maxPriorityPoints(projects, resourceCounts, numResourceTypes);
        System.out.println("Maximum priority points achievable: " + maxPoints);
    }
}