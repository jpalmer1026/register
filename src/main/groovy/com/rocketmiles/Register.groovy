package com.rocketmiles

class Register {

    int twenties
    int tens
    int fives
    int twos
    int ones

    Register(int twenties, int tens, int fives, int twos, int ones) {
        this.twenties = twenties
        this.tens = tens
        this.fives = fives
        this.twos = twos
        this.ones = ones
    }

    /**
     * Show current state in total and each denomination.
     * for example $68 1 2 3 4 5 means: Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5
     *
     * @return current state in total and each denomination
     */
    String show() {
        this.toString()
    }

    /**
     * Put bills in each denomination: #$20 #$10 #$5 #$2 #$1
     *
     * @param twenties number of twenties to put into the register
     * @param tens number of tens to put into the register
     * @param fives number of fives to put into the register
     * @param twos number of twos to put into the register
     * @param ones number of ones to put into the register
     *
     * @return current state in total and each denomination
     */
    String put(int twenties, int tens, int fives, int twos, int ones) {
        this.twenties += twenties
        this.tens += tens
        this.fives += fives
        this.twos += twos
        this.ones += ones
        this.toString()
    }

    /**
     * Take bills in each denomination: #$20 #$10 #$5 #$2 #$1
     *
     * @param twenties number of twenties to take from the register
     * @param tens number of tens to take from the register
     * @param fives number of fives to take from the register
     * @param twos number of twos to take from the register
     * @param ones number of ones to take from the register
     *
     * @return current state in total and each denomination
     */
    String take(int twenties, int tens, int fives, int twos, int ones) {
        this.twenties -= twenties
        this.tens -= tens
        this.fives -= fives
        this.twos -= twos
        this.ones -= ones
        this.toString()
    }

    /**
     * Withdraw a specific amount from the register
     *
     * @param amount the amount to withdraw from the register
     *
     * @return current state in total and each denomination if there are sufficient funds and exact change can be made,
     *         sorry otherwise
     */
    String change(int amount) {
        if (hasSufficientFunds(amount)) {
            if (withdrawAmount(amount)) {
                return this.toString()
            }
        }

        'sorry'
    }

    private boolean hasSufficientFunds(int amountRequested) {
        if (calculateTotal() - amountRequested < 0) {
            return false
        }

        true
    }

    private boolean withdrawAmount(int amountRequested) {
        List<Integer> moneyList = []
        Denomination.values().reverse().each { denomination ->
            (0..<getDenominationCount(denomination)).each {
                moneyList << denomination.dollarValue
            }
        }
        canChangeBeMadeForAmount(moneyList, amountRequested)
    }

    private int getDenominationCount(Denomination denomination) {
        int denominationCount
        switch (denomination) {
            case Denomination.ONE:
                denominationCount = ones
                break
            case Denomination.TWO:
                denominationCount = twos
                break
            case Denomination.FIVE:
                denominationCount = fives
                break
            case Denomination.TEN:
                denominationCount = tens
                break
            case Denomination.TWENTY:
                denominationCount = twenties
                break
        }
        denominationCount
    }

    private boolean canChangeBeMadeForAmount(List<Integer> billDenominationList, int amountRequested, List<Integer> partial = []) {
        int s = 0
        if (partial) {
            s = partial.sum()
        }

        if (s == amountRequested) {
            partial.each {
                decrementBills(it)
            }
            return true
        }

        if (s > amountRequested) {
            return
        }

        for (i in 0..<billDenominationList.size()) {
            List<Integer> remaining = []
            int n = billDenominationList[i]
            (i + 1..<billDenominationList.size()).each { j ->
                remaining << billDenominationList[j]
            }
            List<Integer> partialList = [] + partial
            partialList << n
            if (canChangeBeMadeForAmount(remaining, amountRequested, partialList)) {
                return true
            }
        }
    }

    private void decrementBills(int it) {
        switch (it) {
            case 20:
                twenties--
                break
            case 10:
                tens--
                break
            case 5:
                fives--
                break
            case 2:
                twos--
                break
            case 1:
                ones--
                break
        }
    }

    private int calculateTotal() {
        twenties * 20 + tens * 10 + fives * 5 + twos * 2 + ones
    }

    @Override
    String toString() {
        "\$${calculateTotal()} $twenties $tens $fives $twos $ones"
    }
}

