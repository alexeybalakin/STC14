package ru.inno.task05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (String word : words) {
            for (String source : sources) {
                service.execute(new Thread(new WordChecker(source, word, this)));
            }
        }
        service.shutdown();
        while (!service.isTerminated()) ;
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
