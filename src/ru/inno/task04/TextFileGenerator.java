package ru.inno.task04;

import java.io.*;
import java.util.Random;

public class TextFileGenerator {
    private Random random = new Random();
    private String[] words;
    private int size;
    private int probability;

    public void getFiles(String path, int n, int size, String[] words, int probability) {
        this.words = words;
        this.size = size;
        this.probability = probability;
        for (int i = 0; i < n; i++) {
            String fileName = path + "file" + i + ".txt";
            writeFile(fileName, getText());
        }
    }

    private String getText(){
        String text = "";
        for (int i = 0; i < size; i++) {
            text += getParagraph();
        }
        return text;
    }

    private String getParagraph(){
        String paragraph = "";
        int sentenceAmount = random.nextInt(20) + 1;
        for (int i = 0; i < sentenceAmount; i++) {
            paragraph += getSentence();
        }
        paragraph += "\r\n";
        return paragraph;
    }

    private String getSentence(){
        String sentence = "";
        int wordsAmount = random.nextInt(15) + 1;
        for (int i = 0; i < wordsAmount; i++) {
            String word = "";
            do {
                word = words[random.nextInt(words.length)];
            } while(random.nextInt(probability) != 0);
            if(i == 0){
                word = firstUpperCase(word);
            }
            sentence += word;
            if(i < wordsAmount - 1){
                if (random.nextInt(probability) == 0){
                    sentence += ",";
                }
                sentence += " ";
            }
        }
        sentence += endOfSentence();
        return sentence;
    }

    private String endOfSentence() {
        char[] endOfSentence = {'.', '!', '?'};
        return endOfSentence[random.nextInt(3)] + " ";
    }

    private String firstUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private void writeFile(String fileName, String content) {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}