package it.lutech.model;

import java.sql.Date;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends PanacheEntity{
    private String name;
    private Date birthday;
    private Boolean alive;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlve(){
        return list("alive", true);
    }

    public static void deleteByName(String name){
        delete("name", name);
    }
}
