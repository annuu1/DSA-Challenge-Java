import java.util.Arrays;

/**
 * Problem Name:  MinCostToFormPalindrome
 *
 * Problem Description: Given a string `s`, you can perform the following operation:
 *  - Choose any two characters from the string and swap them.  This costs 1 unit.
 *  Find the minimum cost to transform the string `s` into a palindrome.  If it's impossible, return -1.
 *
 */
public class MinCostToFormPalindrome {

    public static int minCostToFormPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int cost = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (charArray[left] != charArray[right]) {
                //Find the rightmost occurrence of charArray[left] and the leftmost occurrence of charArray[right]
                int rightmostLeft = -1;
                int leftmostRight = -1;

                for (int i = right; i > left; i--) {
                    if (charArray[i] == charArray[left]) {
                        rightmostLeft = i;
                        break;
                    }
                }
                for (int i = left; i < right; i++) {
                    if (charArray[i] == charArray[right]) {
                        leftmostRight = i;
                        break;
                    }
                }


                if (rightmostLeft == -1 && leftmostRight == -1) return -1; //Impossible to form a palindrome

                int minSwaps;
                if(rightmostLeft == -1) minSwaps = right - left;
                else if(leftmostRight == -1) minSwaps = right - left;
                else minSwaps = Math.min(right - left, (right - rightmostLeft) + (leftmostRight - left));

                cost += minSwaps;

                //Simulate the swap (we don't actually need to swap, just track cost)

                char temp = charArray[left];
                if(rightmostLeft != -1) charArray[left] = charArray[rightmostLeft];
                else charArray[left] = charArray[right];

                if(leftmostRight != -1) charArray[right] = temp;
                else charArray[right] = temp;

            }
            left++;
            right--;
        }
        return cost;
    }


    public static void main(String[] args) {
        String s1 = "abcba";
        System.out.println("Min cost for '" + s1 + "': " + minCostToFormPalindrome(s1)); //0

        String s2 = "abccb";
        System.out.println("Min cost for '" + s2 + "': " + minCostToFormPalindrome(s2)); //2

        String s3 = "abcabc";
        System.out.println("Min cost for '" + s3 + "': " + minCostToFormPalindrome(s3)); //2

        String s4 = "abab";
        System.out.println("Min cost for '" + s4 + "': " + minCostToFormPalindrome(s4)); //0

        String s5 = "abcde";
        System.out.println("Min cost for '" + s5 + "': " + minCostToFormPalindrome(s5)); //-1 //Impossible

        String s6 = "aabbc";
        System.out.println("Min cost for '" + s6 + "': " + minCostToFormPalindrome(s6)); //2
    }
}