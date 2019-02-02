package ru.inno.task12.dao;

import ru.inno.task12.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Реализация dao для работы с таблицей subject
 *
 * @author Alexey Balakin
 */
public class SubjectDAOImpl implements SubjectDAO {
    public static final String INSERT_SUBJECT_SQL = "INSERT INTO subject (description) values (?)";
    public static final String DELETE_SUBJECT_SQL = "DELETE FROM subject WHERE id = ?";
    public static final String UPDATE_SUBJECT_SQL = "UPDATE subject SET description = ? WHERE id = ?";
    public static final String SELECT_SUBJECTS_SQL = "SELECT * FROM subject";
    public static final String SELECT_SUBJECT_BY_ID_SQL = "SELECT * FROM subject where id = ?";
    private final Connection connection;

    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subject.getDescription());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            subject.setId(rs.getInt(1));
        }
    }

    @Override
    public void updateSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SQL)) {
            statement.setString(1, subject.getDescription());
            statement.setInt(2, subject.getId());
            statement.execute();
        }
    }

    @Override
    public void deleteSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_SQL)) {
            statement.setInt(1, subject.getId());
            statement.execute();
        }
    }

    @Override
    public Collection<Subject> getAllSubjects() throws SQLException {
        ArrayList<Subject> subjects = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECTS_SQL)) {
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
    public Subject getSubjectById(int id) throws SQLException {
        Subject result = new Subject();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECT_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setDescription(resultSet.getString(2));
            }
        }
        return result;
    }
}
