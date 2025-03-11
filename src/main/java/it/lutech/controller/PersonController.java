package it.lutech.controller;

import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import it.lutech.model.dto.PersonDto;
import it.lutech.service.PersonService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person")
public class PersonController {
    @Inject
    private PersonService personService;

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDto findByName(@RestQuery String name) {
        PersonDto result = personService.findByName(name);
        System.out.println(result);
        return result;
    }

    @POST
    @Path("/create")
    public PersonDto save(@Valid PersonDto person) {
        return personService.createPerson(person);
    }

    @DELETE
    @Path("/delete/{name}")
    public void delete(@RestPath String name) {
        personService.deletePersonByName(name);
    }
}
