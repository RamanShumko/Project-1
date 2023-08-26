package com.raman.spring.controllers;

import com.raman.spring.dao.BookDAO;
import com.raman.spring.dao.BookDAOImplementation;
import com.raman.spring.dao.PersonDAO;
import com.raman.spring.dao.PersonDAOImplementation;
import com.raman.spring.models.Book;
import com.raman.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.unbescape.json.JsonEscapeType;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private PersonDAO personDAO;
    private Book temporaryBook;

    @GetMapping()
    public String getAllBooks(Model model){
        model.addAttribute("allBooks", bookDAO.getAllBook());
        return "book/show_all_books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model){
        Book book = bookDAO.getBook(id);
        temporaryBook = book;
        model.addAttribute("allPerson", personDAO.getAllPersons());
        model.addAttribute("book", book);
        model.addAttribute("person", personDAO.getPerson(book.getPerson_id()));
        return "book/show_book";
    }

    @PatchMapping ("/person")
    public String addPerson(@ModelAttribute("book") Book book){
        temporaryBook.setPerson_id(book.getPerson_id());
        bookDAO.addPerson(temporaryBook);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/delete/person")
    public String deletePerson(@PathVariable("id") int id){
        bookDAO.deletePerson(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new_book";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") int id, Model model){
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "book/edit_book";
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit_book";
        }
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

}
