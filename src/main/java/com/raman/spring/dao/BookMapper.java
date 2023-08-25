package com.raman.spring.dao;

import com.raman.spring.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book>{
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setBookName(resultSet.getString("book_name"));
        book.setAuthorName(resultSet.getString("author_name"));
        book.setYearOfProduction(resultSet.getInt("year_of_production"));
        book.setPerson_id(resultSet.getInt("person_id"));
        return book;
    }
}
