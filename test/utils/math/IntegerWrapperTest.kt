package utils.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class IntegerWrapperTest {
    companion object {
        @JvmStatic
        fun getFirstDigitCases() = listOf(
            Pair(-1, 1),
            Pair(0, 0),
            Pair(1, 1),
            Pair(4231, 4)
        )

        @JvmStatic
        fun isDivisibleByCases() = listOf(
            Triple(-2, 2, true),
            Triple(2, 2, true),
            Triple(1, 2, false),
            Triple(3, 2, false),
            Triple(3, -2, false),
            Triple(0, 3, true),
        )
    }

    @ParameterizedTest
    @MethodSource("getFirstDigitCases")
    fun `gets first digit correctly`(case: Pair<Int, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.getFirstDigit()

        assertEquals(expected, result, "getFirstDigit for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("isDivisibleByCases")
    fun `returns if number is divisible by factor correctly`(case: Triple<Int, Int, Boolean>) {
        val (number, factor, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.isDivisibleBy(factor)

        assertEquals(expected, result, "isDivisibleBy for $number and $factor should return $expected, got $result")
    }

    @Test
    fun `throws if checking if divisible by 0`() {
        val cut = IntegerWrapper(0)

        assertThrows(IllegalArgumentException::class.java) {
            cut.isDivisibleBy(0)
        }
    }
}
