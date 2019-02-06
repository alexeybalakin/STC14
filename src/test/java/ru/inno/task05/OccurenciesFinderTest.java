package ru.inno.task05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OccurenciesFinderTest {
    private static final String[] SENTENCES = {"Doing Test", "Let's do it", "Test test test"};
    private static final String[] WORDS = {"test"};
    private static final String RESULT = "res";
    private static final String[] SOURCES = {"someFile.txt"};
    @Mock
    private ResourceParser parser;
    @Mock
    private SentenceWriter writer;
    private OccurenciesFinder occurenciesFinder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        occurenciesFinder = new OccurenciesFinder(parser, writer);
    }

    @Test
    void getOccurenciesFindWordTest() {
        when(parser.getSentences(anyString())).thenReturn(new HashSet<>(Arrays.asList(SENTENCES)));
        occurenciesFinder.getOccurencies(SOURCES, WORDS, RESULT);
        verify(writer).writeSentence(eq("Doing Test"), anyString());
        verify(writer).writeSentence(eq("Test test test"), anyString());
        verify(writer, times(0)).writeSentence(eq("Let's do it"), anyString());
    }

    @Test
    void getOccurenciesNullTest() {
        assertDoesNotThrow(() -> occurenciesFinder.getOccurencies(null, WORDS, RESULT));
        assertDoesNotThrow(() -> occurenciesFinder.getOccurencies(SOURCES, null, RESULT));
        assertDoesNotThrow(() -> occurenciesFinder.getOccurencies(SOURCES, WORDS, null));
    }
}