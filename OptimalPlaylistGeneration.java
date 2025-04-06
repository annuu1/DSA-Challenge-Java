import java.util.Arrays;

/**
 * Problem Name: OptimalPlaylistGeneration
 *
 * Problem Description: Given a list of songs with their durations (in seconds) and a target playlist duration,
 * find the combination of songs that comes closest to the target duration without exceeding it.  Return the total duration
 * of the selected songs and the indices of the songs selected. If no combination is possible, return -1 and an empty array.
 */
public class OptimalPlaylistGeneration {

    public static int[] findOptimalPlaylist(int[] songDurations, int targetDuration) {
        int n = songDurations.length;
        int closestSum = -1;
        int[] closestCombination = {};

        for (int i = 0; i < (1 << n); i++) { // Iterate through all possible subsets of songs
            int currentSum = 0;
            int[] currentCombination = new int[n];
            int count = 0;

            for (int j = 0; j < n; j++) {
                if ((i >> j) % 2 == 1) {
                    currentSum += songDurations[j];
                    currentCombination[count++] = j;
                }
            }

            if (currentSum <= targetDuration) {
                if (closestSum == -1 || targetDuration - currentSum < targetDuration - closestSum) {
                    closestSum = currentSum;
                    closestCombination = Arrays.copyOf(currentCombination, count);
                }
            }
        }

        if (closestSum == -1){
            return new int[]{-1};
        } else {
            return closestCombination;
        }
    }


    public static void main(String[] args) {
        int[] songDurations = {180, 240, 120, 300, 150, 60};
        int targetDuration = 600;

        int[] resultIndices = findOptimalPlaylist(songDurations, targetDuration);

        if (resultIndices[0] == -1){
            System.out.println("No suitable playlist found.");
        } else {
            System.out.print("Optimal playlist indices: [");
            for (int i = 0; i < resultIndices.length; i++) {
                System.out.print(resultIndices[i] + (i < resultIndices.length - 1 ? ", " : ""));
            }
            System.out.println("]");

            int totalDuration = 0;
            for(int index : resultIndices){
                totalDuration += songDurations[index];
            }
            System.out.println("Total duration: " + totalDuration);
        }



        int[] songDurations2 = {100, 200, 300, 400};
        int targetDuration2 = 10;

        int[] resultIndices2 = findOptimalPlaylist(songDurations2, targetDuration2);

        if (resultIndices2[0] == -1){
            System.out.println("\nNo suitable playlist found for the second example.");
        } else {
            System.out.print("Optimal playlist indices: [");
            for (int i = 0; i < resultIndices2.length; i++) {
                System.out.print(resultIndices2[i] + (i < resultIndices2.length - 1 ? ", " : ""));
            }
            System.out.println("]");

            int totalDuration = 0;
            for(int index : resultIndices2){
                totalDuration += songDurations2[index];
            }
            System.out.println("Total duration: " + totalDuration);
        }
    }
}