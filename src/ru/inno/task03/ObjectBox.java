package ru.inno.task03;

import java.util.Collection;

public class ObjectBox {
    private Collection<Object> elements;

    public void add(Object element) {
        elements.add(element);
    }

    public void delete(Object element) {
        elements.remove(element);
    }

    public void dump() {
        System.out.println(elements);
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "elements=" + elements +
                '}';
    }
}
