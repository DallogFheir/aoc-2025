package day6.calculator

import utils.math.operators.Operator

open class CephalopodCalculator(protected val numbers: List<List<Long>>, operatorSymbols: List<String>) {
    init {
        val numbersSizeSet = numbers.map { it.size }.toSet()
        require(numbersSizeSet.size == 1) {
            "All number lists must be same size"
        }

        require(operatorSymbols.size == numbersSizeSet.first()) {
            "Operator list must be same size as number lists"
        }
    }

    protected val operators = operatorSymbols.map { Operator.fromSymbol(it) }

    open fun calculate(): Long {
        val initialResults = List(operators.size) { index ->
            operators[index].neutralElement
        }

        val results = numbers.fold(initial = initialResults) { results, numbersRow ->
            results.zip(numbersRow).mapIndexed { index, zippedNumbers ->
                val (num1, num2) = zippedNumbers

                val operator = operators[index]

                operator.calculate(num1, num2)
            }
        }

        return results.sum()
    }
}
