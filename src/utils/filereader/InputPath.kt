package utils.filereader

import java.nio.file.Path

private const val SOURCE_DIRECTORY_PATH = "src"
private const val DAY_DIRECTORY_PATH_TEMPLATE = "day%d"
private const val INPUT_DIRECTORY_PATH = "input"
private const val FILE_EXTENSION = ".txt"

object InputPath {
    fun getFullPathForFileName(dayNumber: Int, fileName: String): String {
        return Path.of(
            SOURCE_DIRECTORY_PATH,
            DAY_DIRECTORY_PATH_TEMPLATE.format(dayNumber),
            INPUT_DIRECTORY_PATH,
            fileName + FILE_EXTENSION
        ).toString()
    }
}
