package com.example.springboot.service.mapper;

import com.example.springboot.domain.Person;
import com.example.springboot.service.dto.PersonDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }

}
