package utils.math.sequences.mathSequence

data class InvalidGetNthElementTestCase(
    val n: Int
)

data class InvalidGetNFirstNumbersSumTestCase(
    val n: Int
)

data class InvalidDoGetPossibleFirstIndexOfElementTestCase(
    val element: Double
)

data class GetSumBetweenFirstAndElementTestCase(
    val element: Double,
    val expected: Double
)

data class InvalidGetSumBetweenFirstAndElementTestCase(
    val element: Double
)
