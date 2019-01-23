package ru.inno.task05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Вспомогательный класс для получения списка слов
 * из текстового файла
 *
 * @author Alexey Balakin
 */
public class WordsExtractor {

    /**
     * Создает массив слов из полученного файла
     *
     * @param file имя текстовоо файла, содрежащего в каждой строке по одному слову
     * @return массив слов
     */
    public String[] getWords(String file) {
        String[] words = new String[500];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < words.length; i++) {
                words[i] = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
