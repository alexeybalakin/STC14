package ru.inno.task03;

import java.util.Collection;
import java.util.Random;
import java.util.TreeSet;

/**
 * Хранит коллекцию обьектов
 *
 * @author Alexey Balakin
 */
public class ObjectBox<T> {
    /**
     * Уникальный индентификатор обьекта, используется
     * в hashCode и equals
     */
    private int id = new Random().nextInt(Integer.MAX_VALUE);

    protected Collection<T> elements = new TreeSet<>();

    /**
     * Добавляет в коллекцию полученный объект
     *
     * @param element обьект для добавления в коллекцию
     */
    public void add(T element) {
        elements.add(element);
    }

    /**
     * Удаляет из коллекции полученный объект
     *
     * @param element объект для удаления из коллекции
     */
    public void delete(T element) {
        elements.remove(element);
    }

    /**
     * Выводит не печать все элементы коллекции
     */
    public void dump() {
        System.out.println(toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return id == objectBox.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "elements=" + elements +
                '}';
    }
}
