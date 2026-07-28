package utils.math.mathUtils

data class GetFirstNumberForDigitCountTestCase(
    val digitCount: Int,
    val expected: Long,
)

data class GetLastNumberForDigitCountTestCase(
    val digitCount: Int,
    val expected: Long,
)

data class InvalidNonPositiveGetXNumberForDigitCountTestCase(
    val digitCount: Int,
)
