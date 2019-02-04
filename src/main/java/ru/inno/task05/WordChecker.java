package ru.inno.task05;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Проверяет содержится ли слово в текстовом файле.
 * Если слово содержится, то предложеие, в котором
 * находится слово добавляется в общий файл срезультатами поиска.
 *
 * @author Alexey Balakin
 */
public class WordChecker implements Runnable {
    /**
     * Имя файла, в котором будет происходить поиск
     */
    private String source;

    /**
     * Слово, которое нужно найти
     */
    private String word;

    /**
     * Файл в который будут записаны результаты поиска
     */
    private String filename;

    public WordChecker(String source, String word, String filename) {
        this.source = source;
        this.word = word;
        this.filename = filename;
    }

    @Override
    public void run() {
        Set<String> sentences  = getSentences(source);
        sentences.stream().filter(this::checkSentence).forEach(this::writeFile);
    }

    private Set<String> getSentences(String source) {
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

    private boolean checkSentence(String sentence) {
        List<String> words = Arrays.asList(sentence.replaceAll("\\pP", "").split(" "));
        return words.contains(word);
    }

    /**
     * Записывает в файл найденное предложение.
     *
     * @param sentence предложение, в котором содержится искомое слово
     */
    private void writeFile(String sentence) {
        sentence = sentence.trim() + System.lineSeparator();
        try (Writer writer = new FileWriter(filename, true)) {
            synchronized (WordChecker.class) {
                writer.write(sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
