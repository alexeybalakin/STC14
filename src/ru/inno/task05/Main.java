package ru.inno.task05;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        WordsExtractor wordsExtractor = new WordsExtractor();
        String[] words = wordsExtractor.getWords("c:/temp/task05/dictionary.txt");
        //String[] sources = {"c:/temp/task05/4.txt"};
        String[] sources = IntStream.range(0, 5).
                mapToObj(i -> "c:/temp/task05/" + i + ".txt").
                toArray(String[]::new);
        String res = "c:/temp/task05/finded.txt";
        OccurenciesFinder finder = new OccurenciesFinder();
        finder.getOccurencies(sources, words, res);
    }
}
