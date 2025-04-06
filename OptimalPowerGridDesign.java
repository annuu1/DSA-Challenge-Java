import java.util.Arrays;

/**
 * Problem Name: OptimalPowerGridDesign
 *
 * Problem Description:
 * You are designing a power grid for a city. The city is represented as a graph where nodes are houses and edges represent connections between houses.  Each connection has a cost associated with it. You need to find the minimum cost to connect all houses such that there are no cycles (a minimum spanning tree). However, there's a constraint:  each house has a maximum power capacity.  You can only connect a house to another house if the sum of their power capacities is greater than or equal to a certain threshold.  Find the minimum cost to connect all houses under this constraint.
 *
 * Input:
 *  - numHouses: The number of houses.
 *  - capacities: An array of integers representing the power capacity of each house.
 *  - edges: A 2D array representing the edges. Each inner array contains [house1, house2, cost].
 *  - threshold: The minimum sum of capacities required to connect two houses.
 *
 * Output:
 *  - The minimum cost to connect all houses, or -1 if it's not possible.
 */
public class OptimalPowerGridDesign {

    public static int minCostToConnect(int numHouses, int[] capacities, int[][] edges, int threshold) {
        // Sort edges by cost
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        // Use Kruskal's algorithm (Union-Find)
        UnionFind uf = new UnionFind(numHouses);
        int minCost = 0;
        int edgesCount = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // Check capacity constraint
            if (capacities[u] + capacities[v] >= threshold) {
                if (uf.union(u, v)) {
                    minCost += cost;
                    edgesCount++;
                    if (edgesCount == numHouses - 1) {
                        break; // All houses connected
                    }
                }
            }
        }

        return edgesCount == numHouses - 1 ? minCost : -1; // -1 if not all houses connected
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); //Path compression
        }

        boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else if (rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else {
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
                return true;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        int numHouses = 5;
        int[] capacities = {10, 15, 20, 5, 8};
        int[][] edges = {{0, 1, 10}, {0, 2, 15}, {1, 2, 5}, {1, 3, 20}, {2, 4, 12}, {3,4, 8}};
        int threshold = 20;

        int minCost = minCostToConnect(numHouses, capacities, edges, threshold);
        System.out.println("Minimum cost to connect all houses: " + minCost);


        numHouses = 4;
        capacities = {5, 10, 15, 20};
        edges = {{0, 1, 2}, {0, 2, 5}, {1, 2, 10}, {2,3, 1}};
        threshold = 15;
        minCost = minCostToConnect(numHouses, capacities, edges, threshold);
        System.out.println("Minimum cost to connect all houses: " + minCost); // Should be 3

    }
}