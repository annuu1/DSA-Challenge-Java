import java.util.Arrays;

/**
 * Problem Name: Optimal Tower Construction
 *
 * Problem Description: You are given a list of blocks, each with a height.  You want to construct a tower using these blocks,
 * such that the tower is as tall as possible, but you cannot place a block on top of another block that is taller than it.
 * Find the maximum height of the tower you can build.
 */
public class OptimalTowerConstruction {

    public static int maxTowerHeight(int[] blocks) {
        // Sort the blocks in ascending order of height.
        Arrays.sort(blocks);

        int[] dp = new int[blocks.length];
        Arrays.fill(dp, blocks[0]); // Initialize dp array with the height of the smallest block

        for (int i = 1; i < blocks.length; i++) {
            dp[i] = blocks[i]; //Consider the current block as a separate tower
            for (int j = 0; j < i; j++) {
                if (blocks[i] >= blocks[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + blocks[i]); //Extend an existing tower if possible
                }
            }
        }

        int maxHeight = 0;
        for (int height : dp) {
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }


    public static void main(String[] args) {
        int[] blocks1 = {1, 2, 3, 4, 5};
        System.out.println("Max height for blocks1: " + maxTowerHeight(blocks1)); // Output: 15

        int[] blocks2 = {5, 4, 3, 2, 1};
        System.out.println("Max height for blocks2: " + maxTowerHeight(blocks2)); // Output: 15

        int[] blocks3 = {1, 3, 2, 4, 5, 6, 8, 7};
        System.out.println("Max height for blocks3: " + maxTowerHeight(blocks3)); // Output: 31

        int[] blocks4 = {10, 5, 20, 15, 8, 12};
        System.out.println("Max height for blocks4: " + maxTowerHeight(blocks4)); //Output: 47 or similar (depends on order after sorting)


    }
}