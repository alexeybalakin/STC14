package ru.inno.task05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordsExtractor {
    private Random random = new Random();
    public String[] getWords(String file){
        String[] words = new String[500];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < words.length; i++) {
                words[i] = reader.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
