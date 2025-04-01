import java.util.Arrays;

public class OptimalResourceAllocation {

    /**
     * Problem: Optimal Resource Allocation
     *
     * You have a list of resources with their respective weights.  You need to
     * allocate these resources to `k` different projects.  Each project must receive
     * at least one resource, and the goal is to minimize the maximum weight among
     * all projects.  The total weight of resources allocated to each project doesn't
     * matter, only the maximum weight of a single resource in each project.
     *
     * For example:
     * resources = [5, 3, 2, 8, 1, 4, 7]
     * k = 3
     *
     * Optimal allocation could be:
     * Project 1: [8, 1] (max weight 8)
     * Project 2: [7, 2] (max weight 7)
     * Project 3: [5, 4, 3] (max weight 5)
     *
     * The minimum maximum weight among projects is 8 in this case.  But the optimal solution
     * might have a smaller maximum weight for all projects.
     *
     * Input:
     * resources: An array of integers representing the weights of the resources.
     * k: The number of projects.
     *
     * Output:
     * The minimum possible maximum weight among all projects after optimal allocation.
     */


    public static int minMaxWeight(int[] resources, int k) {
        Arrays.sort(resources);
        int n = resources.length;
        if (k > n) return -1; //Not enough resources for k projects

        int left = resources[0]; //Minimum possible max weight
        int right = resources[n - 1]; //Maximum possible max weight

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 1;
            int currentMax = mid;

            for (int i = n - 1; i >= 0; i--) {
                if (resources[i] <= currentMax) {
                    continue;
                } else {
                    count++;
                    currentMax = mid;
                }
            }

            if (count <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] resources1 = {5, 3, 2, 8, 1, 4, 7};
        int k1 = 3;
        System.out.println("Minimum maximum weight for resources1 and k1: " + minMaxWeight(resources1, k1)); // Expected output should be <=8

        int[] resources2 = {10, 20, 30, 40, 50};
        int k2 = 2;
        System.out.println("Minimum maximum weight for resources2 and k2: " + minMaxWeight(resources2, k2)); //Expected output should be <=30

        int[] resources3 = {1,2,3,4,5,6};
        int k3 = 6;
        System.out.println("Minimum maximum weight for resources3 and k3: " + minMaxWeight(resources3, k3)); //Expected output: 1

        int[] resources4 = {1,2,3,4,5};
        int k4 = 10;
        System.out.println("Minimum maximum weight for resources4 and k4: " + minMaxWeight(resources4, k4)); //Expected output: -1
    }
}