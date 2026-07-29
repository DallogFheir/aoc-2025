package utils

private const val INPUT_FILE_NAME = "input"

class Solver<T, U>(
    private val dayNumber: Int,
    private val testCases: List<TestCase<T>>,
    private val solver: (dayNumber: Int, fileName: String) -> U
) {
    fun solve(): U {
        testCases.forEach {
            val result = solver(dayNumber, it.fileName)

            check(result == it.expectedResult) {
                "Test '${it.fileName}' failed: expected result = ${it.expectedResult}, actual result = $result"
            }
        }

        return solver(dayNumber, INPUT_FILE_NAME)
    }
}
