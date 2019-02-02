package ru.inno.task05b;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Релизация своего Thread Pool. На входе будет получать класс,
 * реализующий Runnable и запускать его метод run в отдельном
 * потоке при этом количество потоков ограничено (задается при
 * запуске пула потоков). Если число работающих потоков больше
 * ограничения, новые объекты ожидают завершения работы выполняющихся
 * потоков.
 *
 * @author Alexey Balakin
 */
public class MyThreadPool {
    /**
     * Очередь для задач на выполнение в пуле
     */
    private Queue<Runnable> tasks;

    /**
     * Управляет потоками в пуле
     */
    private Starter starter;

    /**
     * @param size размер пула потоков
     */
    public MyThreadPool(int size) {
        this.tasks = new ConcurrentLinkedQueue<>();
        starter = new Starter(size, tasks);
        starter.start();
    }

    /**
     * Добавляет в пул новую задачу
     *
     * @param task задача для выполняния в пуле
     */
    public void execute(Runnable task) {
        tasks.add(task);
    }


    /**
     * Завершает работу пула
     */
    public void shutdown() {
        starter.interrupt();
    }

    /**
     * Проверяет что в пуле не осталось работающих потоков
     * и в очереди не осталось задач на выполнение
     *
     * @return <code>true</code> если все потоки закончили
     * работу и в очерди нет задач на выполнение;
     * <code>false</code>  противном случае
     */
    public boolean isFinished() {
        return starter.getThreadsSize() == 0 && starter.getTasksSize() == 0;
    }
}
