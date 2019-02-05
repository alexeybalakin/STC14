package ru.inno.task05;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileSentenceWriter implements SentenceWriter {

    /**
     * Записывает в файл предложение.
     *
     * @param sentence предложение для записи
     * @param resource имя ресурса для записи
     */
    @Override
    public void writeSentence(String sentence, String resource) {
        sentence = sentence.trim() + System.lineSeparator();
        try (Writer writer = new FileWriter(resource, true)) {
            synchronized (FileSentenceWriter.class) {
                writer.write(sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
