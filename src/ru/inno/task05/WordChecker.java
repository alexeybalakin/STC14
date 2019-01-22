package ru.inno.task05;

import java.io.*;

public class WordChecker implements Runnable{
    private String source;
    private String word;
    private OccurenciesFinder occurenciesFinder;

    public WordChecker(String source, String word, OccurenciesFinder occurenciesFinder) {
        this.source = source;
        this.word = word;
        this.occurenciesFinder = occurenciesFinder;
    }

    @Override
    public void run() {
        StringBuilder sentence = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            int i = -1;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                if (c == '\n' || c == '\r'){
                    continue;
                }
                sentence.append(c);
                if (c == '.' || c == '!' || c == '?') {
                    if(sentence.toString().contains(word)){
                        sentence = new StringBuilder(sentence.toString().trim());
                        sentence.append("\r\n");
                        occurenciesFinder.writeFile(sentence.toString());
                    }
                    sentence = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
