package ru.inno.task05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

class WordCheckerTest {
    private static final String WORD_TEST = "test";
    private static final String WORD_XXX = "xxx";
    private static final String SOME_FILE_TXT = "someFile.txt";
    private static final String[] SENTENCES = {"Doing Test", "Let's do it", "Test test test"};
    private static final String RESULT = "res";
    @Mock
    private ResourceParser parser;
    @Mock
    private SentenceWriter writer;
    private WordChecker wordChecker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void runFindWordTest() {
        wordChecker = new WordChecker(SOME_FILE_TXT, WORD_TEST, RESULT, parser, writer);
        when(parser.getSentences(anyString())).thenReturn(new HashSet<>(Arrays.asList(SENTENCES)));
        wordChecker.run();
        verify(writer, times(2)).writeSentence(anyString(), anyString());
        verify(writer, times(0)).writeSentence(eq("Let's do it"), anyString());
    }

    @Test
    void runNotFindWordTest() {
        wordChecker = new WordChecker(SOME_FILE_TXT, WORD_XXX, RESULT, parser, writer);
        when(parser.getSentences(anyString())).thenReturn(new HashSet<>(Arrays.asList(SENTENCES)));
        wordChecker.run();
        verify(writer, times(0)).writeSentence(anyString(), anyString());
    }
}