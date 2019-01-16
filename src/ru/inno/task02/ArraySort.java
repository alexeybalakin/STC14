package ru.inno.task02;

import java.util.Arrays;
import java.util.Random;

/**
 * Домашнее задание, урок 2
 * Реализовать любой алгоритм сортировки массива
 *
 * @author Alexey Balakin
 */
public class ArraySort {
    public static void main(String[] args) {
        int[] array = getRandomArray(10);
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * Метод для сортировки массива целых чисел, использующий алгоритм пузырька
     *
     * @param array массив, который нужно отсортировать
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * Возвращает массив, размером {@code size} заполненный случайными целыми числами до 100
     *
     * @param size размер массива
     * @return заполненый массив int
     */
    public static int[] getRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
}
