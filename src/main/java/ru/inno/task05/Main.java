package ru.inno.task05;

public class Main {
    public static void main(String[] args) {
        WordsExtractor wordsExtractor = new WordsExtractor();
        String[] words = wordsExtractor.getWords("c:/temp/task05/dictionary.txt");
        //String[] sources = {"c:/temp/task05/4.txt"};
        String[] sources = new String[5];
        for (int i = 0; i < sources.length; i++) {
            sources[i] = "c:/temp/task05/" + i + ".txt";
        }
        String res = "c:/temp/task05/finded.txt";
        OccurenciesFinder finder = new OccurenciesFinder();
        finder.getOccurencies(sources, words, res);
    }
}
