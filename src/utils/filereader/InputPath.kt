package utils.filereader

object InputPath {
    const val INPUT_DIRECTORY_PATH = """src\day1\input"""
    const val PATH_SEPARATOR = """\"""
    const val FILE_EXTENSION = ".txt"

    fun getFullPathForFileName(fileName: String): String {
        return INPUT_DIRECTORY_PATH + PATH_SEPARATOR + fileName + FILE_EXTENSION
    }
}
