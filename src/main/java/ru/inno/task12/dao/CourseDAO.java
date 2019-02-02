package ru.inno.task12.dao;

import ru.inno.task12.entity.Person;
import ru.inno.task12.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Слой dao для работы с таблицей course
 *
 * @author Alexey Balakin
 */
public interface CourseDAO {

    /**
     * Возвращает коллекцию студентов изучающих указанный
     * учебный предмет
     *
     * @param subject учебный предмет
     * @return коллекция студентов изучающих предмет
     * @throws SQLException
     */
    Collection<Person> getPersonsBySubject(Subject subject) throws SQLException;

    /**
     * Возвращает коллекцию учебных предметов, которые
     * изучает указанный студент
     *
     * @param person студент
     * @return коллекция предметов, которые изучает студент
     * @throws SQLException
     */
    Collection<Subject> getSubjectsByPerson(Person person) throws SQLException;

    /**
     * Связывает студента и учебные предметы, которые
     * он изучает
     *
     * @param person  студент
     * @param subject предмет
     * @throws SQLException
     */
    void linkToCourse(Person person, Subject subject) throws SQLException;

}
