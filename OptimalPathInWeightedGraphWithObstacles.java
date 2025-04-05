import java.util.Arrays;

/**
 * Problem Name: OptimalPathInWeightedGraphWithObstacles
 *
 * Problem Description:
 * Given a weighted, undirected graph represented as an adjacency matrix,
 * find the shortest path from a source node (0) to a destination node (n-1).
 * The graph contains obstacles represented by -1 in the adjacency matrix.  
 * You cannot traverse through cells with -1.  Find the shortest path length, 
 * or return -1 if no path exists.
 */
public class OptimalPathInWeightedGraphWithObstacles {

    public static int findShortestPath(int[][] graph) {
        int n = graph.length;
        if (n == 0) return -1;

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean[] visited = new boolean[n];

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            if (u == -1) break; // No reachable node found

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != -1 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph1 = {
                {0, 4, -1, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {-1, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int shortestPath = findShortestPath(graph1);
        System.out.println("Shortest path length: " + shortestPath); // Expected Output: 21


        int[][] graph2 = {
                {0, 1, -1},
                {1, 0, 2},
                {-1, 2, 0}
        };
        shortestPath = findShortestPath(graph2);
        System.out.println("Shortest path length: " + shortestPath); // Expected Output: -1


        int[][] graph3 = {{0,1},{1,0}};
        shortestPath = findShortestPath(graph3);
        System.out.println("Shortest path length: " + shortestPath); // Expected Output: 1
    }
}