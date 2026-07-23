package utils.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class IntegerWrapperTest {
    companion object {
        @JvmStatic
        fun firstDigitCases() = listOf(
            Pair(-1, 1),
            Pair(0, 0),
            Pair(1, 1),
            Pair(4231, 4)
        )

        @JvmStatic
        fun lengthCases() = listOf(
            Pair(0, 1),
            Pair(-1, 1),
            Pair(1, 1),
            Pair(10, 2),
            Pair(42_100, 5),
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

        @JvmStatic
        fun withAppendedDigitValidCases() = listOf(
            Triple(-2, 1, -21),
            Triple(2, 1, 21),
            Triple(123, 4, 1234),
            Triple(0, 2, 2),
        )

        @JvmStatic
        fun withAppendedDigitInvalidCases() = listOf(
            Pair(1, -1),
            Pair(1, 11),
        )

        @JvmStatic
        fun toShiftedRightCases() = listOf(
            Pair(0, 0),
            Pair(1, 0),
            Pair(12, 2),
            Pair(1234, 234),
            Pair(-1, 0),
            Pair(-12, -2),
            Pair(-1234, -234),
        )
    }

    @ParameterizedTest
    @MethodSource("firstDigitCases")
    fun `gets first digit correctly`(case: Pair<Int, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.firstDigit

        assertEquals(expected, result, "firstDigit for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("lengthCases")
    fun `gets length correctly`(case: Pair<Int, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.length

        assertEquals(expected, result, "length for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("isDivisibleByCases")
    fun `returns if number is divisible by factor correctly`(case: Triple<Int, Int, Boolean>) {
        val (number, factor, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.isDivisibleBy(factor)

        assertEquals(
            expected,
            result,
            "isDivisibleBy for number $number and factor $factor should return $expected, got $result"
        )
    }

    @Test
    fun `throws if checking if divisible by 0`() {
        val cut = IntegerWrapper(0)

        assertThrows(IllegalArgumentException::class.java) {
            cut.isDivisibleBy(0)
        }
    }

    @ParameterizedTest
    @MethodSource("withAppendedDigitValidCases")
    fun `appends digit correctly`(case: Triple<Int, Int, Int>) {
        val (number, digit, expected) = case

        val cut = IntegerWrapper(number)
        val cutWithAppendedDigit = cut.withAppendedDigit(digit)
        val result = cutWithAppendedDigit.number

        assertEquals(
            expected,
            result,
            "withAppendedDigit for number $number and digit $digit should return IntegerWrapper with number $expected, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("withAppendedDigitInvalidCases")
    fun `throws if trying to append an invalid digit`(case: Pair<Int, Int>) {
        val (number, digit) = case

        val cut = IntegerWrapper(number)

        assertThrows(IllegalArgumentException::class.java) {
            cut.withAppendedDigit(digit)
        }
    }

    @ParameterizedTest
    @MethodSource("toShiftedRightCases")
    fun `shifts right correctly`(case: Pair<Int, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)
        val cutShiftedRighted = cut.toShiftedRight()
        val result = cutShiftedRighted.number

        assertEquals(
            expected,
            result,
            "toShiftedRight for number $number should return IntegerWrapper with number $expected, got $result"
        )
    }
}
