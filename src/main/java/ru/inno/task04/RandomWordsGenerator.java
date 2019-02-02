package ru.inno.task04;

import java.util.Random;

/**
 * Генерирует случайные слова из букв латинского алфавита
 *
 * @author Alexey Balakin
 */
public class RandomWordsGenerator {
    private Random random = new Random();
    private String[] words;

    /**
     * Возвращает массив случайных слов, размером 1<=n4<=1000
     *
     * @return массив случайных слов
     */
    public String[] getWords(){
        int wordsAmount = random.nextInt(1000) +1;
        String[] words = new String[wordsAmount];
        for (int i = 0; i < wordsAmount; i++) {
            words[i] = generateRandomWord();
        }
        return words;
    }

    /**
     * Генерирует случайное слово. Слово состоит из
     * 1<=n2<=15 латинских букв
     *
     * @return сгенерированое слово
     */
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
