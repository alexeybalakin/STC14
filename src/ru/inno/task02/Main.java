package ru.inno.task02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        Integer[] array = bubbleSort.getRandomArray(10);
        System.out.println(Arrays.toString(array));
        bubbleSort.arraySort(array);
        System.out.println(Arrays.toString(array));
    }
}
