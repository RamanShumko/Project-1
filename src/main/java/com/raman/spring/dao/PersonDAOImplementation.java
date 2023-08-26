package com.raman.spring.dao;

import com.raman.spring.models.Book;
import com.raman.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImplementation implements PersonDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Person> getAllPersons() {
        List<Person> allPersons = jdbcTemplate.query("select * from Person order by full_name", new PersonMapper());
        return allPersons;
    }

    @Override
    public void savePerson(Person person) {
        jdbcTemplate.update("insert into Person(full_name, year_of_birth) values(?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    @Override
    public Person getPerson(int id) {
        Person person = jdbcTemplate.query("select * from Person where person_id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
        return person;
    }
    public Person getPerson(String fullName) {
        Person person = jdbcTemplate.query("select * from Person where full_name=?",
                new Object[]{fullName}, new PersonMapper()).stream().findAny().orElse(null);
        return person;
    }
    @Override
    public List<Book> busyBooks(int id) {
        return jdbcTemplate.query("select * from Book where person_id=?", new Object[]{id}, new BookMapper());
    }

    @Override
    public void updatePerson(Person person, int id) {
        jdbcTemplate.update("update Person set full_name=?, year_of_birth=? where person_id=?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    @Override
    public void deletePerson(int id) {
        jdbcTemplate.update("delete from Person where person_id=?", id);
    }
}
