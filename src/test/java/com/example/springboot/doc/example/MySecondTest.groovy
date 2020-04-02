package com.example.springboot.doc.example

import spock.lang.Specification

class MySecondTest extends Specification {

    void 'El nombre del primer lenguaje es Groovy'() {
        given: 'información de un usuario'
        def info = [
                nombre  : 'Iván',
                lenguajes: [
                        [nombre: 'Groovy', conocimientos: 10], [nombre: 'Java', conocimientos: 9]
                ]
        ]

        expect: 'su primer lenguaje es Java'
        info.lenguajes.nombre.first() == 'Java'
    }

}
