import java.util.Arrays;

/**
 * Problem Name:  MinCostToConnectAllCitiesWithUniqueEdges
 *
 * Problem Description: Given an undirected graph represented by an adjacency matrix where each element
 * represents the cost of connecting two cities. Find the minimum cost to connect all cities using a
 * minimum spanning tree.  The cost matrix is symmetric (cost[i][j] == cost[j][i]) and the diagonal is 0.
 *  Note that only unique edges are allowed - you cannot connect two cities more than once.
 *
 *  If it's impossible to connect all cities, return -1.
 */
public class MinCostToConnectAllCitiesWithUniqueEdges {

    public static int minCostToConnect(int[][] costMatrix) {
        int numCities = costMatrix.length;
        if (numCities == 0) return 0;

        // Create an array to keep track of connected components (initially all unconnected)
        int[] parent = new int[numCities];
        Arrays.fill(parent, -1);  // -1 indicates the node is the root of its component

        // Edge representation: (cost, u, v)
        Edge[] edges = new Edge[numCities * (numCities - 1) / 2];
        int edgeIndex = 0;
        for (int i = 0; i < numCities; i++) {
            for (int j = i + 1; j < numCities; j++) {
                edges[edgeIndex++] = new Edge(costMatrix[i][j], i, j);
            }
        }

        // Sort edges by cost (ascending)
        Arrays.sort(edges, (a, b) -> a.cost - b.cost);

        int minCost = 0;
        int edgesCount = 0;
        for (Edge edge : edges) {
            int rootU = find(parent, edge.u);
            int rootV = find(parent, edge.v);

            // Union only if they are not already in the same component (avoid cycles)
            if (rootU != rootV) {
                parent[rootU] = rootV; // Union operation
                minCost += edge.cost;
                edgesCount++;
                if (edgesCount == numCities - 1) break; // MST is complete
            }
        }

        // Check if all cities are connected
        if (edgesCount == numCities - 1) {
            return minCost;
        } else {
            return -1; // Cannot connect all cities
        }
    }

    // Find the root of the component using path compression
    private static int find(int[] parent, int i) {
        if (parent[i] == -1) return i;
        return parent[i] = find(parent, parent[i]); // Path compression
    }


    static class Edge {
        int cost;
        int u;
        int v;

        Edge(int cost, int u, int v) {
            this.cost = cost;
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
                {0, 4, 6, 0, 0, 0},
                {4, 0, 6, 3, 0, 0},
                {6, 6, 0, 1, 8, 0},
                {0, 3, 1, 0, 2, 5},
                {0, 0, 8, 2, 0, 7},
                {0, 0, 0, 5, 7, 0}
        };

        int minCost = minCostToConnect(costMatrix);
        System.out.println("Minimum cost to connect all cities: " + minCost); // Output: 14

        int[][] costMatrix2 = {
                {0, 1, 100},
                {1, 0, 100},
                {100, 100, 0}
        };
        minCost = minCostToConnect(costMatrix2);
        System.out.println("Minimum cost to connect all cities: " + minCost); // Output: 2


        int[][] costMatrix3 = {
                {0,100,100},
                {100,0,100},
                {100,100,0}
        };
        minCost = minCostToConnect(costMatrix3);
        System.out.println("Minimum cost to connect all cities: " + minCost); // Output: -1

    }
}