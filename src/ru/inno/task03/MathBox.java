package ru.inno.task03;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Хранит отсортированную коллекцию наследников Number
 *
 * @author Alexey Balakin
 */
public class MathBox<T extends Number> extends ObjectBox<T>{
    /**
     * Заполняет внутреннюю коллекцию числами из
     * полученного массива
     *
     * @param array массив исходных чисел
     */
    public MathBox(T[] array) {
        Collections.addAll(elements, array);
    }

    /**
     * Возвращает сумму всех элементов коллекции
     *
     * @return сумма элементов коллекции
     */
    public Number summator() {
        BigDecimal sum = new BigDecimal(0);
        for (T element : elements) {
            sum = sum.add(new BigDecimal(element.toString()));
        }
        return  sum;
    }

    /**
     * Возвращает коллекцию, поделенную на полученный делитель
     *
     * @param divider делитель
     * @return коллекция наследников Number
     */
    public  Set<T> splitter(T divider) {
        Set<T> result = new TreeSet<>();
        for (T element : elements) {
            result.add((T)new BigDecimal(element.toString()).divide(new BigDecimal(divider.toString()),
                    RoundingMode.CEILING));
        }
        return result;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "elements=" + elements +
                '}';
    }
}
