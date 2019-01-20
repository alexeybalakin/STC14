package ru.inno.task04;

public class Main {

    public static void main(String[] args) {
        RandomWordsGenerator wordsGenerator = new RandomWordsGenerator();
        TextFileGenerator textFileGenerator = new TextFileGenerator();
        textFileGenerator.getFiles("c:/temp/", 5, 7,wordsGenerator.getWords(), 10);
    }
}
