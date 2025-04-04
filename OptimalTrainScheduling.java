import java.util.Arrays;

/**
 * Problem Name: Optimal Train Scheduling
 *
 * Problem Description:
 * You are given a list of trains, each represented by a start time and an end time.  You have a single train track.  Find the maximum number of trains that can be scheduled without any overlaps.  Overlaps occur if the start time of one train is before the end time of another.
 */
public class OptimalTrainScheduling {

    public static class Train implements Comparable<Train> {
        int start;
        int end;

        public Train(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Train other) {
            return this.end - other.end; // Sort by end time
        }
    }

    public static int maxTrainsScheduled(Train[] trains) {
        if (trains == null || trains.length == 0) {
            return 0;
        }

        // Sort trains by their end times
        Arrays.sort(trains);

        int scheduledCount = 1;
        int lastEndTime = trains[0].end;

        for (int i = 1; i < trains.length; i++) {
            if (trains[i].start >= lastEndTime) {
                scheduledCount++;
                lastEndTime = trains[i].end;
            }
        }

        return scheduledCount;
    }

    public static void main(String[] args) {
        Train[] trains = {
                new Train(1, 5),
                new Train(3, 8),
                new Train(6, 10),
                new Train(11,13),
                new Train(8, 12)
        };

        int maxTrains = maxTrainsScheduled(trains);
        System.out.println("Maximum number of trains that can be scheduled: " + maxTrains); // Output: 3


        Train[] trains2 = {
                new Train(1,10),
                new Train(2,5),
                new Train(5,11),
                new Train(11,15)
        };
        maxTrains = maxTrainsScheduled(trains2);
        System.out.println("Maximum number of trains that can be scheduled: " + maxTrains); //Output: 2


        Train[] trains3 = {};
        maxTrains = maxTrainsScheduled(trains3);
        System.out.println("Maximum number of trains that can be scheduled: " + maxTrains); //Output: 0

        Train[] trains4 = null;
        maxTrains = maxTrainsScheduled(trains4);
        System.out.println("Maximum number of trains that can be scheduled: " + maxTrains); //Output: 0

    }
}