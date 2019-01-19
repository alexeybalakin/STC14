package ru.inno.task04;

import java.io.*;
import java.util.Random;

/**
 * Создает текстовые файлы, заполненные случайными предложениямм
 * на основе полученного массива слов
 *
 * @author Alexey Balakin
 */
public class TextFileGenerator {
    private Random random = new Random();
    /**
     * Массив слов из которых генерируется текст
     */
    private String[] words;

    /**
     * Количество абзацев в создаваемых файлах
     */
    private int size;

    /**
     * Вероятность вхождения одного из слов массива
     * в следующее предложение (1/probability).
     */
    private int probability;

    /**
     * Создает {@code n} текстовых файлов размером {@code size} в каталоге {@code path}
     *
     * @param path каталог, в котором будут созданы файлы
     * @param n количество созданных файлов
     * @param size количество абзацев в каждом файле
     * @param words слова из которых будет генерироваться текст
     * @param probability вероятность вхождения одного из слов массива в следующее предложение (1/probability)
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {
        this.words = words;
        this.size = size;
        this.probability = probability;
        for (int i = 0; i < n; i++) {
            String fileName = path + "file" + i + ".txt";
            writeFile(fileName, getText());
        }
    }

    /**
     * Возвращает весь сгенерированный текст
     */
    private String getText(){
        String text = "";
        for (int i = 0; i < size; i++) {
            text += getParagraph();
        }
        return text;
    }

    /**
     * Возвращает один абзац сгенерированного текста
     */
    private String getParagraph(){
        String paragraph = "";
        int sentenceAmount = random.nextInt(20) + 1;
        for (int i = 0; i < sentenceAmount; i++) {
            paragraph += getSentence();
        }
        paragraph += "\r\n";
        return paragraph;
    }

    /**
     * Возвращает одно предложение сгенерированного текста
     */
    private String getSentence(){
        String sentence = "";
        int wordsAmount = random.nextInt(15) + 1;
        for (int i = 0; i < wordsAmount; i++) {
            String word = "";
            do {
                word = words[random.nextInt(words.length)];
            } while(random.nextInt(probability) != 0);
            if(i == 0){
                word = firstUpperCase(word);
            }
            sentence += word;
            if(i < wordsAmount - 1){
                if (random.nextInt(probability) == 0){
                    sentence += ",";
                }
                sentence += " ";
            }
        }
        sentence += endOfSentence();
        return sentence;
    }

    /**
     * Возвращает знак (.|,|?) которым будет заканчиваться предложение
     */
    private String endOfSentence() {
        char[] endOfSentence = {'.', '!', '?'};
        return endOfSentence[random.nextInt(3)] + " ";
    }

    /**
     * Приводит к верхнему регистру первую букву переданного слова
     */
    private String firstUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Создает файл и записывает в него полученный текст
     */
    private void writeFile(String fileName, String content) {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}