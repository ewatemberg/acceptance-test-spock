# Functional Test with Spock & Spring Boot

Este proyecto contiene algunso ejemplos de tests funcionales realizados con Spock.

## Indice

* [Introducción](#Introducción)
* [Entorno](#Entorno)
* [Run](#Run)
* [01 - My First Test](https://github.com/ewatemberg/functional-test-spock/blob/master/doc/01%20-%20MyFirstTest.md)
* [02 - Data Driven Testing](https://github.com/ewatemberg/functional-test-spock/blob/master/doc/02%20-%20DataDrivenTesting.md)
* [03 - Using Mock](https://github.com/ewatemberg/functional-test-spock/blob/master/doc/03%20-%20UsingMock.md)
* [04 - Using Stub](https://github.com/ewatemberg/functional-test-spock/blob/master/doc/04%20-%20UsingStub.md)


### Introdución
No es ninguna sorpresa que en el mundo Java la herramienta de test más utilizada sea JUnit si tenemos en cuenta que fue creada hace más de 15 años. Esto, sin embargo, no implica que sea la mejor, sino que en muchas ocasiones seguimos utilizando las mismas herramientas por inercia o porque aquí siempre se ha hecho esto así sin plantearnos si existen alternativas mejores. Una de estas alternativas es [Spock](https://github.com/spockframework/spock).

Spock es un framework de tests basado en Groovy que podemos utilizar para testear tanto aplicaciones Java como Groovy. Con Spock podemos escribir tests muy expresivos, fáciles de leer y mantener. Todo ello es posible por dos motivos principalmente: el magnífico DSL que proporciona Spock y la potencia de Groovy, lenguaje con el que escribimos los tests.
Proporciona un runner de JUnit por lo que es compatible con cualquier herramienta, IDE y servidor de integración continua que utilicemos actualmente con JUnit.

### Entorno
Empezando con Spock
```java
    <!-- Mandatory dependencies for using Spock -->
    <dependency>
        <groupId>org.spockframework</groupId>
        <artifactId>spock-core</artifactId>
        <version>1.0-groovy-2.4</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.spockframework</groupId>
        <artifactId>spock-spring</artifactId>
        <version>1.0-groovy-2.4</version>
    </dependency>
    <dependency>
        <groupId>org.codehaus.groovy</groupId>
        <artifactId>groovy-all</artifactId>
        <!-- any version of Groovy \>= 1.5.0 should work here -->
        <version>2.5.8</version>
    </dependency>
```

```java
    <plugin>
        <!-- The gmavenplus plugin is used to compile Groovy code. To learn more about this plugin, visit https://github.com/groovy/GMavenPlus/wiki -->
        <groupId>org.codehaus.gmavenplus</groupId>
        <artifactId>gmavenplus-plugin</artifactId>
        <version>1.8.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>addSources</goal>
                    <goal>addTestSources</goal>
                    <goal>compile</goal>
                    <goal>compileTests</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
```

### Run

##### Instalar Lombok en el IDE

_Esto depende del IDE que utilices, seguir indicaciones de la web de [Project Lombok](https://projectlombok.org/)_


##### Generar Mappers (Mapstruct)

```java
mvn clean install
```

##### Importar BD

```java
mvn flyway:migrate
```

##### Ejecutar test

```java
mvn test
```

##### Iniciar aplicación

```java
mvn springboot:run
```  

[swagger](http://localhost:8080/swagger-ui.html)

###### Fuente

* [adictosaltrabajo](https://www.adictosaltrabajo.com/2016/02/05/tests-funcionales-con-spock-y-geb-para-una-aplicacion-spring-boot/)
* [gambeta.com](https://www.genbeta.com/desarrollo/testeando-tus-aplicaciones-java-con-spock-tests-mas-expresivos-faciles-de-leer-y-mantener)


