import java.util.Arrays;

/**
 * Problem Name: OptimalPowerLinePlacement
 *
 * Problem Description: Given a set of cities represented by their x and y coordinates, find the minimum total length of power lines needed to connect all cities.  Power lines can only be placed horizontally or vertically.  The solution must utilize a minimum spanning tree approach.  This problem differs from standard MST because of the constraint on only horizontal/vertical lines.
 */
public class OptimalPowerLinePlacement {

    static class City {
        int x, y;

        City(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static int minPowerLines(City[] cities) {
        int n = cities.length;
        if (n <= 1) return 0;

        // Create edges (only horizontal and vertical)
        java.util.List<Edge> edges = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int weight = Math.abs(cities[i].x - cities[j].x) + Math.abs(cities[i].y - cities[j].y);
                edges.add(new Edge(i, j, Math.abs(cities[i].x - cities[j].x) + Math.abs(cities[i].y - cities[j].y)));

            }
        }
        edges.sort(Edge::compareTo);


        // Kruskal's Algorithm
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int totalWeight = 0;
        int edgesCount = 0;

        for (Edge edge : edges) {
            int rootU = find(parent, edge.u);
            int rootV = find(parent, edge.v);

            if (rootU != rootV) {
                union(parent, rootU, rootV);
                totalWeight += edge.weight;
                edgesCount++;
                if (edgesCount == n - 1) break; // MST has n-1 edges
            }
        }

        return totalWeight;
    }


    private static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }

    private static void union(int[] parent, int i, int j) {
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        parent[rootI] = rootJ;
    }


    public static void main(String[] args) {
        City[] cities = {new City(0, 0), new City(1, 1), new City(2, 0), new City(1, 2)};
        int minLength = minPowerLines(cities);
        System.out.println("Minimum length of power lines: " + minLength); //Expected output: 4

        City[] cities2 = {new City(0, 0), new City(0, 1), new City(1, 0), new City(1,1)};
        minLength = minPowerLines(cities2);
        System.out.println("Minimum length of power lines: " + minLength); //Expected output: 2
    }
}