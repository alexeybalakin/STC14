package ru.inno.task12.dao;

import ru.inno.task12.entity.Person;
import ru.inno.task12.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Реализация dao для работы с таблицей course
 *
 * @author Alexey Balakin
 */
public class CourseDAOImpl implements CourseDAO {
    public static final String INSERT_COURSE_SQL = "INSERT INTO course values (?, ?, ?)";
    public static final String SELECT_PERSONS_BY_SUBJECT_SQL = "SELECT id, name, birth_date FROM person" +
            " join course on id = person_id where subject_id = ?";
    public static final String SELECT_SUBJECTS_BY_PERSONS_SQL = "SELECT id, description FROM subject" +
            " join course on id = subject_id where person_id= ?";
    private final Connection connection;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> getPersonsBySubject(Subject subject) throws SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSONS_BY_SUBJECT_SQL)) {
            statement.setInt(1, subject.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setBirthDate(resultSet.getDate(3).getTime());
                persons.add(person);
            }
        }
        return persons;
    }

    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) throws SQLException {
        ArrayList<Subject> subjects = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECTS_BY_PERSONS_SQL)) {
            statement.setInt(1, person.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setDescription(resultSet.getString(2));
                subjects.add(subject);
            }
        }
        return subjects;
    }

    @Override
    public void linkToCourse(Person person, Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COURSE_SQL)) {
            statement.setInt(1, person.getId());
            statement.setInt(2, subject.getId());
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.execute();
        }
    }
}
