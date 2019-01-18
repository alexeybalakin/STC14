package ru.inno.task04;

import java.util.Random;

public class RandomWordsGenerator {
    private Random random = new Random();
    private String[] words;

    public String[] getWords(){
        int wordsAmount = random.nextInt(1000) +1;
        String[] words = new String[wordsAmount];
        for (int i = 0; i < wordsAmount; i++) {
            words[i] = generateRandomWord();
        }
        return words;
    }

    private String generateRandomWord() {
        int wordLength = random.nextInt(15) + 1;
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) {
            char c = (char)(random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
}
