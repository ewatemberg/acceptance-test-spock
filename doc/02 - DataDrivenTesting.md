# 01 - DataDrivenTesting

En multitud de ocasiones tenemos que testear el mismo código pero con distintos datos de entrada. Además, en ocasiones, el setup necesario para probar el test no es despreciable por lo que en la práctica acabamos con una gran cantidad de tests en los que el 90% del código es el mismo y sólo cambian los datos y el resultado. Para solucionar este problema podemos usar lo que Spock llama Data driven testing.

```groovy
void 'comprobando el máximo entre dos números'() {
    expect:
        Math.max(a, b) == resultado

    where:
        a  | b | resultado
        1  | 4 | 4
        5  | 2 | 5
        -1 | 3 | 3
}
```

Creo que el test es suficientemente explicativo por sí mismo. Hemos creado una tabla de datos y Spock ejecutará el test tres veces sustituyendo en cada una de ellas las variables a, b y resultado con los valores de cada línea.
Esta aproximación, aunque directa y muy visual, tiene un pequeño problema. Si cualquiera de las iteraciones hace fallar el test, sólo sabremos que el test ha fallado pero no podremos saber exactamente cual de ellas lo ha hecho fallar. Para solucionarlo, añadiremos la anotación @Unroll y además podremos sustituir el nombre de las variables en el propio nombre del test.

```groovy
@Unroll
void 'comprobando el máximo entre dos números'() {
    expect:
        Math.max(a, b) == resultado

    where:
        a  | b | resultado
        1  | 4 | 4
        5  | 2 | 5
        -1 | 3 | 3
}
```

El resultado se mostrará como una lista de test.

###### Fuente

* [gambeta.com](https://www.genbeta.com/desarrollo/testeando-tus-aplicaciones-java-con-spock-tests-mas-expresivos-faciles-de-leer-y-mantener)

