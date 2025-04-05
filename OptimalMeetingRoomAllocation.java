import java.util.Arrays;

/**
 * Problem Name: OptimalMeetingRoomAllocation
 *
 * Problem Description:
 * Given a list of meetings, each represented by a start and end time, find the minimum number of meeting rooms required to accommodate all meetings without any overlap.
 * Each meeting is represented as an integer array of length 2: [startTime, endTime].  Start and end times are integers.
 *
 * Example:
 * meetings = [[1, 5], [2, 6], [7, 10], [8, 11], [12,15]]  =>  Output: 2
 * meetings = [[1, 10], [2, 5], [6, 9]] => Output: 2
 * meetings = [[0,30],[5,10],[15,20]] => Output: 2
 */
public class OptimalMeetingRoomAllocation {

    public static int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Use a min-heap to track the end times of ongoing meetings
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        // Iterate through the meetings
        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            // If there's a meeting ending before the current meeting starts, remove it from the heap
            if (!endTimes.isEmpty() && endTimes.peek() <= startTime) {
                endTimes.poll();
            }

            // Add the end time of the current meeting to the heap
            endTimes.offer(endTime);
        }

        // The size of the heap represents the minimum number of meeting rooms required
        return endTimes.size();
    }


    public static void main(String[] args) {
        int[][] meetings1 = {{1, 5}, {2, 6}, {7, 10}, {8, 11}, {12, 15}};
        int[][] meetings2 = {{1, 10}, {2, 5}, {6, 9}};
        int[][] meetings3 = {{0,30},{5,10},{15,20}};
        int[][] meetings4 = {};
        int[][] meetings5 = null;


        System.out.println("Minimum meeting rooms required for meetings1: " + minMeetingRooms(meetings1)); // Output: 2
        System.out.println("Minimum meeting rooms required for meetings2: " + minMeetingRooms(meetings2)); // Output: 2
        System.out.println("Minimum meeting rooms required for meetings3: " + minMeetingRooms(meetings3)); // Output: 2
        System.out.println("Minimum meeting rooms required for meetings4: " + minMeetingRooms(meetings4)); // Output: 0
        System.out.println("Minimum meeting rooms required for meetings5: " + minMeetingRooms(meetings5)); // Output: 0

    }
}