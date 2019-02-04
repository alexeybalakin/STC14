package ru.inno.task05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.*;

/**
 * Проверяет содержатся ли слова из полученного массива
 * в массиве текстовых ресурсов. Для поиска в каждом
 * ресурсе запускает отдельный поток.
 *
 * @author Alexey Balakin
 */
public class OccurenciesFinder {
    /**
     * Имя файла с результатами поиска.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OccurenciesFinder.class);

    /**
     * В многопоточном режиме ищет слова в массиве текстовых ресурсов.
     *
     * @param sources текстовые ресурсы
     * @param words   искомые слова
     * @param res     имя файла с результатами
     */
    public void getOccurencies(String[] sources, String[] words, String res) {
        long startTime = System.currentTimeMillis();
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (String word : words) {
            for (String source : sources) {
                service.execute(new Thread(new WordChecker(source, word, res)));
            }
        }
        service.shutdown();
        while (!service.isTerminated()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                LOGGER.error("thread was interrupted " + e);
            }
        };
        LOGGER.info("Потрачено: " + ((System.currentTimeMillis() - startTime) / 1000d) + "s");
    }
}