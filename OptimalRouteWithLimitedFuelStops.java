import java.util.Arrays;

/**
 * Problem Name: OptimalRouteWithLimitedFuelStops
 *
 * Problem Description:
 * You are given a list of cities represented by their distances from the starting point (city 0).
 * You have a car with a limited fuel capacity.  You can refuel at any city.
 *  Find the minimum number of fuel stops required to reach the last city.
 *  Return -1 if it's impossible to reach the last city.
 *
 *  Input:
 *  distances: An array of integers representing the distances of cities from the starting point.
 *  fuelCapacity: The maximum fuel your car can hold.
 *  fuelStops: An array of integers representing the amount of fuel available at each city (excluding city 0).
 *             The index of fuelStops corresponds to the index of distances (excluding city 0).
 *
 */
public class OptimalRouteWithLimitedFuelStops {

    public static int minFuelStops(int[] distances, int fuelCapacity, int[] fuelStops) {
        int n = distances.length;
        if (n <= 1) return 0; // Already at destination or only one city

        long currentFuel = fuelCapacity;
        int stops = 0;
        int i = 0;

        //PriorityQueue to store fuel stops in descending order of fuel available
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        while (i < n -1) {
            if (currentFuel >= distances[i+1] - distances[i]) {
                currentFuel -= (distances[i+1] - distances[i]);
                i++;
                if (i < n -1 && fuelStops.length > i -1) {
                    pq.offer(fuelStops[i-1]);
                }
            } else {
                if (pq.isEmpty()) return -1; //No more fuel available
                currentFuel += pq.poll();
                stops++;
            }

        }

        return stops;
    }


    public static void main(String[] args) {
        int[] distances = {0, 200, 375, 550, 750, 950};
        int fuelCapacity = 400;
        int[] fuelStops = {200, 200, 150, 100, 150};


        int minStops = minFuelStops(distances, fuelCapacity, fuelStops);

        System.out.println("Minimum fuel stops required: " + minStops);


        int[] distances2 = {0, 100, 200, 300};
        int fuelCapacity2 = 150;
        int[] fuelStops2 = {100,100,50};
        minStops = minFuelStops(distances2, fuelCapacity2, fuelStops2);
        System.out.println("Minimum fuel stops required: " + minStops); //Should be 1


        int[] distances3 = {0,100,200};
        int fuelCapacity3 = 50;
        int[] fuelStops3 = {};
        minStops = minFuelStops(distances3, fuelCapacity3, fuelStops3);
        System.out.println("Minimum fuel stops required: " + minStops); // Should be -1


    }
}