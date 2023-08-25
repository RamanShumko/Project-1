package com.raman.spring.dao;

import com.raman.spring.models.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDAO {
    List<Book> getAllBook();
    void saveBook(Book book);
    Book getBook(int id);
    void addPerson(Book book);
    void deletePerson(int id);

    List<Book> busyBooks(int id);

    void updateBook(Book book, int id);
    void deleteBook(int id);

}
