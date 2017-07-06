package com.rocketmiles

enum Denomination {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20)

    Denomination(int value) {
        this.dollarValue = value
    }
    int getDollarValue() {
        dollarValue
    }

    private final int dollarValue
}