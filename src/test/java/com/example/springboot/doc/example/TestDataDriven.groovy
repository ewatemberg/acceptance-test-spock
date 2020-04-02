package com.example.springboot.doc.example

import spock.lang.Specification
import spock.lang.Unroll

class TestDataDriven extends Specification {

    //@Unroll
    void 'El máximo entre dos números #a y #b es #resultado'() {
        expect:
        Math.max(a, b) == resultado

        where:
        a  | b | resultado
        1  | 4 | 4
        5  | 2 | 5
        -1 | 3 | 3
    }

}
