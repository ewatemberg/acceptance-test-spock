package com.example.springboot.mock

import com.example.springboot.mock.Mock
import com.example.springboot.service.dto.PersonDTO

class PersonPostMock extends Mock{

    private static String BASE_PATH = "/json/person/post/"

    private static String PATH_VALID_PERSON = "${BASE_PATH}VALID_PERSON.json"

    Map validPerson() {
        buildMapResponse(PATH_VALID_PERSON, PersonDTO)
    }

}
