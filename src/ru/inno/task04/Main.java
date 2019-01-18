package ru.inno.task04;

public class Main {
    static String[] words = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing",
            "elit", "morbi", "non", "justo", "feugiat", "pellentesque", "eros", "in", "lobortis"};
    public static void main(String[] args) {
        RandomWordsGenerator wordsGenerator = new RandomWordsGenerator();
        TextFileGenerator textFileGenerator = new TextFileGenerator();
        //textFileGenerator.getFiles("c:/temp/", 5, 4,words, 10);
        textFileGenerator.getFiles("c:/temp/", 5, 7,wordsGenerator.getWords(), 10);
    }
}
