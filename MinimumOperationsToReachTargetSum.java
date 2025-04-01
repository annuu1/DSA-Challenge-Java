import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  MinimumOperationsToReachTargetSum
 *
 * Problem Description: Given an array of positive integers `nums` and a target sum `target`,
 * find the minimum number of operations required to reach the target sum. In each operation,
 * you can either add or subtract any element from `nums`.  Return -1 if the target is unreachable.
 */
public class MinimumOperationsToReachTargetSum {

    public static int minOperations(int[] nums, int target) {
        // Create a map to store the reachable sums and the minimum operations to reach them.
        Map<Integer, Integer> reachableSums = new HashMap<>();
        reachableSums.put(0, 0); // Start with sum 0 and 0 operations.

        for (int num : nums) {
            Map<Integer, Integer> nextReachableSums = new HashMap<>(reachableSums); // Create a copy to avoid concurrent modification.

            for (Map.Entry<Integer, Integer> entry : reachableSums.entrySet()) {
                int currentSum = entry.getKey();
                int currentOps = entry.getValue();

                // Try adding the current number.
                int nextSumAdd = currentSum + num;
                if (!nextReachableSums.containsKey(nextSumAdd) || nextReachableSums.get(nextSumAdd) > currentOps + 1) {
                    nextReachableSums.put(nextSumAdd, currentOps + 1);
                }

                // Try subtracting the current number.
                int nextSumSub = currentSum - num;
                if (!nextReachableSums.containsKey(nextSumSub) || nextReachableSums.get(nextSumSub) > currentOps + 1) {
                    nextReachableSums.put(nextSumSub, currentOps + 1);
                }
            }
            reachableSums = nextReachableSums;
        }

        if (reachableSums.containsKey(target)) {
            return reachableSums.get(target);
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 5, 3};
        int target1 = 7;
        System.out.println("Minimum operations for target " + target1 + ": " + minOperations(nums1, target1)); // Expected: 2

        int[] nums2 = {2, 4, 6};
        int target2 = 10;
        System.out.println("Minimum operations for target " + target2 + ": " + minOperations(nums2, target2)); // Expected: 1

        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 100;
        System.out.println("Minimum operations for target " + target3 + ": " + minOperations(nums3, target3)); // Expected: -1 or a higher number depending on the algorithm's efficiency.

        int[] nums4 = {5, 10, 15};
        int target4 = 0;
        System.out.println("Minimum operations for target " + target4 + ": " + minOperations(nums4, target4)); //Expected: 2

        int[] nums5 = {};
        int target5 = 5;
        System.out.println("Minimum operations for target " + target5 + ": " + minOperations(nums5, target5)); //Expected: -1
    }
}