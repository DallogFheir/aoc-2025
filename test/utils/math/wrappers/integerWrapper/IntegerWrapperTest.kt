package utils.math.wrappers.integerWrapper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.wrappers.IntegerWrapper

class IntegerWrapperTest {
    companion object {
        @JvmStatic
        fun firstDigitCases() = listOf(
            FirstDigitTestCase(number = -1L, expected = 1),
            FirstDigitTestCase(number = 0L, expected = 0),
            FirstDigitTestCase(number = 1L, expected = 1),
            FirstDigitTestCase(number = 4231L, expected = 4),
        )

        @JvmStatic
        fun lengthCases() = listOf(
            LengthTestCase(number = 0L, expected = 1),
            LengthTestCase(number = -1L, expected = 1),
            LengthTestCase(number = 1L, expected = 1),
            LengthTestCase(number = 10L, expected = 2),
            LengthTestCase(number = 42_100L, expected = 5),
        )

        @JvmStatic
        fun isDivisibleByCases() = listOf(
            IsDivisibleByTestCase(number = -2L, factor = 2L, expected = true),
            IsDivisibleByTestCase(number = 2L, factor = 2L, expected = true),
            IsDivisibleByTestCase(number = 1L, factor = 2L, expected = false),
            IsDivisibleByTestCase(number = 3L, factor = 2L, expected = false),
            IsDivisibleByTestCase(number = 3L, factor = -2L, expected = false),
            IsDivisibleByTestCase(number = 0L, factor = 3L, expected = true),
        )

        @JvmStatic
        fun withAppendedDigitCases() = listOf(
            WithAppendedDigitTestCase(number = -2L, digit = 1, expected = -21L),
            WithAppendedDigitTestCase(number = 2L, digit = 1, expected = 21L),
            WithAppendedDigitTestCase(number = 123L, digit = 4, expected = 1234L),
            WithAppendedDigitTestCase(number = 0L, digit = 2, expected = 2L),
        )

        @JvmStatic
        fun invalidWithAppendedDigitCases() = listOf(
            InvalidWithAppendedDigitTestCase(number = 1L, digit = -1),
            InvalidWithAppendedDigitTestCase(number = 1L, digit = 11),
        )

        @JvmStatic
        fun toShiftedRightCases() = listOf(
            ToShiftedRightTestCase(number = 0L, expected = 0L),
            ToShiftedRightTestCase(number = 1L, expected = 0L),
            ToShiftedRightTestCase(number = 12L, expected = 2L),
            ToShiftedRightTestCase(number = 1234L, expected = 234L),
            ToShiftedRightTestCase(number = -1L, expected = 0L),
            ToShiftedRightTestCase(number = -12L, expected = -2L),
            ToShiftedRightTestCase(number = -1234L, expected = -234L),
        )
    }

    @ParameterizedTest
    @MethodSource("firstDigitCases")
    fun `gets first digit correctly`(case: FirstDigitTestCase) {
        val cut = IntegerWrapper(case.number)

        Assertions.assertEquals(
            case.expected,
            cut.firstDigit,
            "firstDigit for ${case.number} should return ${case.expected}, got ${cut.firstDigit}"
        )
    }

    @ParameterizedTest
    @MethodSource("lengthCases")
    fun `gets length correctly`(case: LengthTestCase) {
        val cut = IntegerWrapper(case.number)

        Assertions.assertEquals(
            case.expected,
            cut.length,
            "length for ${case.number} should return ${case.expected}, got ${cut.length}"
        )
    }

    @ParameterizedTest
    @MethodSource("isDivisibleByCases")
    fun `returns if number is divisible by factor correctly`(case: IsDivisibleByTestCase) {
        val cut = IntegerWrapper(case.number)

        val result = cut.isDivisibleBy(case.factor)

        Assertions.assertEquals(
            case.expected,
            result,
            "isDivisibleBy for number ${case.number} and factor ${case.factor} should return ${case.expected}, got $result"
        )
    }

    @Test
    fun `throws if checking if divisible by 0`() {
        val cut = IntegerWrapper(0)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.isDivisibleBy(0)
        }
    }

    @ParameterizedTest
    @MethodSource("withAppendedDigitCases")
    fun `appends digit correctly`(case: WithAppendedDigitTestCase) {
        val result = IntegerWrapper(case.number)
            .withAppendedDigit(case.digit)
            .number

        Assertions.assertEquals(
            case.expected,
            result,
            "withAppendedDigit for number ${case.number} and digit ${case.digit} should return IntegerWrapper with number ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidWithAppendedDigitCases")
    fun `throws if trying to append an invalid digit`(case: InvalidWithAppendedDigitTestCase) {
        val cut = IntegerWrapper(case.number)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.withAppendedDigit(case.digit)
        }
    }

    @ParameterizedTest
    @MethodSource("toShiftedRightCases")
    fun `shifts right correctly`(case: ToShiftedRightTestCase) {
        val result = IntegerWrapper(case.number)
            .toShiftedRight()
            .number

        Assertions.assertEquals(
            case.expected,
            result,
            "toShiftedRight for number ${case.number} should return IntegerWrapper with number ${case.expected}, got $result"
        )
    }
}
