package ru.inno.task12.dao;

import ru.inno.task12.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Реализация dao для работы с таблицей person
 *
 * @author Alexey Balakin
 */
public class PersonDAOImpl implements PersonDAO {
    public static final String INSERT_PERSON_SQL = "INSERT INTO person (name, birth_date) values (?, ?)";
    public static final String DELETE_PERSON_SQL = "DELETE FROM person WHERE id = ?";
    public static final String UPDATE_PERSON_SQL = "UPDATE person SET name = ?, birth_date = ? WHERE id = ?";
    public static final String SELECT_PERSONS_SQL = "SELECT * FROM person";
    public static final String SELECT_PERSON_BY_ID_SQL = "SELECT * FROM person where id = ?";
    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createPerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            person.setId(rs.getInt(1));
        }
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_SQL)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.setInt(3, person.getId());
            statement.execute();
        }
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PERSON_SQL)) {
            statement.setInt(1, person.getId());
            statement.execute();
        }
    }

    @Override
    public Collection<Person> getAllPersons() throws SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSONS_SQL)) {
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
    public Person getPersonById(int id) throws SQLException {
        Person result = new Person();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                result.setBirthDate(resultSet.getDate(3).getTime());
            }
        }
        return result;
    }
}
