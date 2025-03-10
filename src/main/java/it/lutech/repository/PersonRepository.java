package it.lutech.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.lutech.model.entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PersonRepository implements PanacheRepository<Person> {

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAlive(){
        return list("alive", true);
    }

    public void deleteByName(String name){
        delete("name", name);
    }

}
