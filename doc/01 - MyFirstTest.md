# 01 - MyFirstTest

Vamos a crear nuestro primer test y explicar cada parte en detalle:

```groovy
class MiPrimerTest extends Specification {             // 1

    void 'invertir una cadena de texto'() {            // 2
        given: 'una cadena de text'                    // 3
            def miCadena = 'Hola Genbetadev'

        when: 'la invertimos'                          // 4
            def cadenaInvertida = miCadena.reverse()

        then: 'se invierte correctamente'              // 5
            cadenaInvertida == 'vedatebneG aloH'
    }
}
```

1. Todos nuestros tests deben heredar de spock.lang.Specification puesto que en esa clase se encuentra definido el runner compatible con JUnit.
2. El nombre de cada test se escribe como una cadena de texto entre comillas. Se acabó el nombrar los test como testInvertirCadenaDeTexto o similar. Ahora podemos escribir nombres de tests muy descriptivos y que realmente expresan el motivo del test.
3. En su forma más general todos los tests de Spock se basan en los bloques given, when, then, siendo given en el que establecemos el estado inicial de nuestro test.
4. En el bloque when describimos los estímulos, es decir, lo que queremos testear.
Finalmente, en la parte then pondremos las condiciones que se deben cumplir para que el test pase. Es importante notar que lo que escribamos en este bloque son aserciones, por lo que no es necesario usar assert delante de cada una de ellas.

Cabe también destacar que podemos escribir opcionalmente un pequeño texto explicativo en cada una de los bloques, given, when, then. De hecho se considera una buena práctica porque hace que podamos leer todo el test sin tener que mirar en detalle el código del mismo.
Una alternativa a escribir el test anterior que podemos usar en ocasiones para reducir la verbosidad del mismo es utilizar el bloque expect en el que directamente definimos nuestras expectativas.

```groovy
void 'invertir una cadena de texto'() {
    expect:
        'Hola Genbetadev'.reverse() == 'vedatebneG aloH'
}   
```

#### ¿Y si falla un test?
Si pensamos en TDD, sabemos que el ciclo de test sería escribir un test que falle, escribir el código mínimo que hace que el test pase y finalmente refactorizar el código.

Una característica que debe tener un buen framework de test es mostrar información relevante de cómo y por qué ha fallado un test. A ninguno nos gusta tener que llenar el código de println's o tener que depurar cuando el test falla.

Imaginemos el siguiente test. Queremos comprobar que el primer lenguaje de un usuario es Java. Este test falla porque como vemos, tenemos una lista de lenguajes y el primero de ellos es Groovy.

```groovy
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
```

Si ejecutamos el test, además de indicarnos que ha fallado, Spock nos mostrará la siguiente salida:

```groovy
Condition not satisfied:

info.lenguajes.nombre.first() == 'Java'
|    |         |      |       |
|    |         |      Groovy  false
|    |         [Groovy, Java] 5 differences (16% similarity)
|    |                        (Groo)v(y)
|    |                        (Ja--)v(a)
|    [[nombre:Groovy, conocimientos:10], [nombre:Java, conocimientos:9]]
[nombre:Iván, lenguajes:[[nombre:Groovy, conocimientos:10], [nombre:Java, conocimientos:9]]]

Expected :Java

Actual   :Groovy
```

Vemos que tenemos la información de cada variable del assert de tal forma que podemos ver claramente todos los valores y saber por qué ha fallado el test sin necesidad de depurar ni de println's adicionales.

###### Fuente

* [gambeta.com](https://www.genbeta.com/desarrollo/testeando-tus-aplicaciones-java-con-spock-tests-mas-expresivos-faciles-de-leer-y-mantener)
