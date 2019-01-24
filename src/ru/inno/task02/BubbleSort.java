package ru.inno.task02;

import java.util.Random;

/**
 * Домашнее задание, урок 2
 * Реализовать любой алгоритм сортировки массива
 *
 * @author Alexey Balakin
 */
public class BubbleSort implements ArraySort {
    /**
     * Метод для сортировки массива целых чисел, использующий алгоритм пузырька
     *
     * @param array массив, который нужно отсортировать
     */
    @Override
    public void arraySort(Integer[] array) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    if (array[j] != null && (array[j + 1] == null || array[j] > array[j + 1])) {
                        Integer temp = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * Возвращает массив, размером {@code size} заполненный случайными целыми числами до 100
     *
     * @param size размер массива
     * @return заполненый массив
     */
    public Integer[] getRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
}
