import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  OptimalMeetingScheduling
 *
 * Problem Description:
 * Given an array of meeting times, represented as pairs of start and end times,
 * find the maximum number of non-overlapping meetings that can be scheduled.
 *  A meeting [start, end] overlaps with another meeting [start2, end2] if
 *  start < end2 && end > start2.
 */
public class OptimalMeetingScheduling {

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            return this.end - other.end; // Sort by end time
        }
    }

    public static int maxNonOverlappingMeetings(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }

        // Sort meetings by end time
        Arrays.sort(meetings);

        int count = 1; // Count of non-overlapping meetings
        int lastEndTime = meetings[0].end;

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i].start >= lastEndTime) {
                count++;
                lastEndTime = meetings[i].end;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Meeting[] meetings1 = {
                new Meeting(1, 5),
                new Meeting(6, 10),
                new Meeting(11, 15),
                new Meeting(2, 7) //Overlaps with 1,5 and 6,10
        };
        System.out.println("Max non-overlapping meetings: " + maxNonOverlappingMeetings(meetings1)); // Output: 3

        Meeting[] meetings2 = {
                new Meeting(1, 3),
                new Meeting(2, 4),
                new Meeting(5, 7),
                new Meeting(6,8)
        };
        System.out.println("Max non-overlapping meetings: " + maxNonOverlappingMeetings(meetings2)); //Output: 2

        Meeting[] meetings3 = {};
        System.out.println("Max non-overlapping meetings: " + maxNonOverlappingMeetings(meetings3)); // Output: 0

        Meeting[] meetings4 = {new Meeting(1,10)};
        System.out.println("Max non-overlapping meetings: " + maxNonOverlappingMeetings(meetings4)); // Output: 1

    }
}