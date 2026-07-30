package day2

import utils.filereader.FileReader
import utils.range.Range
import utils.range.SameLengthRange

private const val ID_RANGES_SEPARATOR = ","
private const val INPUT_FILE_LINE_COUNT = 1

fun solveWithInvalidIdAdder(
    dayNumber: Int,
    fileName: String,
    adderFactory: (range: SameLengthRange) -> InvalidIdAdder
): Long {
    val ranges = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
        val rangeStrings = line.split(ID_RANGES_SEPARATOR)

        rangeStrings.map { Range.fromString(it) }
    }

    if (ranges.size != INPUT_FILE_LINE_COUNT) {
        throw IllegalArgumentException("File must be $INPUT_FILE_LINE_COUNT line.")
    }

    val flattenedRanges = ranges[0]

    val result = flattenedRanges.sumOf { range ->
        val subranges = range.divideIntoSameLengthSubranges()

        subranges.sumOf { subrange ->
            val counter = adderFactory(subrange)

            counter.sumUp()
        }
    }

    return result
}
