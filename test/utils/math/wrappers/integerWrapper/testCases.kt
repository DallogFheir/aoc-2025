package utils.math.wrappers.integerWrapper

data class FirstDigitTestCase(
    val number: Long,
    val expected: Int,
)

data class LengthTestCase(
    val number: Long,
    val expected: Int,
)

data class IsDivisibleByTestCase(
    val number: Long,
    val factor: Long,
    val expected: Boolean,
)

data class WithAppendedDigitTestCase(
    val number: Long,
    val digit: Int,
    val expected: Long,
)

data class InvalidWithAppendedDigitTestCase(
    val number: Long,
    val digit: Int,
)

data class ToShiftedRightTestCase(
    val number: Long,
    val expected: Long,
)
