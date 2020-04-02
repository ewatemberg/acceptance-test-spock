package com.example.springboot

import com.example.springboot.service.dto.PersonDTO
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class BaseTest extends Specification {


    private ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper()
    }

    PersonDTO createPerson(def path) {
        def personMock = createObjetoFromJson(path, PersonDTO.class)
        return personMock
    }

    def createObjetoFromJson(String jsonPath, Class<?> clazzObject) {
        objectMapper.readValue(readJson(jsonPath), clazzObject)
    }

    def readJson(String jsonPath) {
        new File(getClass().getResource(jsonPath).toURI()).text
    }
}