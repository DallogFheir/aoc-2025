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
}
