package com.example.springboot.resource;

import com.example.springboot.config.ApiVersion;
import com.example.springboot.exception.ManagerError;
import com.example.springboot.exception.NotFoundException;
import com.example.springboot.resource.util.PaginationUtil;
import com.example.springboot.service.PersonService;
import com.example.springboot.service.dto.PersonDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonResource extends BaseResource{

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    public static final String PATH = "/people";
    public static final String BASE_PATH = "/api" + PATH;

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    /**
     * POST  /people : Create a new antonym.
     *
     * @param personDTO the personDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personDTO, or with status 400 (Bad Request) if the antonym has already an ID
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
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * PUT  /people : Updates an existing person.
     *
     * @param personDTO the personDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personDTO,
     * or with status 400 (Bad Request) if the personDTO is not valid,
     * or with status 500 (Internal Server Error) if the personDTO couldn't be updated
     */
    @ApiOperation(value = "Update a person")
    @PutMapping(value = ApiVersion.V1 + PATH)
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody @ApiParam(name = "person",
            value = "This objects represents a person", required = true) PersonDTO personDTO) {
        log.debug("REST request to update Person : {}", personDTO);
        if (personDTO.getId() == null) {
            return createPerson(personDTO);
        }
        PersonDTO result = personService.save(personDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * GET  /people : get all the people.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of people in body
     */
    @ApiOperation(value = "Get all people")
    @GetMapping(value = ApiVersion.V1 + PATH)
    public ResponseEntity<List<PersonDTO>> getAllPeople(@PageableDefault(size = 200) Pageable pageable) {
        log.debug("REST request to get a page of People");
        Page<PersonDTO> page = personService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, BASE_PATH);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /people/:id : get the "id" person.
     *
     * @param id the id of the personDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personDTO, or with status 404 (Not Found)
     */
    @ApiOperation(value = "Get a person by id")
    @GetMapping(value = ApiVersion.V1 + PATH +"/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        log.debug("REST request to get Person : {}", id);
        try {
            PersonDTO result = personService.findOne(id);
            return ResponseEntity.ok()
                    .body(result);
        }catch (NotFoundException nfe){
            throw notFound(id.toString(), PATH);
        }
    }

    /**
     * DELETE  /people/:id : delete the "id" person.
     *
     * @param id the id of the antonymDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation(value = "Delete a person by id")
    @DeleteMapping(value = ApiVersion.V1 + PATH +"/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}