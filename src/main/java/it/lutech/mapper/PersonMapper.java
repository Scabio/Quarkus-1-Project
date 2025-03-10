package it.lutech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import it.lutech.model.dto.PersonDto;
import it.lutech.model.entity.Person;

@Mapper(componentModel = ComponentModel.JAKARTA_CDI)
public interface PersonMapper {
    PersonDto toDto(Person person);

    Person toEntity(PersonDto person);
}
