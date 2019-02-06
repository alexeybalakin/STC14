package ru.inno.task05;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Реализация ResourceParser для источника,
 * являющегося текстовым файлом.
 *
 * @author Alexey Balakin
 */
public class FileResourceParser implements ResourceParser {
    @Override
    public Set<String> getSentences(String source) {
        Set<String> sentences = new HashSet<>();
        if (source != null && !source.isEmpty()) {
            StringBuilder sentence = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(source)))) {
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
        }
        return sentences;
    }
}
