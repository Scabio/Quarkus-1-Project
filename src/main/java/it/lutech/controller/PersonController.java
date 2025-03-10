package it.lutech.controller;

import java.util.Date;

import org.jboss.resteasy.reactive.RestQuery;

import it.lutech.controller.service.PersonService;
import it.lutech.model.dto.PersonDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/person")
public class PersonController {
    @Inject
    private PersonService personService;

    @POST
    @Path("/create")
    public PersonDto save(@RestQuery String name, @RestQuery Date birthady, @RestQuery Boolean alive) {
        final PersonDto personDto = new PersonDto(null, name, birthady, alive);
        return personService.createPerson(personDto);
    }
}
