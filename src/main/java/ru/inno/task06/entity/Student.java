package ru.inno.task06.entity;

/**
 * Сущность Student с полями: имя, фамилия,
 * возраст, средняя оценка, пол.
 *
 * @author Alexey Balakin
 */
public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private double averageMark;
    private boolean isMale;

    public Student(String firstName, String lastName, int age, double averageMark, boolean isMale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.averageMark = averageMark;
        this.isMale = isMale;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", averageMark=" + averageMark +
                ", isMale=" + isMale +
                '}';
    }
}
