package ru.inno.task10;

import java.util.*;

/**
 * Демонстрирует утечку памяти. Создает много объектов,
 * периодически частично удаляя их, чтобы GC имел возможность
 * очищать часть памяти. Через некоторое время программа должна
 * завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 * Для ускорения получеия исключения можно добавить флаг -Xmx500m
 *
 * @author Alexey Balakin
 */

public class MemoryTest {
    private static final int LOOP_COUNT = 100_000_000;

    /**
     * Создает много объектов, периодически частично удаляя их,
     * чтобы GC имел возможность очищать часть памяти.
     */
    public void leakTest() {
        List<String> list = new ArrayList<>();
        Random random = new Random();
        String garbage = "";
        for (int i = 0; i < LOOP_COUNT; i++) {
            String longString = "Some string dfdgdgdrtthrtewrtertrhthhtyjtyjfoeepjggporgptrklfffkgjhgl;hj" +
                    "orjtiohrtjhorjthjlfjghlgfjhorthojojhofjj;fjhiortjhorjhoj;dfhjorthjf;jhiorjhhjd;fjh" +
                    "ti;jhiojtlhjojhorithojrtoihojroihjtorthjhiohjojhriothj" + random.nextInt();
            for (int j = 0; j < 100; j++) {
                garbage += longString;
            }
            list.add(garbage);
            if (list.size() == 100) {
                for (int j = 99; j >= 0; j--) {
                    list.remove(j);
                }
            }
        }
    }
}
