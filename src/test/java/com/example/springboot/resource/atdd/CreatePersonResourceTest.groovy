package com.example.springboot.resource.atdd

import com.example.springboot.mock.PersonPostMock
import com.example.springboot.resource.PersonResource
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired

class CreatePersonResourceTest extends com.example.springboot.config.AcceptanceSpecification {

    @Autowired
    private PersonResource controller
    private BeanFactory beanFactory = Mock(BeanFactory)
    private PersonPostMock mocks = new PersonPostMock()

    def setup() {
        //StaticContextHolder.CONTEXT = beanFactory
    }

    def "Se hace un POST a personas con datos de una persona válida"() {
        given: "El nombre de la persona creada"
        def namePerson =  "Emilio"

        when: "Se crea una persona"
        def postPersonResponse = doPostWith(mocks.validPerson().json)

        then: "Se obtienen los datos de la persona con el id asignado"
        postPersonResponse.status == 200
        postPersonResponse.body.name == namePerson

    }

}
