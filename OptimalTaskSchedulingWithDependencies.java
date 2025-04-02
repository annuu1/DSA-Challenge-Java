import java.util.Arrays;

/**
 * Problem Name:  OptimalTaskSchedulingWithDependencies
 *
 * Problem Description:  Given a set of tasks with dependencies and their execution times, find the minimum time required to complete all tasks.
 * Each task has a unique ID (0-indexed), a list of dependencies (IDs of tasks that must be completed before this task can start), and an execution time.
 *
 * Input:  A list of Task objects. Each Task object contains:
 *         - id: int (unique identifier)
 *         - dependencies: int[] (array of IDs of dependent tasks)
 *         - executionTime: int
 *
 * Output: The minimum time required to complete all tasks.
 */
public class OptimalTaskSchedulingWithDependencies {

    static class Task {
        int id;
        int[] dependencies;
        int executionTime;

        Task(int id, int[] dependencies, int executionTime) {
            this.id = id;
            this.dependencies = dependencies;
            this.executionTime = executionTime;
        }
    }

    public static int minTimeToCompleteTasks(Task[] tasks) {
        // Create an adjacency list to represent dependencies
        int n = tasks.length;
        int[] inDegree = new int[n];
        int[][] adj = new int[n][];
        for (Task task : tasks) {
            for (int dep : task.dependencies) {
                inDegree[task.id]++;
            }
            adj[task.id] = task.dependencies;
        }

        // Topological sort using Kahn's algorithm
        int[] completionTime = new int[n];
        for (int i = 0; i < n; i++) {
            completionTime[i] = tasks[i].executionTime;
        }

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int maxCompletionTime = 0;
        while (!queue.isEmpty()) {
            int taskID = queue.poll();
            maxCompletionTime = Math.max(maxCompletionTime, completionTime[taskID]);

            for (int i = 0; i < n; i++) {
                boolean isDependent = false;
                for(int dep : adj[i]){
                    if(dep == taskID){
                        isDependent = true;
                        break;
                    }
                }
                if (isDependent) {
                    completionTime[i] = Math.max(completionTime[i], completionTime[taskID] + tasks[i].executionTime);
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        return maxCompletionTime;
    }


    public static void main(String[] args) {
        Task[] tasks = {
                new Task(0, new int[]{}, 5),
                new Task(1, new int[]{0}, 3),
                new Task(2, new int[]{0}, 7),
                new Task(3, new int[]{1, 2}, 2)
        };

        int minTime = minTimeToCompleteTasks(tasks);
        System.out.println("Minimum time to complete all tasks: " + minTime); // Output: 17

        Task[] tasks2 = {
                new Task(0, new int[]{}, 1),
                new Task(1, new int[]{0}, 2),
                new Task(2, new int[]{1}, 3),
                new Task(3, new int[]{0,2}, 4)
        };
        minTime = minTimeToCompleteTasks(tasks2);
        System.out.println("Minimum time to complete all tasks: " + minTime); //Output: 10
    }
}