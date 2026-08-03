package day06

import utils.filereader.FileReader
import utils.math.operators.Operator
import utils.math.wrappers.IntegerWrapper

private const val EMPTY_CELL = " "

private class FoldResult(
    val numbers: List<List<Long>>,
    val operatorSymbols: List<String>,
    val currentNumberList: List<Long>,
    val currentOperatorSymbol: String?,
)

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val lines = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { it }

        val numberLines = lines.slice(0..<lines.lastIndex)
        val operatorLine = lines.last()

        val columnCount = lines.first().length

        val result = (0..<columnCount).fold(
            initial =
                FoldResult(
                    numbers = listOf(),
                    operatorSymbols = listOf(),
                    currentNumberList = listOf(),
                    currentOperatorSymbol = null,
                )
        ) { result, columnIndex ->
            if (numberLines.all { it[columnIndex].toString() == EMPTY_CELL } && operatorLine[columnIndex].toString() == EMPTY_CELL) {
                require(result.currentOperatorSymbol != null)
                require(result.currentNumberList.isNotEmpty())

                return@fold FoldResult(
                    numbers = result.numbers + listOf(result.currentNumberList),
                    operatorSymbols = result.operatorSymbols + result.currentOperatorSymbol,
                    currentNumberList = listOf(),
                    currentOperatorSymbol = null,
                )
            }

            val number = numberLines.fold(initial = IntegerWrapper(0L)) { number, numberLine ->
                val digitString = numberLine[columnIndex].toString()

                if (digitString == EMPTY_CELL && number.number != 0L) {
                    return@fold number
                }

                val digit = if (digitString == EMPTY_CELL) 0 else digitString.toInt()

                number.withAppendedDigit(digit)
            }

            var currentOperatorSymbol = result.currentOperatorSymbol
            val operatorSymbol = operatorLine[columnIndex].toString()
            if (operatorSymbol != EMPTY_CELL) {
                currentOperatorSymbol = operatorSymbol
            }

            FoldResult(
                numbers = result.numbers,
                operatorSymbols = result.operatorSymbols,
                currentNumberList = result.currentNumberList + listOf(number.number),
                currentOperatorSymbol = currentOperatorSymbol,
            )
        }

        require(result.currentOperatorSymbol != null)
        require(result.currentNumberList.isNotEmpty())

        val numbers = result.numbers + listOf(result.currentNumberList)
        val operatorSymbols = result.operatorSymbols + listOf(result.currentOperatorSymbol)

        require(numbers.size == operatorSymbols.size)

        return numbers.zip(operatorSymbols).sumOf { (numberList, operatorSymbol) ->
            val operator = Operator.fromSymbol(operatorSymbol)

            operator.calculate(*numberList.toLongArray())
        }
    }
}
