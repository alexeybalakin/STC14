package ru.inno.task03;

import java.util.*;

public class MathBox {
    private Set<Integer> elements;
    private int id = new Random().nextInt(Integer.MAX_VALUE);

    public MathBox(Integer[] array) {
        elements = new TreeSet<>(Arrays.asList(array));
    }

    public Integer summator() {
        Integer sum = new Integer(0);
        for (Integer element : elements) {
            sum += element;
        }
        return sum;
    }

    public Set<Integer> splitter(int divider) {
        Set<Integer> result = new TreeSet<>();
        for (Integer element : elements) {
            result.add((element / divider));
        }
        return result;
    }

    public void delete(Integer element) {
        elements.remove(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return id == mathBox.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "elements=" + elements +
                '}';
    }
}
