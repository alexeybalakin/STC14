package ru.inno.task05;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Проверяет содержится ли слово в текстовом файле.
 * Если слово содержится, то предложеие, в котором
 * находится слово добавляется в общий ресурс с результатами поиска.
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

    private SentenceWriter writer;
    private ResourceParser parser;

    public WordChecker(String source, String word, String filename, ResourceParser parser, SentenceWriter writer) {
        this.source = source;
        this.word = word;
        this.filename = filename;
        this.parser = parser;
        this.writer = writer;
    }

    @Override
    public void run() {
        Set<String> sentences = parser.getSentences(source);
        if (sentences != null) {
            sentences.stream().filter(this::checkSentence).forEach(sentence -> writer.writeSentence(sentence, filename));
        }
    }

    private boolean checkSentence(String sentence) {
        List<String> words = Arrays.asList(sentence.replaceAll("\\pP", "").
                toLowerCase().split(" "));
        return words.contains(word.toLowerCase());
    }
}
