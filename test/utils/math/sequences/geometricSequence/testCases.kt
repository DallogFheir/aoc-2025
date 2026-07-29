package utils.math.sequences.geometricSequence

data class GetNthElementTestCase(
    val firstElement: Double,
    val ratio: Double,
    val n: Int,
    val expected: Double? = null,
)

data class GetNFirstElementsSumTestCase(
    val firstElement: Double,
    val ratio: Double,
    val n: Int,
    val expected: Double? = null,
)

data class GetIndexOfElementTestCase(
    val firstElement: Double,
    val ratio: Double,
    val element: Double,
    val expected: Int,
)

data class InvalidGetIndexOfElementTestCase(
    val firstElement: Double,
    val ratio: Double,
    val element: Double,
)
