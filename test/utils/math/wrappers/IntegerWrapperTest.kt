package utils.math.wrappers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class IntegerWrapperTest {
    companion object {
        @JvmStatic
        fun firstDigitCases() = listOf(
            Pair(-1L, 1),
            Pair(0L, 0),
            Pair(1L, 1),
            Pair(4231L, 4)
        )

        @JvmStatic
        fun lengthCases() = listOf(
            Pair(0L, 1),
            Pair(-1L, 1),
            Pair(1L, 1),
            Pair(10L, 2),
            Pair(42_100L, 5),
        )

        @JvmStatic
        fun isDivisibleByCases() = listOf(
            Triple(-2L, 2L, true),
            Triple(2L, 2L, true),
            Triple(1L, 2L, false),
            Triple(3L, 2L, false),
            Triple(3L, -2L, false),
            Triple(0L, 3L, true),
        )

        @JvmStatic
        fun withAppendedDigitCases() = listOf(
            Triple(-2L, 1, -21L),
            Triple(2L, 1, 21L),
            Triple(123L, 4, 1234L),
            Triple(0L, 2, 2L),
        )

        @JvmStatic
        fun invalidWithAppendedDigitCases() = listOf(
            Pair(1L, -1),
            Pair(1L, 11),
        )

        @JvmStatic
        fun toShiftedRightCases() = listOf(
            Pair(0L, 0L),
            Pair(1L, 0L),
            Pair(12L, 2L),
            Pair(1234L, 234L),
            Pair(-1L, 0L),
            Pair(-12L, -2L),
            Pair(-1234L, -234L),
        )
    }

    @ParameterizedTest
    @MethodSource("firstDigitCases")
    fun `gets first digit correctly`(case: Pair<Long, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.firstDigit

        assertEquals(expected, result, "firstDigit for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("lengthCases")
    fun `gets length correctly`(case: Pair<Long, Int>) {
        val (number, expected) = case

        val cut = IntegerWrapper(number)

        val result = cut.length

        assertEquals(expected, result, "length for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("isDivisibleByCases")
    fun `returns if number is divisible by factor correctly`(case: Triple<Long, Long, Boolean>) {
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
    @MethodSource("withAppendedDigitCases")
    fun `appends digit correctly`(case: Triple<Long, Int, Long>) {
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
    @MethodSource("invalidWithAppendedDigitCases")
    fun `throws if trying to append an invalid digit`(case: Pair<Long, Int>) {
        val (number, digit) = case

        val cut = IntegerWrapper(number)

        assertThrows(IllegalArgumentException::class.java) {
            cut.withAppendedDigit(digit)
        }
    }

    @ParameterizedTest
    @MethodSource("toShiftedRightCases")
    fun `shifts right correctly`(case: Pair<Long, Long>) {
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
