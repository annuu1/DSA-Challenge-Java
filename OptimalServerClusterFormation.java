import java.util.Arrays;

/**
 * Problem Name: OptimalServerClusterFormation
 *
 * Problem Description: Given a list of servers with processing speeds, group them into clusters such that the difference between the fastest and slowest server in each cluster is minimized.  The goal is to minimize the maximum difference across all clusters.  Each cluster must have at least one server.
 */
public class OptimalServerClusterFormation {

    public static int minMaxClusterDifference(int[] serverSpeeds, int numClusters) {
        if (serverSpeeds == null || serverSpeeds.length == 0 || numClusters <= 0 || numClusters > serverSpeeds.length) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        Arrays.sort(serverSpeeds);
        int n = serverSpeeds.length;
        int minDiff = Integer.MAX_VALUE;

        // Iterate through all possible cluster formations
        for (int i = 0; i < (1 << (n - 1)); ++i) {
            if (Integer.bitCount(i) != numClusters -1) continue; //Must have numClusters -1 dividers

            int[] clusterSizes = new int[numClusters];
            int clusterIndex = 0;
            int count = 0;
            for(int j=0; j< n-1; ++j){
                if((i >> j) % 2 == 1){
                    clusterIndex++;
                    count = 0;
                }
                clusterSizes[clusterIndex]++;
            }
            clusterSizes[clusterIndex]++; // Last cluster

            boolean validPartition = true;
            for(int size : clusterSizes) if(size == 0) validPartition = false;
            if(!validPartition) continue;


            int maxDiff = 0;
            int start = 0;
            for (int j = 0; j < numClusters; j++) {
                int end = start + clusterSizes[j];
                maxDiff = Math.max(maxDiff, serverSpeeds[end - 1] - serverSpeeds[start]);
                start = end;
            }
            minDiff = Math.min(minDiff, maxDiff);
        }
        return minDiff;
    }


    public static void main(String[] args) {
        int[] serverSpeeds = {1, 2, 3, 4, 5, 6, 7, 8};
        int numClusters = 3;
        int minDiff = minMaxClusterDifference(serverSpeeds, numClusters);
        System.out.println("Minimum maximum difference between fastest and slowest server in clusters: " + minDiff);


        int[] serverSpeeds2 = {10,20,30,40,50};
        int numClusters2 = 2;
        minDiff = minMaxClusterDifference(serverSpeeds2, numClusters2);
        System.out.println("Minimum maximum difference between fastest and slowest server in clusters: " + minDiff);

        int[] serverSpeeds3 = {5,10,15,20,25,30};
        int numClusters3 = 3;
        minDiff = minMaxClusterDifference(serverSpeeds3, numClusters3);
        System.out.println("Minimum maximum difference between fastest and slowest server in clusters: " + minDiff);
    }
}