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
    @Mock private ResourceParser parser;
    @Mock private SentenceWriter writer;
    private OccurenciesFinder occurenciesFinder;
    private String[] sentences = {"Doing Test", "Let's do it", "Test test test"};
    private String[] words = {"Test"};
    private String result = "res";
    private String[] sources = {"someFile.txt", "someFile2.txt", "someFile3.txt"};

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        occurenciesFinder = new OccurenciesFinder(parser, writer);
    }

    @Test
    void getOccurencies() {
        when(parser.getSentences(sources[0])).thenReturn(new HashSet<>(Arrays.asList(sentences)));
        occurenciesFinder.getOccurencies(sources, words, result);
        verify(writer).writeSentence("Doing Test",result);
        verify(writer).writeSentence("Test test test",result);
    }
}