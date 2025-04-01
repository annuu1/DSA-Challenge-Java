import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Name: EfficientWordCounting
 *
 * Problem Description: Given a string containing multiple words separated by spaces,
 * efficiently count the occurrences of each word (case-insensitive). Return a map
 * where the keys are the words (lowercase), and the values are their counts.  Handle
 * punctuation by removing it from the words before counting.
 */
public class EfficientWordCounting {

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        if (text == null || text.trim().isEmpty()) {
            return wordCounts; //Handle empty or null input
        }

        String[] words = text.toLowerCase().split("\\s+"); //Split by whitespace, lowercase

        for (String word : words) {
            //Remove punctuation using regular expression
            String cleanedWord = word.replaceAll("[^a-zA-Z0-9]", "");
            if (!cleanedWord.isEmpty()) { //Skip empty words after punctuation removal
                wordCounts.put(cleanedWord, wordCounts.getOrDefault(cleanedWord, 0) + 1);
            }
        }

        return wordCounts;
    }


    public static void main(String[] args) {
        String text = "This is a test, this is a test.  This IS a TEST!";
        Map<String, Integer> counts = countWords(text);
        System.out.println(counts); // Expected output (order may vary): {this:3, is:3, a:3, test:3}


        String text2 = "Hello, world!  This is another, example.";
        counts = countWords(text2);
        System.out.println(counts); // Expected output (order may vary): {hello:1, world:1, this:1, is:1, another:1, example:1}

        String text3 = null;
        counts = countWords(text3);
        System.out.println(counts); // Expected output: {}

        String text4 = "";
        counts = countWords(text4);
        System.out.println(counts); // Expected output: {}

        String text5 = "One, two, three... three!!";
        counts = countWords(text5);
        System.out.println(counts); // Expected output: {one:1, two:1, three:2}

    }
}