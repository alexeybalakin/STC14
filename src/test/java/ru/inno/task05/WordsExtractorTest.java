package ru.inno.task05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsExtractorTest {
    private WordsExtractor wordsExtractor;
    private final static String FILE_NAME = "/task05/dictionary.txt";

    @BeforeEach
    void setUp() {
        wordsExtractor = new WordsExtractor();
    }

    @Test
    void getWords() {
        String[] words = wordsExtractor.getWords(FILE_NAME);
        assertNotNull(words);
        Assertions.assertNotEquals(0, words.length);
        Assertions.assertDoesNotThrow(() -> wordsExtractor.getWords(FILE_NAME));
    }
}