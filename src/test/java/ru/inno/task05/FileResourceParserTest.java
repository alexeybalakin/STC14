package ru.inno.task05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileResourceParserTest {
    private FileResourceParser fileResourceParser;
    private final static String FILE = "/task05/texts/4.txt";

    @BeforeEach
    void setUp() {
        fileResourceParser = new FileResourceParser();
    }

    @Test
    void getSentences() {
        Set<String> sentences =  fileResourceParser.getSentences(FILE);
        assertNotNull(sentences);
        assertNotEquals(0, sentences.size());
        assertDoesNotThrow(() -> fileResourceParser.getSentences(FILE));
    }
}