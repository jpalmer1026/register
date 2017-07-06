package com.rocketmiles

import spock.lang.Specification
import spock.lang.Unroll

class RegisterTest extends Specification {

    void "show exhibits correct behavior"() {
        given: 'a register with $68 in a predefined amount of bills'
        Register register = new Register(1, 2, 3, 4, 5)

        expect: 'show returns the expected total and amount of each denomination correctly'
        register.show() == '$68 1 2 3 4 5'
    }

    void "put exhibits correct behavior"() {
        given: 'a register with $68 in a predefined amount of bills'
        Register register = new Register(1, 2, 3, 4, 5)

        expect: 'put should return the expected results after putting specific denominations into the register'
        register.put(1, 2, 3, 0, 5) == '$128 2 4 6 4 10'
    }

    void "take exhibits correct behavior"() {
        given: 'a register with $128 in a predefined amount of bills'
        Register register = new Register(2, 4, 6, 4, 10)

        expect: 'take should return the expected results after taking specific denominations from the register'
        register.take(1, 4, 3, 0, 10) == '$43 1 0 3 4 0'
    }

    void "no bills are withdrawn from register when more money than the registewr contains is requested or exact change can't be given"() {
        given: 'a register with $43 in a predefined amount of bills'
        Register register = new Register(1, 0, 3, 4, 0)

        when: 'more money is requested then the register contains'
        register.change(44)

        then: 'no money is withdrawn'
        register.toString() == '$43 1 0 3 4 0'

        when: 'exact change can\'t be given for the amount requested'
        register.change(3)

        then: 'no money is withdrawn'
        register.toString() == '$43 1 0 3 4 0'
    }

    @Unroll
    void "change method returns expected amount when \$#amount is requested"() {
        given: 'a register with $43 in a predefined amount of bills'
        Register register = new Register(1, 0, 3, 4, 0)

        when: 'change is called with an amount configured by the test'
        String result = register.change(amount)

        then: 'the returned result is what was expected'
        result == expectedResult

        where:
        amount || expectedResult
        0      || '$43 1 0 3 4 0'
        1      || 'sorry'
        2      || '$41 1 0 3 3 0'
        3      || 'sorry'
        4      || '$39 1 0 3 2 0'
        5      || '$38 1 0 2 4 0'
        6      || '$37 1 0 3 1 0'
        7      || '$36 1 0 2 3 0'
        8      || '$35 1 0 3 0 0'
        9      || '$34 1 0 2 2 0'
        10     || '$33 1 0 1 4 0'
        11     || '$32 1 0 2 1 0'
        12     || '$31 1 0 1 3 0'
        13     || '$30 1 0 2 0 0'
        14     || '$29 1 0 1 2 0'
        15     || '$28 1 0 0 4 0'
        16     || '$27 1 0 1 1 0'
        17     || '$26 1 0 0 3 0'
        18     || '$25 1 0 1 0 0'
        19     || '$24 1 0 0 2 0'
        20     || '$23 0 0 3 4 0'
        21     || '$22 1 0 0 1 0'
        22     || '$21 0 0 3 3 0'
        23     || '$20 1 0 0 0 0'
        24     || '$19 0 0 3 2 0'
        25     || '$18 0 0 2 4 0'
        26     || '$17 0 0 3 1 0'
        27     || '$16 0 0 2 3 0'
        28     || '$15 0 0 3 0 0'
        29     || '$14 0 0 2 2 0'
        30     || '$13 0 0 1 4 0'
        31     || '$12 0 0 2 1 0'
        32     || '$11 0 0 1 3 0'
        33     || '$10 0 0 2 0 0'
        34     || '$9 0 0 1 2 0'
        35     || '$8 0 0 0 4 0'
        36     || '$7 0 0 1 1 0'
        37     || '$6 0 0 0 3 0'
        38     || '$5 0 0 1 0 0'
        39     || '$4 0 0 0 2 0'
        40     || 'sorry'
        41     || '$2 0 0 0 1 0'
        42     || 'sorry'
        43     || '$0 0 0 0 0 0'
    }
}
