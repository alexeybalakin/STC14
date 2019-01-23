package ru.inno.task05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        List<Thread> threadList = new ArrayList<>();
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        for (String word : words) {
            for (String source : sources) {
                Thread thread = new Thread(new WordChecker(source, word, this));
                thread.start();
                threadList.add(thread);
            }
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + " finished");
        }
        System.out.println("Потрачено: " + ((System.currentTimeMillis() - startTime) / 1000d) + "s");
    }

    /**
     * Записывает в файл найденное предложение.
     *
     * @param content предложение, в котором содержится искомое слово
     */
    public void writeFile(String content) {
        try (Writer writer = new FileWriter(filename, true)) {
            synchronized (this) {
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
