package org.rinzler.spring.util;

import org.rinzler.spring.DAO.PersonDAO;
import org.rinzler.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;


    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getEmail()) !=null){
            errors.rejectValue("email","","email is already busy");
        }

    }
}
