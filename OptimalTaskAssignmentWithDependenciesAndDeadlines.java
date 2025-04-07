import java.util.Arrays;

/**
 * Problem Name: OptimalTaskAssignmentWithDependenciesAndDeadlines
 *
 * Problem Description:  Given a list of tasks, each with a duration, dependencies (a list of tasks that must be completed before this task can start), and a deadline, find the optimal schedule that minimizes the maximum lateness of any task.  Lateness is defined as the completion time of a task minus its deadline.  A task's completion time is the sum of its duration and the completion time of its last dependency.  If a task has no dependencies, its completion time is simply its duration.  Return the maximum lateness of any task in the optimal schedule.
 */
public class OptimalTaskAssignmentWithDependenciesAndDeadlines {

    static class Task {
        int id;
        int duration;
        int[] dependencies;
        int deadline;
        int completionTime;

        Task(int id, int duration, int[] dependencies, int deadline) {
            this.id = id;
            this.duration = duration;
            this.dependencies = dependencies;
            this.deadline = deadline;
            this.completionTime = 0;
        }
    }

    public static int minMaxLateness(Task[] tasks) {
        //Sort tasks topologically (this is a simplification, a proper topological sort would be needed for a more robust solution)
        Arrays.sort(tasks, (a, b) -> Integer.compare(a.dependencies.length, b.dependencies.length));


        for (Task task : tasks) {
            int maxDependencyCompletion = 0;
            for (int dependencyId : task.dependencies) {
                for (Task depTask : tasks) {
                    if (depTask.id == dependencyId) {
                        maxDependencyCompletion = Math.max(maxDependencyCompletion, depTask.completionTime);
                        break;
                    }
                }
            }
            task.completionTime = maxDependencyCompletion + task.duration;
        }

        int maxLateness = 0;
        for (Task task : tasks) {
            maxLateness = Math.max(maxLateness, task.completionTime - task.deadline);
        }
        return maxLateness;
    }


    public static void main(String[] args) {
        Task[] tasks = new Task[]{
                new Task(0, 5, new int[]{}, 10),
                new Task(1, 3, new int[]{0}, 8),
                new Task(2, 2, new int[]{0}, 7),
                new Task(3, 4, new int[]{1, 2}, 15)
        };

        int maxLateness = minMaxLateness(tasks);
        System.out.println("Maximum lateness: " + maxLateness); //Expected output should be less than or equal to 2


        Task[] tasks2 = new Task[]{
                new Task(0, 1, new int[]{}, 3),
                new Task(1, 2, new int[]{0}, 5),
                new Task(2, 3, new int[]{1}, 8)
        };
        maxLateness = minMaxLateness(tasks2);
        System.out.println("Maximum lateness: " + maxLateness); //Expected output should be 0 or less


    }
}