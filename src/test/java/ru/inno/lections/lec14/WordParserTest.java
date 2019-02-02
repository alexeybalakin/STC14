package ru.inno.lections.lec14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {
    private WordParser parser;
    private ResourseLoader loader;

    @BeforeEach
    void setUp(){
        ResourseLoader loaderOld = new ResourseLoader() {
            @Override
            public void close() throws Exception {
                this.close();
            }

            @Override
            public List<String> getStringFromURL(String url) {
                if("1".equals(url)){
                    return null;
                }
                return Collections.emptyList();
            }
        };

        loader = Mockito.mock(ResourseLoader.class);
        parser = new WordParser(loader);
    }

    @Test
    void firstWord() {
        Mockito.when(loader.getStringFromURL(Mockito.anyString())).thenReturn(Arrays.asList("", "", "word1"));
        assertEquals("word1", parser.firstWord(""));
        Mockito.when(loader.getStringFromURL(Mockito.anyString())).thenReturn(null);
//        assertDoesNotThrow(() -> parser.firstWord(""));

    }
}