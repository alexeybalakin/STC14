package ru.inno.task05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OccurenciesFinder {
    private String filename;


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
