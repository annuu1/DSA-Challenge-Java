import java.util.ArrayList;
import java.util.List;

/**
 * Problem Name: OptimalDeliveryRoute
 *
 * Problem Description:
 * A delivery service needs to optimize its delivery route.  Given a list of delivery locations represented as (x, y) coordinates,
 * find the shortest possible route that visits all locations exactly once, starting and ending at the same location.  This is a variation of the Traveling Salesperson Problem (TSP).
 * Since finding the absolute shortest route is computationally expensive (NP-hard), this solution will use a greedy approach to find a reasonably short route.
 * The greedy approach will select the nearest unvisited location at each step.
 *
 */
public class OptimalDeliveryRoute {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distanceTo(Point other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    public static List<Point> findRoute(List<Point> locations) {
        if (locations == null || locations.size() < 2) {
            return locations; // Nothing to optimize
        }

        List<Point> route = new ArrayList<>();
        List<Point> unvisited = new ArrayList<>(locations);
        Point current = unvisited.remove(0); // Start at the first location
        route.add(current);

        while (!unvisited.isEmpty()) {
            Point nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (Point p : unvisited) {
                double distance = current.distanceTo(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = p;
                }
            }
            route.add(nearest);
            unvisited.remove(nearest);
            current = nearest;
        }

        //Return to starting point
        route.add(route.get(0));
        return route;
    }


    public static void main(String[] args) {
        List<Point> locations = new ArrayList<>();
        locations.add(new Point(1, 1));
        locations.add(new Point(3, 4));
        locations.add(new Point(5, 2));
        locations.add(new Point(7, 6));
        locations.add(new Point(9, 3));

        List<Point> optimalRoute = findRoute(locations);

        System.out.println("Optimal (Greedy) Route:");
        for (Point p : optimalRoute) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}