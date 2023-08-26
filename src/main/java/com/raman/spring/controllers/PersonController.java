package com.raman.spring.controllers;

import com.raman.spring.dao.BookDAOImplementation;
import com.raman.spring.dao.PersonDAOImplementation;
import com.raman.spring.models.Book;
import com.raman.spring.models.Person;
import com.raman.spring.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDAOImplementation personDAOImplementation;
    @Autowired
    private PersonValidator personValidator;

    @GetMapping()
    public String showAllPersons(Model model){
        List<Person> allPersons = personDAOImplementation.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        return "person/show_all_persons";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        Person person = personDAOImplementation.getPerson(id);
        model.addAttribute("person", person);
        model.addAttribute("busyBooks", personDAOImplementation.busyBooks(id));
        return "person/show_person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "person/new_person";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/new_person";
        }
        personDAOImplementation.savePerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String updatePerson(@PathVariable("id") int id, Model model){
        Person person = personDAOImplementation.getPerson(id);
        model.addAttribute("person", person);
        return "person/edit_person";
    }

    @PutMapping("/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/edit_person";
        }
        personDAOImplementation.updatePerson(person, id);
        return "redirect:/persons";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAOImplementation.deletePerson(id);
        return "redirect:/persons";
    }


}
