package com.example.springboot.resource;

import com.example.springboot.config.ApiVersion;
import com.example.springboot.exception.ManagerError;
import com.example.springboot.service.PersonService;
import com.example.springboot.service.dto.PersonDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    public static final String PATH = "/person";

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    /**
     * POST  /person : Create a new antonym.
     *
     * @param personDTO the personDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personDTO, or with status 400 (Bad Request) if the antonym has already an ID
     * @throws ApiException if the id exist
     */
    @ApiOperation(value = "Create a person")
    @PostMapping(value = ApiVersion.V1 + PATH)
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody @ApiParam(name = "person",
            value = "This objects represents a person", required = true) PersonDTO personDTO) {
        log.debug("REST request to save Person : {}", personDTO);
        if (personDTO.getId() != null) {
            throw ManagerError.error("A new person cannot already have an ID ", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        PersonDTO result = personService.save(personDTO);
        return ResponseEntity.ok().body(result);
    }
}