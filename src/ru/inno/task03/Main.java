package ru.inno.task03;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MathBox mathBox = new MathBox(new Integer[]{45, 37, 12, 2, 99, 18, 123, 11, 0, 54, 48});
        System.out.println(mathBox);
        System.out.println("Sum = " + mathBox.summator());
        Set<Integer> divided = mathBox.splitter(2);
        System.out.println(divided);
        mathBox.delete(11);
        System.out.println(mathBox);
    }
}
