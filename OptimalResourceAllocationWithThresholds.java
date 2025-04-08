import java.util.Arrays;

/**
 * Problem Name: OptimalResourceAllocationWithThresholds
 *
 * Problem Description:
 * You are given an array of resources with their respective values and thresholds.  You need to allocate these resources
 * to projects such that the total value of allocated resources is maximized, but no resource's value exceeds its
 * given threshold.  A resource can only be allocated to one project.
 *
 */
public class OptimalResourceAllocationWithThresholds {

    public static int maxResourceValue(int[] values, int[] thresholds) {
        if (values.length != thresholds.length || values.length == 0) {
            return 0; // Handle invalid input
        }

        int n = values.length;
        int[][] dp = new int[n + 1][1 << n]; // DP table: dp[i][mask] represents max value using resources up to i with allocation mask 'mask'

        for (int i = 1; i <= n; i++) {
            for (int mask = 0; mask < (1 << n); mask++) {
                dp[i][mask] = dp[i - 1][mask]; // Exclude current resource

                if ((mask & (1 << (i - 1))) == 0) { // If resource i is not already allocated
                    int currentValue = values[i - 1];
                    int newMask = mask | (1 << (i - 1));
                    boolean feasible = true;


                    //Check if adding this resource exceeds the threshold.  We need to check all previously allocated resources as well.
                    for(int j=0; j<n; ++j){
                        if((newMask & (1 << j)) != 0){
                            int threshold = thresholds[j];
                            int allocatedValue = 0;
                            for(int k=0; k < n; ++k){
                                if((newMask & (1 << k)) != 0){
                                    allocatedValue += values[k];
                                }
                            }
                            if(allocatedValue > threshold){
                                feasible = false;
                                break;
                            }
                        }
                    }

                    if (feasible) {
                        dp[i][newMask] = Math.max(dp[i][newMask], dp[i - 1][mask] + currentValue);
                    }
                }
            }
        }

        int maxValue = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            maxValue = Math.max(maxValue, dp[n][mask]);
        }

        return maxValue;
    }


    public static void main(String[] args) {
        int[] values = {10, 5, 15, 20};
        int[] thresholds = {25, 10, 30, 25};
        int maxValue = maxResourceValue(values, thresholds);
        System.out.println("Maximum resource value: " + maxValue); // Output should be 35 (10 + 25)

        int[] values2 = {5,10,15};
        int[] thresholds2 = {15,20,25};
        maxValue = maxResourceValue(values2, thresholds2);
        System.out.println("Maximum resource value: " + maxValue); // Output should be 30 (15 + 15)

        int[] values3 = {10,20,30};
        int[] thresholds3 = {5,10,15};
        maxValue = maxResourceValue(values3,thresholds3);
        System.out.println("Maximum resource value: " + maxValue); //Output should be 0


    }
}