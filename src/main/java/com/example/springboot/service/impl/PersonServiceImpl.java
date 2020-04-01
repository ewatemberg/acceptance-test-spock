package com.example.springboot.service.impl;

import com.example.springboot.domain.Person;
import com.example.springboot.repository.PersonRepository;
import com.example.springboot.service.PersonService;
import com.example.springboot.service.dto.PersonDTO;
import com.example.springboot.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Person.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        PersonDTO result = personMapper.toDto(person);
        return result;
    }


}
