package ru.inno.task05;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Проверяет содержатся ли слова из полученного массива
 * в массиве текстовых ресурсов. Если слово содержится,
 * то предложеие, в котором находится слово добавляется
 * в общий файл с результатами поиска.
 *
 * @author Alexey Balakin
 */
public class OccurenciesFinder {
    /**
     * Имя файла с результатами поиска.
     */
    private String filename;
    private static final Object monitor = new Object();

    /**
     * В многопоточном режиме ищет слова в массиве текстовых ресурсов.
     *
     * @param sources текстовые ресурсы
     * @param words   искомые слова
     * @param res     имя файла с результатами
     */
    public void getOccurencies(String[] sources, String[] words, String res) {
        long startTime = System.currentTimeMillis();
        this.filename = res;
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        Arrays.stream(words).parallel().map(word -> Arrays.stream(sources).
                map(source -> new Thread(new WordChecker(source, word, this))).
                collect(Collectors.toList())).forEach(threads -> {
            threads.forEach(Thread::start);
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        System.out.println("Потрачено: " + ((System.currentTimeMillis() - startTime) / 1000d) + "s");
    }

    /**
     * Записывает в файл найденное предложение.
     *
     * @param content предложение, в котором содержится искомое слово
     */
    public void writeFile(String content) {
        try (Writer writer = new FileWriter(filename, true)) {
            synchronized (monitor) {
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
