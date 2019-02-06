package ru.inno.task05;

public class Main {
    public static void main(String[] args) {
        WordsExtractor wordsExtractor = new WordsExtractor();
        String[] words = wordsExtractor.getWords("/task05/dictionary.txt");
        String[] sources = new String[5];
        for (int i = 0; i < sources.length; i++) {
            sources[i] = "/task05/texts/" + i + ".txt";
        }
        String res = "found.txt";
        ResourceParser parser = new FileResourceParser();
        SentenceWriter writer = new FileSentenceWriter();
        OccurenciesFinder finder = new OccurenciesFinder(parser, writer);
        finder.getOccurencies(sources, words, res);
    }
}
