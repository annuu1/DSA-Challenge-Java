import java.util.Arrays;

/**
 * Problem Name: OptimalResourceAllocationWithVaryingCapacities
 *
 * Problem Description:
 * You have N resources with varying capacities and M tasks. Each task requires a specific amount of resources to complete.  You need to assign tasks to resources such that all tasks are completed, and the total resource utilization is minimized.  A resource can only handle one task at a time, and a task can only be assigned to one resource.  If a task's resource requirement exceeds the capacity of a resource, it cannot be assigned to that resource.
 *
 * Input:
 *  - An integer array `resourceCapacities` representing the capacities of N resources.
 *  - An integer array `taskRequirements` representing the resource requirements of M tasks.
 *
 * Output:
 *  - An integer representing the minimum total resource utilization (sum of resources used for all tasks).  Return -1 if no valid assignment is possible.
 */
public class OptimalResourceAllocationWithVaryingCapacities {

    public static int minTotalResourceUtilization(int[] resourceCapacities, int[] taskRequirements) {
        Arrays.sort(resourceCapacities);
        Arrays.sort(taskRequirements);

        int n = resourceCapacities.length;
        int m = taskRequirements.length;

        if (n < m) return -1; // Not enough resources

        int totalUtilization = 0;
        int taskIndex = m - 1;
        int resourceIndex = n - 1;

        while (taskIndex >= 0) {
            if (resourceCapacities[resourceIndex] >= taskRequirements[taskIndex]) {
                totalUtilization += taskRequirements[taskIndex];
                resourceIndex--;
                taskIndex--;
            } else {
                //Try next resource, if any
                resourceIndex--;
                if(resourceIndex < 0) return -1; // No suitable resource found for this task.
            }
        }

        return totalUtilization;
    }

    public static void main(String[] args) {
        int[] resourceCapacities1 = {5, 10, 15, 20};
        int[] taskRequirements1 = {2, 8, 12};
        System.out.println("Minimum total utilization: " + minTotalResourceUtilization(resourceCapacities1, taskRequirements1)); //Output: 22

        int[] resourceCapacities2 = {5, 10, 15};
        int[] taskRequirements2 = {2, 8, 12, 18};
        System.out.println("Minimum total utilization: " + minTotalResourceUtilization(resourceCapacities2, taskRequirements2)); //Output: -1

        int[] resourceCapacities3 = {10, 20, 30, 40, 50};
        int[] taskRequirements3 = {5, 15, 25, 35, 45};
        System.out.println("Minimum total utilization: " + minTotalResourceUtilization(resourceCapacities3, taskRequirements3)); //Output: 120

        int[] resourceCapacities4 = {10};
        int[] taskRequirements4 = {1,2,3};
        System.out.println("Minimum total utilization: " + minTotalResourceUtilization(resourceCapacities4, taskRequirements4)); //Output: -1
    }
}