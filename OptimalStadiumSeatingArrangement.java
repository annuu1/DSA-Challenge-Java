import java.util.Arrays;

/**
 * Problem Name: OptimalStadiumSeatingArrangement
 *
 * Problem Description:
 * A stadium has a rectangular grid of seats.  Each seat has a 'value' representing its desirability (higher is better).
 * You need to assign seats to groups of people such that:
 * 1. Each group has a size of 'groupSize'.
 * 2. Groups must be seated in contiguous blocks (horizontally or vertically).
 * 3. The total value of seats assigned to each group should be maximized.
 * 4. No seat can be assigned to more than one group.
 *
 * Find the maximum total value that can be achieved by assigning seats to groups.
 * If it's impossible to assign all groups, return -1.
 */
public class OptimalStadiumSeatingArrangement {

    public static int maxStadiumValue(int[][] stadium, int groupSize) {
        int rows = stadium.length;
        int cols = stadium[0].length;
        int numGroups = (rows * cols) / groupSize; //Check if groups are possible


        if((rows * cols) % groupSize != 0){
            return -1;
        }

        int[][] dp = new int[rows][cols];
        int maxVal = 0;


        //Base Cases: single seat values
        for(int i =0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                dp[i][j] = stadium[i][j];
            }
        }

        //Dynamic Programming: build up from smaller blocks
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                //Horizontal Check
                if(j + groupSize <= cols){
                    int sum = 0;
                    for(int k = 0; k < groupSize; k++){
                        sum += stadium[i][j+k];
                    }
                    dp[i][j] = Math.max(dp[i][j], sum);
                }

                //Vertical Check
                if(i + groupSize <= rows){
                    int sum = 0;
                    for(int k = 0; k < groupSize; k++){
                        sum += stadium[i+k][j];
                    }
                    dp[i][j] = Math.max(dp[i][j], sum);
                }

                maxVal = Math.max(maxVal, dp[i][j]);
            }
        }

        return maxVal;
    }


    public static void main(String[] args) {
        int[][] stadium = {
                {10, 20, 30, 40},
                {50, 60, 70, 80},
                {90, 100, 110, 120},
                {130, 140, 150, 160}
        };

        int groupSize = 2;
        int maxValue = maxStadiumValue(stadium, groupSize);
        System.out.println("Max stadium value for groups of size " + groupSize + ": " + maxValue);


        int[][] stadium2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        groupSize = 3;
        maxValue = maxStadiumValue(stadium2, groupSize);
        System.out.println("Max stadium value for groups of size " + groupSize + ": " + maxValue);


        int[][] stadium3 = {{1,2},{3,4}};
        groupSize = 1;
        maxValue = maxStadiumValue(stadium3, groupSize);
        System.out.println("Max stadium value for groups of size " + groupSize + ": " + maxValue);

        int[][] stadium4 = {{1,2},{3,4}};
        groupSize = 5; //Impossible case
        maxValue = maxStadiumValue(stadium4, groupSize);
        System.out.println("Max stadium value for groups of size " + groupSize + ": " + maxValue);


    }
}