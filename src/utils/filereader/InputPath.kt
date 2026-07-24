package utils.filereader

private const val INPUT_DIRECTORY_PATH = """src\day%d\input"""
private const val PATH_SEPARATOR = """\"""
private const val FILE_EXTENSION = ".txt"

object InputPath {
    fun getFullPathForFileName(dayNumber: Int, fileName: String): String {
        return INPUT_DIRECTORY_PATH.format(dayNumber) + PATH_SEPARATOR + fileName + FILE_EXTENSION
    }
}
