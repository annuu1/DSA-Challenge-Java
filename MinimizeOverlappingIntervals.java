import java.util.Arrays;

/**
 * Problem Name:  MinimizeOverlappingIntervals
 *
 * Problem Description: Given an array of intervals, where each interval is represented by a start and end time, find the minimum number of intervals that need to be removed to ensure that no two intervals overlap.  Overlapping intervals are defined as intervals where the start time of one is less than or equal to the end time of another.
 */
public class MinimizeOverlappingIntervals {

    public static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval other) {
            return Integer.compare(this.end, other.end); // Sort by end time
        }
    }


    public static int minIntervalsToRemove(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals by their end times
        Arrays.sort(intervals);

        int removedCount = 0;
        int lastEndTime = Integer.MIN_VALUE;

        for (Interval interval : intervals) {
            if (interval.start < lastEndTime) {
                removedCount++; //Overlap detected, remove this interval
            } else {
                lastEndTime = interval.end; //Update lastEndTime if no overlap
            }
        }

        return removedCount;
    }


    public static void main(String[] args) {
        Interval[] intervals1 = {
                new Interval(1, 5),
                new Interval(2, 4),
                new Interval(6, 10),
                new Interval(8, 12)
        };
        System.out.println("Minimum intervals to remove: " + minIntervalsToRemove(intervals1)); // Output: 1


        Interval[] intervals2 = {
                new Interval(1, 3),
                new Interval(3, 5),
                new Interval(5, 7),
                new Interval(7,9)
        };
        System.out.println("Minimum intervals to remove: " + minIntervalsToRemove(intervals2)); //Output: 0

        Interval[] intervals3 = {
                new Interval(1, 10),
                new Interval(2, 3),
                new Interval(4, 6),
                new Interval(7, 9)
        };
        System.out.println("Minimum intervals to remove: " + minIntervalsToRemove(intervals3)); // Output: 3


        Interval[] intervals4 = {};
        System.out.println("Minimum intervals to remove: " + minIntervalsToRemove(intervals4)); // Output: 0

    }
}