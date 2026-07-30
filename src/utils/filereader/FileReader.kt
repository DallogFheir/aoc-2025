package utils.filereader

import java.io.File

class FileReader(dayNumber: Int, fileName: String) {
    private val filePath = InputPath.getFullPathForFileName(dayNumber = dayNumber, fileName = fileName)

    fun <T> readLinesWithParser(parser: (line: String) -> T): List<T> {
        return File(filePath).readLines().map { parser(it) }
    }

    fun read(): String {
        return File(filePath).readText()
    }

    fun <T, U> readTwoPartLinesWithParsers(
        part1Parser: (line: String) -> T,
        part2Parser: (line: String) -> U
    ): Pair<List<T>, List<U>> {
        val partSeparator = "${System.lineSeparator()}${System.lineSeparator()}"

        val text = read()
        val parts = text.split(partSeparator)

        if (parts.size != 2) {
            throw IllegalArgumentException("Input must contain 2 parts divided by double line break")
        }

        val part1 = parts[0].split(System.lineSeparator()).map { part1Parser(it) }
        val part2 = parts[1].split(System.lineSeparator()).map { part2Parser(it) }

        return Pair(part1, part2)
    }
}
