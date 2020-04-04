package com.example.springboot.service;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.service.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

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

    /**
     * Get all the people.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PersonDTO> findAll(@PageableDefault(size = 200) Pageable pageable);

    /**
     * Get the "id" person.
     *
     * @param id the id of the entity
     * @return the entity
     * @throws NotFoundException if person not exist
     */
    PersonDTO findOne(Long id) throws NotFoundException;

    /**
     * Delete the "id" person.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
