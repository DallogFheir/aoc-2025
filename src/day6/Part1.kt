package day6

import day6.calculator.CephalopodCalculator
import utils.filereader.FileReader

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val parsedLines = FileReader(
            dayNumber = dayNumber,
            fileName = fileName
        ).readLinesWithParser { line -> line.split(" ").filter { it.isNotEmpty() } }

        val numbers = parsedLines.slice(0..<parsedLines.lastIndex).map { numberStrings ->
            numberStrings.map {
                it.toLong()
            }
        }
        val operatorSymbols = parsedLines.last()

        return CephalopodCalculator(numbers = numbers, operatorSymbols = operatorSymbols).calculate()
    }
}
