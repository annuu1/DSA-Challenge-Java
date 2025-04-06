import java.util.Arrays;

/**
 * Problem Name: OptimalBuildingConstructionSequencing
 *
 * Problem Description:
 * You are given a list of buildings to construct. Each building has a construction time and a dependency list (a list of building IDs that must be constructed before it).  Find the minimum total construction time to construct all buildings, respecting the dependencies.  Return -1 if a valid construction sequence is impossible.
 */
public class OptimalBuildingConstructionSequencing {

    static class Building {
        int id;
        int constructionTime;
        int[] dependencies;

        Building(int id, int constructionTime, int[] dependencies) {
            this.id = id;
            this.constructionTime = constructionTime;
            this.dependencies = dependencies;
        }
    }

    public static int minConstructionTime(Building[] buildings) {
        int n = buildings.length;
        int[] inDegree = new int[n];
        Arrays.fill(inDegree, 0);

        //Calculate in-degree for topological sort
        for (Building building : buildings) {
            for (int dependency : building.dependencies) {
                inDegree[building.id]++;
            }
        }


        //Topological sort using Kahn's Algorithm
        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue[tail++] = i;
            }
        }

        long totalTime = 0;
        int count = 0;
        while (head < tail) {
            int buildingId = queue[head++];
            totalTime += buildings[buildingId].constructionTime;
            count++;

            for (Building building : buildings) {
                boolean found = false;
                for (int dep : building.dependencies) {
                    if (dep == buildingId) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    inDegree[building.id]--;
                    if (inDegree[building.id] == 0) {
                        queue[tail++] = building.id;
                    }
                }
            }
        }

        if (count != n) return -1; //Cycle detected or invalid dependencies

        return (int) totalTime;
    }

    public static void main(String[] args) {
        Building[] buildings = new Building[]{
                new Building(0, 2, new int[]{}),
                new Building(1, 5, new int[]{0}),
                new Building(2, 3, new int[]{0, 1}),
                new Building(3, 1, new int[]{2})
        };

        int minTime = minConstructionTime(buildings);
        System.out.println("Minimum construction time: " + minTime); // Output: 11


        Building[] buildings2 = new Building[]{
                new Building(0, 2, new int[]{1}),
                new Building(1, 5, new int[]{0})
        };
        minTime = minConstructionTime(buildings2);
        System.out.println("Minimum construction time: " + minTime); // Output: -1 (cycle)


        Building[] buildings3 = new Building[]{
                new Building(0, 1, new int[]{}),
                new Building(1, 2, new int[]{}),
                new Building(2, 3, new int[]{})
        };
        minTime = minConstructionTime(buildings3);
        System.out.println("Minimum construction time: " + minTime); //Output 6

    }
}