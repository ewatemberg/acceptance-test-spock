package com.example.springboot.service;

import com.example.springboot.service.dto.PersonDTO;

/**
 * Service Interface for managing Antonym.
 */
public interface PersonService {

    /**
     * Save a person.
     *
     * @param personDTO the entity to save
     * @return the persisted entity
     */
    PersonDTO save(PersonDTO personDTO);
}
