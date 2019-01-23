package ru.inno.task06;

import ru.inno.task06.entity.Student;

public class Main {

    public static final String FILE = "c:/temp/task06/file.xml";

    public static void main(String[] args) {
        Student student = new Student("Ivan", "Petrov", 25, 4.5, true);
        System.out.println("Object for XML: " + student);
        Serializator serializator = new Serializator();
        serializator.serialize(student, FILE);
        Student studentFromXml = (Student) serializator.deSerialize(FILE);
        System.out.println("Object from XML: " + studentFromXml);
    }
}

