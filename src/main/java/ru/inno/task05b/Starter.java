package ru.inno.task05b;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * Управляет потоками в пуле
 *
 * @author Alexey Balakin
 */
public class Starter extends Thread {
    private List<Thread> threads;
    private Queue<Runnable> tasks;
    private int size;

    /**
     * @param size  размер пула потокв
     * @param tasks очередь задач на выполнение в пуле
     */
    public Starter(int size, Queue<Runnable> tasks) {
        this.size = size;
        this.threads = new ArrayList<>(size);
        this.tasks = tasks;
    }

    /**
     * Запускает на выполение задачи из очереди
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            if (threads.size() < size && !tasks.isEmpty()) {
                Thread thread = new Thread(tasks.poll());
                thread.start();
                threads.add(thread);
            }
            removeFinishedThreads(threads);
        }
    }

    public int getThreadsSize() {
        return threads.size();
    }

    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Удаляет из пула завершенные потоки
     *
     * @param threads список потоков пула
     */
    private void removeFinishedThreads(List<Thread> threads) {
        if (threads != null && !threads.isEmpty()) {
            threads.removeIf(thread -> !thread.isAlive());
        }
    }
}
