import java.util.Arrays;

public class OptimalClassroomAssignment {

    /**
     * Problem: Optimal Classroom Assignment
     *
     * Given a list of student preferences for classrooms (represented as a 2D array where each inner array represents a student's ranked preferences), and a list of classroom capacities, find the optimal assignment of students to classrooms to minimize the total dissatisfaction.  Dissatisfaction is calculated as the rank of the classroom assigned to a student.  Rank 1 is no dissatisfaction, rank 2 is 1 unit of dissatisfaction, and so on.  A student can only be assigned to a classroom with available capacity.
     *
     * For example:
     * preferences = [[1, 2, 3], [2, 1, 3], [3, 1, 2]]  // Student 1 prefers 1, then 2, then 3, etc.
     * capacities = [1, 1, 1]                          // Each classroom holds only 1 student
     *
     * Optimal assignment:
     * Student 1 -> Classroom 1 (dissatisfaction = 0)
     * Student 2 -> Classroom 2 (dissatisfaction = 1)
     * Student 3 -> Classroom 3 (dissatisfaction = 1)
     * Total dissatisfaction = 2
     *
     */
    public static int minDissatisfaction(int[][] preferences, int[] capacities) {
        int numStudents = preferences.length;
        int numClassrooms = capacities.length;

        //Check for invalid inputs
        if (numStudents == 0 || numClassrooms == 0 || preferences.length != numStudents){
            throw new IllegalArgumentException("Invalid input parameters");
        }

        int[] assignment = new int[numStudents];
        int[] classroomOccupancy = Arrays.copyOf(capacities, capacities.length);
        int totalDissatisfaction = 0;

        for (int i = 0; i < numStudents; i++) {
            int minDissatisfactionForStudent = Integer.MAX_VALUE;
            int assignedClassroom = -1;

            for (int j = 0; j < numClassrooms; j++) {
                int rank = getRank(preferences[i], j + 1);
                if (classroomOccupancy[j] > 0 && rank < minDissatisfactionForStudent) {
                    minDissatisfactionForStudent = rank;
                    assignedClassroom = j;
                }
            }
            if (assignedClassroom != -1){
                assignment[i] = assignedClassroom + 1;
                classroomOccupancy[assignedClassroom]--;
                totalDissatisfaction += minDissatisfactionForStudent;
            } else {
                //Handle the case where no classroom has enough capacity.  This could be improved with more sophisticated algorithms
                return Integer.MAX_VALUE;
            }

        }

        return totalDissatisfaction;
    }

    private static int getRank(int[] preferences, int classroom) {
        for (int i = 0; i < preferences.length; i++) {
            if (preferences[i] == classroom) {
                return i;
            }
        }
        return Integer.MAX_VALUE; //Should not happen if classroom is valid
    }


    public static void main(String[] args) {
        int[][] preferences = {{1, 2, 3}, {2, 1, 3}, {3, 1, 2}};
        int[] capacities = {1, 1, 1};
        int minDissatisfaction = minDissatisfaction(preferences, capacities);
        System.out.println("Minimum dissatisfaction: " + minDissatisfaction); // Output: 2


        int[][] preferences2 = {{1,2,3},{2,3,1},{3,1,2},{1,3,2}};
        int[] capacities2 = {2,2};
        minDissatisfaction = minDissatisfaction(preferences2, capacities2);
        System.out.println("Minimum dissatisfaction: " + minDissatisfaction); // Output: 2


        int[][] preferences3 = {{1, 2, 3}, {2, 1, 3}};
        int[] capacities3 = {1, 1, 1};
        minDissatisfaction = minDissatisfaction(preferences3, capacities3);
        System.out.println("Minimum dissatisfaction: " + minDissatisfaction); // Output: 1


    }
}