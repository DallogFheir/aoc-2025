package utils.filereader

import java.io.File

class FileReader(fileName: String) {
    private val filePath = InputPath.getFullPathForFileName(fileName)

    fun <T> readLinesWithParser(parser: (line: String) -> T): List<T> {
        return File(filePath).readLines().map { parser(it) }
    }
}
