package utils.filereader

import java.io.File

class FileReader(dayNumber: Int, fileName: String) {
    private val filePath = InputPath.getFullPathForFileName(dayNumber = dayNumber, fileName = fileName)

    fun read(): String {
        return File(filePath).readText()
    }

    fun <T> readLinesWithParser(parser: (line: String) -> T): List<T> {
        return File(filePath).readLines().map { parser(it) }
    }

    fun <T, U> readTwoPartLinesWithParsers(
        part1Parser: (line: String) -> T,
        part2Parser: (line: String) -> U
    ): Pair<List<T>, List<U>> {
        return readTwoPartWithParsers(
            part1Parser = {
                val lines = it.split(System.lineSeparator())

                lines.map { line ->
                    part1Parser(line)
                }
            },
            part2Parser = {
                val lines = it.split(System.lineSeparator())

                lines.map { line ->
                    part2Parser(line)
                }
            },
        )
    }

    fun <T, U> readTwoPartWithParsers(
        part1Parser: (content: String) -> T,
        part2Parser: (content: String) -> U
    ): Pair<T, U> {
        val partSeparator = "${System.lineSeparator()}${System.lineSeparator()}"

        val text = read()
        val parts = listOf(
            text.substringBeforeLast(partSeparator),
            text.substringAfterLast(partSeparator),
        )

        val part1 = part1Parser(parts[0])
        val part2 = part2Parser(parts[1])

        return Pair(part1, part2)
    }
}
