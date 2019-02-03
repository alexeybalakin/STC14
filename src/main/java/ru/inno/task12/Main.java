package ru.inno.task12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.task12.dao.*;
import ru.inno.task12.entity.Person;
import ru.inno.task12.entity.Subject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        try (Reader reader = new InputStreamReader(ClassLoader.getSystemClassLoader().
                getResourceAsStream("task12/jdbc.properties"))) {
            props.load(reader);
        } catch (IOException e) {
            LOGGER.error("Property file read error", e);
        }
        String url = props.getProperty("url");
        String login = props.getProperty("login");
        String pass = props.getProperty("pass");
        try (Connection connection = DriverManager.getConnection(url, login, pass)) {
            LOGGER.info("Connected to DB: " + !connection.isClosed());

            PersonDAO personDAO = new PersonDAOImpl(connection);
            Person person = new Person();
            person.setName("John Smith");
            person.setBirthDate(System.currentTimeMillis());
            personDAO.createPerson(person);
            LOGGER.info("Получаем список всех person");
            Collection<Person> persons = personDAO.getAllPersons();
            for (Person p : persons) {
                LOGGER.info("Person: " + p);
            }

            person.setName("Willy Dou");
            person.setBirthDate(new Date("2012/12/12").getTime());
            personDAO.updatePerson(person);
            persons = personDAO.getAllPersons();
            LOGGER.info("Получаем список всех person после update");
            for (Person p : persons) {
                LOGGER.info("Person: " + p);
            }
            personDAO.deletePerson(person);
            persons = personDAO.getAllPersons();
            LOGGER.info("Получаем список всех person после delete");
            for (Person p : persons) {
                LOGGER.info("Person: " + p);
            }

            CourseDAO courseDAO = new CourseDAOImpl(connection);
            SubjectDAO subjectDAO = new SubjectDAOImpl(connection);
            Subject subj = subjectDAO.getSubjectById(1);
            persons = courseDAO.getPersonsBySubject(subj);
            LOGGER.info("Получаем список всех person изучающих subject " + subj.getDescription());
            persons.forEach(p -> LOGGER.info("Person: " + p));

            person = personDAO.getPersonById(1);
            Collection<Subject> subjects = courseDAO.getSubjectsByPerson(person);
            LOGGER.info("Получаем список всех subject котрые изучает person " + person.getName());
            subjects.forEach(s -> LOGGER.info("Subject: " + s));
        } catch (SQLException e) {
           LOGGER.error("Ошибка при работе с БД " + e);
        }
        //connection.close();
        //connection  в try resourses
    }
}
