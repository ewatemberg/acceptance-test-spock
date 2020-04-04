package com.example.springboot.service.impl;

import com.example.springboot.domain.Person;
import com.example.springboot.exception.NotFoundException;
import com.example.springboot.repository.PersonRepository;
import com.example.springboot.service.PersonService;
import com.example.springboot.service.dto.PersonDTO;
import com.example.springboot.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Antonyms");
        return personRepository.findAll(pageable)
                .map(personMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDTO findOne(Long id) throws NotFoundException {
        log.debug("Request to get Person : {}", id);
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        return personMapper.toDto(person);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }


}
