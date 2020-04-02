# 04 - UsingStub

Si en lugar de simplemente querer asegurarnos de que un método se ha ejecutado necesitamos que éste devuelva un determinado valor o lance una excepción, lo que haremos será un stub.

Un caso de uso para un stub puede ser que necesitemos obtener algún valor de la base de datos para continuar con nuestro test. Ejemplo:

```groovy
    void 'respuesta obtenida cuando se crea (guarda) una persona'() {

        given:
        def personRepository = Stub(PersonRepository) {
            save(_) >> new Person(1, "Emilio")
        }
        def personMapper = Stub(PersonMapper) {
            toDto(_) >> new PersonDTO(1, "Emilio")
            toEntity(_) >> new Person(1, "Emilio")
        }
        service = new PersonServiceImpl(personRepository, personMapper)
        def person = new PersonDTO(null, "Emilio")

        when: "guardo la pesona creada y obtengo sus datos"
        def result = service.save(person)

        then:
        result.id == 1

    }
```

###### Fuente

* [gambeta.com](https://www.genbeta.com/desarrollo/testeando-tus-aplicaciones-java-con-spock-tests-mas-expresivos-faciles-de-leer-y-mantener)
