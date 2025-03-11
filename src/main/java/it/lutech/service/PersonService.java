package it.lutech.service;

import it.lutech.mapper.PersonMapper;
import it.lutech.model.dto.PersonDto;
import it.lutech.model.entity.Person;
import it.lutech.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PersonService {

    @Inject
    private PersonRepository personRepository;
    @Inject
    private PersonMapper personMapper;

    public PersonDto findByName(String name) {
        System.out.println("Name: " + name);
        return personMapper.toDto(personRepository.findByName(name));
    }

    public PersonDto createPerson(PersonDto person) {
        Person entity = personMapper.toEntity(person);
        personRepository.persist(entity);
        return personMapper.toDto(entity);
    }

    public void deletePersonByName(String name) {
        personRepository.deleteByName(name);
    }
}