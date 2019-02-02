package ru.inno.task12.dao;

import ru.inno.task12.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Слой dao для работы с таблицей subject
 *
 * @author Alexey Balakin
 */
public interface SubjectDAO {
    /**
     * Создает в таблице subject учебный предмет
     *
     * @param subject учебный предмет
     * @throws SQLException
     */
    void createSubject(Subject subject) throws SQLException;

    /**
     * Обновляет данные учебного предмета в таблице subject
     *
     * @param subject учебный предмет
     * @throws SQLException
     */
    void updateSubject(Subject subject) throws SQLException;

    /**
     * Удаляет учебный предмет из таблицы subject
     *
     * @param subject учебный предмет
     * @throws SQLException
     */
    void deleteSubject(Subject subject) throws SQLException;

    /**
     * Возвращает все предметы из таблицы subject
     *
     * @return коллекция учебных предметов
     * @throws SQLException
     */
    Collection<Subject> getAllSubjects() throws SQLException;

    /**
     * Возвращает учебный предмет из таблицы subject
     * по номеру id
     *
     * @param id идентификатор учебного предмета
     * @return учебный предмет
     * @throws SQLException
     */
    Subject getSubjectById(int id) throws SQLException;
}
