package ru.inno.task05;

/**
 * Записывает полученную строку в указанный ресурс
 *
 * @author Alexey Balakin
 */
public interface SentenceWriter {
    /**
     * @param sentence строка для записи
     * @param resource ресурс для записи
     */
    void writeSentence(String sentence, String resource);
}
