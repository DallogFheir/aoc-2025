package utils.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MathUtilsTest {
    companion object {
        @JvmStatic
        fun getFirstNumberForDigitCountCases() = listOf(
            Pair(1, 0),
            Pair(2, 10),
            Pair(3, 100),
            Pair(5, 10_000)
        )

        @JvmStatic
        fun getLastNumberForDigitCountCases() = listOf(
            Pair(1, 9),
            Pair(2, 99),
            Pair(3, 999),
            Pair(5, 99_999)
        )

        @JvmStatic
        fun getXNumberForDigitCountNonPositiveCases() = listOf(
            -1, 0
        )
    }

    @ParameterizedTest
    @MethodSource("getFirstNumberForDigitCountCases")
    fun `gets first number for digit count correctly`(case: Pair<Int, Int>) {
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
    fun `gets last number for digit count correctly`(case: Pair<Int, Int>) {
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
