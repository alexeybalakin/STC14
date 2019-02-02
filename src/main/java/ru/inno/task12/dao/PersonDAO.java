package ru.inno.task12.dao;

import ru.inno.task12.entity.Person;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Слой dao для работы с таблицей студетов
 *
 * @author Alexey Balakin
 */
public interface PersonDAO {
    /**
     * Создает в таблице person студента
     *
     * @param person студент
     * @throws SQLException
     */
    void createPerson(Person person) throws SQLException;

    /**
     * Обновляет данные студента в таблице person
     *
     * @param person студент
     * @throws SQLException
     */
    void updatePerson(Person person) throws SQLException;

    /**
     * Удаляет студента из таблицы person
     *
     * @param person студент
     * @throws SQLException
     */
    void deletePerson(Person person) throws SQLException;

    /**
     * Возвращает всех студентов из таблицы person
     *
     * @return коллекция студентов
     * @throws SQLException
     */
    Collection<Person> getAllPersons() throws SQLException;

    /**
     * Возвращает студента из таблицы person
     * по номеру id
     *
     * @param id идентификатор студента
     * @return студент
     * @throws SQLException
     */
    Person getPersonById(int id) throws SQLException;
}
