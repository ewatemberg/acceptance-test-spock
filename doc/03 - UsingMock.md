# 03 - UsingMock

Un mock es un objeto sin comportamiento en el que podremos llamar a un método pero no habrá ningún efecto adicional más allá de devolver el valor por defecto del propio método. La idea es utilizarlo para comprobar que un método se ha ejecutado e incluso con qué parametros lo ha hecho.

```groovy
void 'respuesta obtenida cuando se crea (guarda) una persona'() {

        given:
        def personRepository = Mock(PersonRepository)
        def personMapper = Mock(PersonMapper)
        service = new PersonServiceImpl(personRepository, personMapper)
        def person = new PersonDTO(null,"Emilio")

        when:
        def result = service.save(person)

        then:
        1 * personRepository.save(_) >> new Person(1,"Emilio")
        1 * personMapper.toEntity(_) >> new Person(1,"Emilio")
        1 * personMapper.toDto(_) >> new PersonDTO(1,"Emilio")

        result.id == 1

    }
```

Después, en el bloque then podemos comprobar que el método de mapeo y persistencia de datos, has sido ejecutado solamente una vez en nuestro mock. Si el método no se ejecuta o lo hace más de una vez, el test fallará.

###### Fuente

* [gambeta.com](https://www.genbeta.com/desarrollo/testeando-tus-aplicaciones-java-con-spock-tests-mas-expresivos-faciles-de-leer-y-mantener)
