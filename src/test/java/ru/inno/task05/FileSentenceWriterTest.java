package ru.inno.task05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.*;

class FileSentenceWriterTest {
    private FileSentenceWriter writer;
    private static final String FILE = "testFile.txt";
    private static final String SENTENCE = "Test test test";

    @BeforeEach
    void setUp() {
        writer = new FileSentenceWriter();
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void writeSentence() {
        writer.writeSentence(SENTENCE, FILE);
        String sentence = getSentenceFromFile(FILE);
        assertEquals(SENTENCE, sentence);
    }

    private String getSentenceFromFile(String file) {
        String result = "";
        try {
            result = new String(readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
            result = result.replaceAll(System.lineSeparator(),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}