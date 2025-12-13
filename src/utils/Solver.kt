package utils

class Solver<T, U>(
    private val testCases: List<TestCase<T>>,
    private val solver: (fileName: String) -> U
) {
    fun solve(): U {
        testCases.forEach {
            val result = solver(it.fileName)

            check(result == it.expectedResult) {
                "Test '${it.fileName}' failed: expected result = ${it.expectedResult}, actual result = $result"
            }
        }

        return solver(INPUT_FILE_NAME)
    }

    companion object {
        const val INPUT_FILE_NAME = "input"
    }
}
