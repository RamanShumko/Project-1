package com.raman.spring.validators;


import com.raman.spring.dao.PersonDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.raman.spring.models.Person;
@Component
public class PersonValidator implements Validator {
    @Autowired
    private PersonDAOImplementation personDAOImplementation;
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if((personDAOImplementation.getPerson(person.getFullName()) != null)){
            errors.rejectValue("fullName", "", "This name is already taken");
        }
    }
}
