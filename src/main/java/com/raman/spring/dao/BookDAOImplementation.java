package com.raman.spring.dao;

import com.raman.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImplementation implements BookDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Book> getAllBook() {
        return jdbcTemplate.query("select * from Book order by author_name", new BookMapper());
    }

    @Override
    public void saveBook(Book book) {
        jdbcTemplate.update("insert into Book(book_name, author_name, year_of_production) values(?, ?, ?)",
                book.getBookName(), book.getAuthorName(), book.getYearOfProduction());
    }

    @Override
    public Book getBook(int id) {
        return jdbcTemplate.query("select * from Book where book_id=?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }
    @Override
    public void addPerson(Book book){
        jdbcTemplate.update("update Book set person_id=? where book_id=?", book.getPerson_id(), book.getId());
    }
    @Override
    public void deletePerson(int id){
        jdbcTemplate.update("update Book set person_id=null where book_id=?", id);
    }
    @Override
    public void updateBook(Book book, int id) {
        jdbcTemplate.update("update Book set book_name=?, author_name=?, year_of_production=? where book_id=?",
                book.getBookName(), book.getAuthorName(), book.getYearOfProduction(), id);
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update("delete from Book where book_id=?", id);
    }
}
