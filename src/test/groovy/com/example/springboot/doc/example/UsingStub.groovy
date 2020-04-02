package com.example.springboot.doc.example

import com.example.springboot.domain.Person
import com.example.springboot.repository.PersonRepository
import com.example.springboot.service.dto.PersonDTO
import com.example.springboot.service.impl.PersonServiceImpl
import com.example.springboot.service.mapper.PersonMapper
import spock.lang.Specification

class UsingStub extends Specification {

    private PersonServiceImpl service;

    //Aca podemos predefinir una configuración que aplique para todos los test que se escriban debajo.
    def setup() {
    }

    void 'respuesta obtenida cuando se crea (guarda) una persona'() {

        given:
        def personRepository = Stub(PersonRepository) {
            save(_) >> new Person(1, "Emilio")
        }
        def personMapper = Stub(PersonMapper) {
            toDto(_) >> new PersonDTO(1, "Emilio")
            toEntity(_) >> new Person(1, "Emilio")
        }
        service = new PersonServiceImpl(personRepository, personMapper)
        def person = new PersonDTO(null, "Emilio")

        when: "guardo la pesona creada y obtengo sus datos"
        def result = service.save(person)

        then:
        result.id == 1

    }

}
