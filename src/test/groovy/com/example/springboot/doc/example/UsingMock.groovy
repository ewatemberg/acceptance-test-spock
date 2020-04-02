package com.example.springboot.doc.example

import com.example.springboot.domain.Person
import com.example.springboot.repository.PersonRepository
import com.example.springboot.service.PersonService
import com.example.springboot.service.dto.PersonDTO
import com.example.springboot.service.impl.PersonServiceImpl
import com.example.springboot.service.mapper.PersonMapper
import spock.lang.Specification

class UsingMock extends Specification {

    private PersonServiceImpl service;

    //Aca podemos predefinir una configuración que aplique para todos los test que se escriban debajo.
    def setup() {
    }

    void 'respuesta obtenida cuando se crea (guarda) una persona'() {

        given:
        def personRepository = Mock(PersonRepository)
        def personMapper = Mock(PersonMapper)
        service = new PersonServiceImpl(personRepository, personMapper)
        def person = new PersonDTO(null,"Emilio")

        when:
        def result = service.save(person)

        then:
        1 * personRepository.save(_) >> new Person(1,"Emilio")
        1 * personMapper.toEntity(_) >> new Person(1,"Emilio")
        1 * personMapper.toDto(_) >> new PersonDTO(1,"Emilio")
        //Que pasa si espero que se ejecute 2 veces un metodo?
        //2 * personMapper.toDto(_) >> new PersonDTO(1,"Emilio")

    }

}
