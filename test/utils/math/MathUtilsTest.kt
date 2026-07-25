package utils.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MathUtilsTest {
    companion object {
        @JvmStatic
        fun getFirstNumberForDigitCountCases() = listOf(
            Pair(1, 0L),
            Pair(2, 10L),
            Pair(3, 100L),
            Pair(5, 10_000L)
        )

        @JvmStatic
        fun getLastNumberForDigitCountCases() = listOf(
            Pair(1, 9L),
            Pair(2, 99L),
            Pair(3, 999L),
            Pair(5, 99_999L)
        )

        @JvmStatic
        fun getXNumberForDigitCountNonPositiveCases() = listOf(
            -1, 0
        )
    }

    @ParameterizedTest
    @MethodSource("getFirstNumberForDigitCountCases")
    fun `gets first number for digit count correctly`(case: Pair<Int, Long>) {
        val (digitCount, expected) = case

        val result = MathUtils.getFirstNumberForDigitCount(digitCount)

        assertEquals(
            expected,
            result,
            "getFirstNumberForDigitCount for $digitCount should return $expected, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("getXNumberForDigitCountNonPositiveCases")
    fun `throws if trying to get first number for non-positive digit count`(case: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            MathUtils.getFirstNumberForDigitCount(case)
        }
    }

    @ParameterizedTest
    @MethodSource("getLastNumberForDigitCountCases")
    fun `gets last number for digit count correctly`(case: Pair<Int, Long>) {
        val (digitCount, expected) = case

        val result = MathUtils.getLastNumberForDigitCount(digitCount)

        assertEquals(
            expected,
            result,
            "getLastNumberForDigitCount for $digitCount should return $expected, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("getXNumberForDigitCountNonPositiveCases")
    fun `throws if trying to get last number for non-positive digit count`(case: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            MathUtils.getLastNumberForDigitCount(case)
        }
    }
}
