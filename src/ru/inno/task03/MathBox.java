package ru.inno.task03;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class MathBox<T extends Number> {
    private Set<T> elements;
    private int id = new Random().nextInt(Integer.MAX_VALUE);

    public MathBox(T[] array) {
        elements = new TreeSet<>(Arrays.asList(array));
    }

    public Number summator() {
        BigDecimal sum = new BigDecimal(0);
        for (Number element : elements) {
            sum = sum.add(new BigDecimal(element.toString()));
        }
        return  sum;
    }

    public Set<T> splitter(T divider) {
        Set<T> result = new TreeSet<>();
        for (Number element : elements) {
            result.add((T)new BigDecimal(element.toString()).divide(new BigDecimal(divider.toString()),
                    RoundingMode.CEILING));
        }
        return result;
    }

    public void delete(T element) {
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
