import java.util.Arrays;

/**
 * Problem Name: OptimalTeamFormationWithSkillConstraints
 *
 * Problem Description:
 * You are given a list of potential team members, each with a skill level in different areas.  You need to form a team with a maximum number of members, subject to the following constraints:
 * 1. The team must have at least one member with a skill level of at least 'minSkill' in each of the 'numSkills' skill areas.
 * 2. No two members can have the same skill level in any area (e.g., two members cannot both have a skill level of 5 in 'coding').
 *
 * Input:
 *  - teamMembers: A 2D array where each inner array represents a team member's skill levels in different areas.
 *  - minSkill: The minimum skill level required in each skill area.
 *  - numSkills: The number of skill areas.
 *
 * Output:
 *  - The maximum number of team members that can be formed while satisfying the constraints.  Return -1 if no valid team can be formed.
 */
public class OptimalTeamFormationWithSkillConstraints {

    public static int maxTeamSize(int[][] teamMembers, int minSkill, int numSkills) {
        int numMembers = teamMembers.length;
        if (numMembers == 0 || numSkills == 0) return 0; //Handle edge cases


        int maxTeam = 0;
        for (int i = 0; i < (1 << numMembers); i++) { // Iterate through all possible subsets of team members
            int[] team = new int[numSkills];
            int currentTeamSize = 0;
            boolean validTeam = true;
            boolean[] skillLevelsUsed = new boolean[numSkills*100]; //Assuming max skill level is 99. Adjust as needed.

            for (int j = 0; j < numMembers; j++) {
                if ((i >> j) % 2 == 1) { //Check if jth member is in the subset
                    currentTeamSize++;
                    for (int k = 0; k < numSkills; k++) {
                        if(skillLevelsUsed[k * 100 + teamMembers[j][k]]){
                            validTeam = false;
                            break;
                        }
                        team[k] = Math.max(team[k], teamMembers[j][k]);
                        skillLevelsUsed[k * 100 + teamMembers[j][k]] = true;
                    }
                    if (!validTeam) break;
                }
            }

            if (validTeam) {
                boolean meetsMinSkill = true;
                for (int skillLevel : team) {
                    if (skillLevel < minSkill) {
                        meetsMinSkill = false;
                        break;
                    }
                }
                if (meetsMinSkill) {
                    maxTeam = Math.max(maxTeam, currentTeamSize);
                }
            }
        }

        return maxTeam == 0 ? -1 : maxTeam;
    }


    public static void main(String[] args) {
        int[][] teamMembers = {
                {5, 8, 2},
                {7, 3, 9},
                {6, 4, 10},
                {9, 7, 5},
                {2,1,1}
        };
        int minSkill = 5;
        int numSkills = 3;

        int maxTeamSize = maxTeamSize(teamMembers, minSkill, numSkills);
        System.out.println("Maximum team size: " + maxTeamSize); // Expected output: 3 or 2 depending on constraints interpretation


        int[][] teamMembers2 = {{1,2},{3,4},{5,6}};
        minSkill = 2;
        numSkills = 2;
        maxTeamSize = maxTeamSize(teamMembers2, minSkill, numSkills);
        System.out.println("Maximum team size: " + maxTeamSize); //Expected 2
    }
}