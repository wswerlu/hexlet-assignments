package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();

        if (sentence.equals("")) {
            return result;
        }

        String[] words = sentence.split(" ");

        for (String word : words) {
            Integer count = result.getOrDefault(word, 0) + 1;
            result.put(word, count);
        }

        return result;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<String, Integer> word : wordsCount.entrySet()) {
            result.append(String.format("\n  %s: %d", word.getKey(), word.getValue()));
        }
        result.append("\n}");

        return result.toString();
    }
}
//END
