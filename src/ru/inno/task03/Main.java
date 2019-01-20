package ru.inno.task03;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("---MathBox<Integer>---");
        MathBox<Integer> integerMathBox =
                new MathBox<>(new Integer[]{45, 37, 12, 2, 99, 18, 123, 11, 0, 54, 48});
        integerMathBox.dump();
        System.out.println("Sum = " + integerMathBox.summator());
        Set<Integer> divided1 = integerMathBox.splitter(2);
        System.out.println("Divided by 2: " + divided1);
        integerMathBox.delete(11);
        System.out.println("Delete 11");
        integerMathBox.dump();
        integerMathBox.add(33);
        System.out.println("Add 33");
        integerMathBox.dump();

        System.out.println("---MathBox<Double>---");
        MathBox<Double> doubleMathBox =
                new MathBox<>(new Double[]{45.2, 37.7, 12.2, 2.3, 99.9, 18.5, 123d, 11.15, 0d, 54.333, 48d});
        doubleMathBox.dump();
        System.out.println("Sum = " + doubleMathBox.summator());
        Set<Double> divided2 = doubleMathBox.splitter(2.7);
        System.out.println("Divided by 2.7: " + divided2);
        doubleMathBox.delete(12.2);
        System.out.println("Delete 12.2");
        doubleMathBox.dump();
        doubleMathBox.add(33.33);
        System.out.println("Add 33.33");
        doubleMathBox.dump();
    }
}
