package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static  boolean scrabble(String symbols, String word) {
        List<Character> letters = getCharactersToLowerCaseList(symbols);
        List<Character> wordLetters = getCharactersToLowerCaseList(word);

        for (Character letter : letters) {
            wordLetters.remove(letter);
            if (wordLetters.isEmpty()) {
                break;
            }
        }

        return wordLetters.isEmpty();
    }

    private static List<Character> getCharactersToLowerCaseList(String str) {
        List<Character> chars = new ArrayList<>();

        for (char ch : str.toCharArray()) {
            chars.add(Character.toLowerCase(ch));
        }
        return chars;
    }
}
//END
