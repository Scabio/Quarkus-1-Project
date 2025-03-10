package it.lutech.controller;

import java.sql.Date;

import it.lutech.controller.service.PersonService;
import it.lutech.model.Person;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/person")
public class PersonController {
    @Inject
    private PersonService personService;

    public Person save(String name, Date birthady, Boolean alive){
        return personService.createPerson(name, birthady, alive);
    }
}
