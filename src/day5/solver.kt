package day5

import utils.filereader.FileReader
import utils.range.Range

fun solveForRangesAndIngredients(
    dayNumber: Int,
    fileName: String,
    callback: (ranges: List<Range>, ingredients: List<Long>) -> Long
): Long {
    val (ranges, ingredients) = FileReader(dayNumber = dayNumber, fileName = fileName).readTwoPartLinesWithParsers(
        part1Parser = { line -> Range.fromString(line) },
        part2Parser = { line -> line.toLong() }
    )

    return callback(ranges, ingredients)
}
