package com.raman.spring.controllers;

import com.raman.spring.dao.BookDAOImplementation;
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
    private BookDAOImplementation bookDAOImplementation;
    @Autowired
    private PersonDAOImplementation personDAOImplementation;
    private Book book1;
    @GetMapping()
    public String getAllBooks(Model model){
        List<Book> allBooks = bookDAOImplementation.getAllBook();
        model.addAttribute("allBooks", allBooks);
        return "book/show_all_books";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model){
        Book book = bookDAOImplementation.getBook(id);
        book1 = book;
        List<Person> allPerson = personDAOImplementation.getAllPersons();
        model.addAttribute("allPerson", allPerson);
        model.addAttribute("book", book);
        model.addAttribute("person", personDAOImplementation.getPerson(book.getPerson_id()));
        return "book/show_book";
    }
    @PatchMapping ("/person")
    public String addPerson(@ModelAttribute("book") Book book, Model model){
        book1.setPerson_id(book.getPerson_id());
        bookDAOImplementation.addPerson(book1);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}/delete/person")
    public String deletePerson(@PathVariable("id") int id){
        bookDAOImplementation.deletePerson(id);
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
        bookDAOImplementation.saveBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") int id, Model model){
        Book book = bookDAOImplementation.getBook(id);
        model.addAttribute("book", book);
        return "book/edit_book";
    }
    @PutMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit_book";
        }
        bookDAOImplementation.updateBook(book, id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAOImplementation.deleteBook(id);
        return "redirect:/books";
    }

}
