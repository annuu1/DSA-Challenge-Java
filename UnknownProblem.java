import java.util.Arrays;

/**
 * Problem Name: OptimalUniversityCourseScheduling
 *
 * Problem Description:
 * A university wants to schedule courses for the next semester.  Each course has a duration (in hours) and a list of prerequisites (other courses that must be completed before it can be taken).  The goal is to schedule the courses in a way that minimizes the total time spent in lectures (sum of durations of all courses), while respecting the prerequisites.  Courses can run concurrently, but a course cannot start until all its prerequisites are completed.
 *
 * Input: A list of courses, where each course is represented by a Course object containing its name, duration, and a list of prerequisite course names.
 * Output: The minimum total duration of the schedule and a list of the courses in an optimal schedule order.
 */
class OptimalUniversityCourseScheduling {

    static class Course {
        String name;
        int duration;
        String[] prerequisites;

        Course(String name, int duration, String[] prerequisites) {
            this.name = name;
            this.duration = duration;
            this.prerequisites = prerequisites;
        }
    }

    public static int[] solve(Course[] courses) {
        // Create a graph representing course dependencies
        Graph graph = new Graph(courses.length);
        for (int i = 0; i < courses.length; i++) {
            for (String pre : courses[i].prerequisites) {
                int preIndex = findCourseIndex(courses, pre);
                if (preIndex != -1) {
                    graph.addEdge(preIndex, i);
                }
            }
        }


        //Topological sort to find a valid order
        int[] order = graph.topologicalSort();
        if (order == null) {
            System.out.println("Cycle detected in course prerequisites!"); //Handle cyclic dependencies
            return null;
        }

        //Calculate total duration
        int totalDuration = 0;
        for (int i : order) {
            totalDuration += courses[i].duration;
        }

        return new int[] {totalDuration, Arrays.toString(order).hashCode()}; //Returning hashcode for uniqueness of order

    }

    private static int findCourseIndex(Course[] courses, String courseName) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].name.equals(courseName)) {
                return i;
            }
        }
        return -1;
    }


    // Simple graph implementation using adjacency list
    static class Graph {
        int V;
        java.util.List<Integer>[] adj;

        Graph(int v) {
            V = v;
            adj = new java.util.ArrayList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new java.util.ArrayList<>();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        int[] topologicalSort() {
            int[] inDegree = new int[V];
            for (int u = 0; u < V; u++) {
                for (int v : adj[u]) {
                    inDegree[v]++;
                }
            }

            java.util.Queue<Integer> q = new java.util.LinkedList<>();
            for (int i = 0; i < V; i++) {
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }

            int[] result = new int[V];
            int count = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                result[count++] = u;
                for (int v : adj[u]) {
                    if (--inDegree[v] == 0) {
                        q.add(v);
                    }
                }
            }

            if (count != V) {
                return null; // Cycle detected
            }
            return result;
        }
    }


    public static void main(String[] args) {
        Course[] courses = {
                new Course("A", 4, new String[0]),
                new Course("B", 2, new String[]{"A"}),
                new Course("C", 3, new String[]{"A"}),
                new Course("D", 5, new String[]{"B", "C"})
        };

        int[] result = solve(courses);
        if(result != null) {
            System.out.println("Minimum total duration: " + result[0]);
            System.out.println("Optimal schedule order hashcode (for uniqueness): " + result[1]); //Use the hashcode to compare schedules efficiently
        }
    }
}