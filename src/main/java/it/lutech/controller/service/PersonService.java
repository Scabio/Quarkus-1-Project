package it.lutech.controller.service;

import java.sql.Date;

import it.lutech.model.Person;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonService {
    public Person createPerson(String name, Date birthday, Boolean alive){
        Person person = new Person(name, birthday, alive);
        person.persist();
        return person;
    }
}
