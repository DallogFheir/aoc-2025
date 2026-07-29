package utils.math.mathUtils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.MathUtils

class MathUtilsTest {
    companion object {
        @JvmStatic
        fun getFirstNumberForDigitCountCases() = listOf(
            GetFirstNumberForDigitCountTestCase(
                digitCount = 1,
                expected = 0L,
            ),
            GetFirstNumberForDigitCountTestCase(
                digitCount = 2,
                expected = 10L,
            ),
            GetFirstNumberForDigitCountTestCase(
                digitCount = 3,
                expected = 100L,
            ),
            GetFirstNumberForDigitCountTestCase(
                digitCount = 5,
                expected = 10_000L,
            ),
        )

        @JvmStatic
        fun getLastNumberForDigitCountCases() = listOf(
            GetLastNumberForDigitCountTestCase(
                digitCount = 1,
                expected = 9L,
            ),
            GetLastNumberForDigitCountTestCase(
                digitCount = 2,
                expected = 99L,
            ),
            GetLastNumberForDigitCountTestCase(
                digitCount = 3,
                expected = 999L,
            ),
            GetLastNumberForDigitCountTestCase(
                digitCount = 5,
                expected = 99_999L,
            ),
        )

        @JvmStatic
        fun invalidNonPositiveGetXNumberForDigitCountCases() = listOf(
            InvalidNonPositiveGetXNumberForDigitCountTestCase(digitCount = -1),
            InvalidNonPositiveGetXNumberForDigitCountTestCase(digitCount = 0),
        )
    }

    @ParameterizedTest
    @MethodSource("getFirstNumberForDigitCountCases")
    fun `gets first number for digit count correctly`(case: GetFirstNumberForDigitCountTestCase) {
        val (digitCount, expected) = case

        val result = MathUtils.getFirstNumberForDigitCount(digitCount)

        Assertions.assertEquals(
            expected,
            result,
            "getFirstNumberForDigitCount for $digitCount should return $expected, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidNonPositiveGetXNumberForDigitCountCases")
    fun `throws if trying to get first number for non-positive digit count`(case: InvalidNonPositiveGetXNumberForDigitCountTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            MathUtils.getFirstNumberForDigitCount(case.digitCount)
        }
    }

    @ParameterizedTest
    @MethodSource("getLastNumberForDigitCountCases")
    fun `gets last number for digit count correctly`(case: GetLastNumberForDigitCountTestCase) {
        val (digitCount, expected) = case

        val result = MathUtils.getLastNumberForDigitCount(digitCount)

        Assertions.assertEquals(
            expected,
            result,
            "getLastNumberForDigitCount for $digitCount should return $expected, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidNonPositiveGetXNumberForDigitCountCases")
    fun `throws if trying to get last number for non-positive digit count`(case: InvalidNonPositiveGetXNumberForDigitCountTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            MathUtils.getLastNumberForDigitCount(case.digitCount)
        }
    }
}
