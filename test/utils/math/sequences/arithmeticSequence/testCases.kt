package utils.math.sequences.arithmeticSequence

data class GetNthElementTestCase(
    val firstElement: Double,
    val difference: Double,
    val n: Int,
    val expected: Double,
)

data class GetNFirstElementsSumTestCase(
    val firstElement: Double,
    val difference: Double,
    val n: Int,
    val expected: Double,
)

data class GetIndexOfElementTestCase(
    val firstElement: Double,
    val difference: Double,
    val element: Double,
    val expected: Int,
)

data class InvalidGetIndexOfElementTestCase(
    val firstElement: Double,
    val difference: Double,
    val element: Double,
)
