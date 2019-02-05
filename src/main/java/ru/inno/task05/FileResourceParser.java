package ru.inno.task05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileResourceParser implements ResourceParser{
    @Override
    public Set<String> getSentences(String source) {
        Set<String> sentences = new HashSet<>();
        StringBuilder sentence = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            int i = -1;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                if (c == '\n' || c == '\r') {
                    continue;
                }
                sentence.append(c);
                if (c == '.' || c == '!' || c == '?') {
                    sentences.add(sentence.toString());
                    sentence = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentences;
    }
}
