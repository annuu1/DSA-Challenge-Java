import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name:  LongestSubsequenceWithUniqueCharacters
 *
 * Problem Description: Given a string, find the length of the longest subsequence
 *  (not necessarily contiguous) that contains only unique characters.
 *
 *  For example:
 *  Input: "abcabcbb"
 *  Output: 3 (The longest subsequence with unique characters is "abc")
 *
 *  Input: "bbbbb"
 *  Output: 1 (The longest subsequence with unique characters is "b")
 *
 *  Input: "pwwkew"
 *  Output: 3 (The longest subsequence with unique characters is "pwe" or "wke" etc.)
 *
 */
public class LongestSubsequenceWithUniqueCharacters {

    public static int longestSubsequenceWithUniqueChars(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        int end = 0;

        while (end < s.length()) {
            char currentChar = s.charAt(end);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            while (charCount.get(currentChar) > 1) {
                char startChar = s.charAt(start);
                charCount.put(startChar, charCount.get(startChar) -1);
                if(charCount.get(startChar) == 0) {
                    charCount.remove(startChar);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }

        return maxLength;
    }


    public static void main(String[] args) {
        String[] testCases = {"abcabcbb", "bbbbb", "pwwkew", "abcabcabc", "", "abcdefg", "aabcdeff"};
        for (String testCase : testCases) {
            int result = longestSubsequenceWithUniqueChars(testCase);
            System.out.println("Input: \"" + testCase + "\", Output: " + result);
        }
    }
}