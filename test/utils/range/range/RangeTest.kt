package utils.range.range

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.range.Range

class RangeTest {
    companion object {
        @JvmStatic
        fun invalidConstructorCases() = listOf(
            InvalidConstructorTestCase(rangeStart = -1L, rangeEnd = 1L),
            InvalidConstructorTestCase(rangeStart = 0L, rangeEnd = 1L),
            InvalidConstructorTestCase(rangeStart = 1L, rangeEnd = -1L),
            InvalidConstructorTestCase(rangeStart = 1L, rangeEnd = 0L),
            InvalidConstructorTestCase(rangeStart = 3L, rangeEnd = 1L),
        )

        @JvmStatic
        fun fromStringCases() = listOf(
            FromStringTestCase(string = "1-2", expectedRangeStart = 1, expectedRangeEnd = 2),
            FromStringTestCase(string = "1-3000", expectedRangeStart = 1, expectedRangeEnd = 3000),
            FromStringTestCase(string = "1000-3000", expectedRangeStart = 1000, expectedRangeEnd = 3000),
        )

        @JvmStatic
        fun invalidFromStringCases() = listOf(
            InvalidFromStringTestCase(string = "1"),
            InvalidFromStringTestCase(string = "a-b"),
            InvalidFromStringTestCase(string = "1-2-3"),
        )

        @JvmStatic
        fun divideIntoSameLengthSubrangesCases() = listOf(
            DivideIntoSameLengthSubrangesTestCase(
                rangeStart = 1,
                rangeEnd = 2,
                expectedSubrangeBoundaries = listOf(SubrangeBoundary(rangeStart = 1, rangeEnd = 2))
            ),
            DivideIntoSameLengthSubrangesTestCase(
                rangeStart = 1,
                rangeEnd = 10,
                expectedSubrangeBoundaries = listOf(
                    SubrangeBoundary(rangeStart = 1, rangeEnd = 9),
                    SubrangeBoundary(rangeStart = 10, rangeEnd = 10)
                )
            ),
            DivideIntoSameLengthSubrangesTestCase(
                rangeStart = 150,
                rangeEnd = 10_500,
                expectedSubrangeBoundaries = listOf(
                    SubrangeBoundary(rangeStart = 150, rangeEnd = 999),
                    SubrangeBoundary(rangeStart = 1000, rangeEnd = 9999),
                    SubrangeBoundary(rangeStart = 10_000, rangeEnd = 10_500)
                )
            ),
        )

        @JvmStatic
        fun containsCases() = listOf(
            ContainsTestCase(
                rangeStart = 1,
                rangeEnd = 3,
                valueToCheck = 0,
                expected = false,
            ),
            ContainsTestCase(
                rangeStart = 1,
                rangeEnd = 3,
                valueToCheck = 1,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1,
                rangeEnd = 3,
                valueToCheck = 2,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1,
                rangeEnd = 3,
                valueToCheck = 3,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1,
                rangeEnd = 3,
                valueToCheck = 4,
                expected = false,
            )
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `raises if initialized with invalid range ends`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Range(start = case.rangeStart, end = case.rangeEnd)
        }
    }

    @ParameterizedTest
    @MethodSource("fromStringCases")
    fun `initializes from string correctly`(case: FromStringTestCase) {
        val cut = Range.fromString(case.string)

        Assertions.assertEquals(
            case.expectedRangeStart,
            cut.start,
            "fromString for ${case.string} should initialize range with ${case.expectedRangeStart}-${case.expectedRangeEnd}, got ${cut.start}-${cut.end}"
        )
        Assertions.assertEquals(
            case.expectedRangeEnd,
            cut.end,
            "fromString for ${case.string} should initialize range with ${case.expectedRangeEnd}-${case.expectedRangeEnd}, got ${cut.start}-${cut.end}"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidFromStringCases")
    fun `raises if initialized from string with strings`(case: InvalidFromStringTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Range.fromString(case.string)
        }
    }

    @ParameterizedTest
    @MethodSource("divideIntoSameLengthSubrangesCases")
    fun `divides into same length subranges correctly`(case: DivideIntoSameLengthSubrangesTestCase) {
        val cut = Range(start = case.rangeStart, end = case.rangeEnd)

        val subranges = cut.divideIntoSameLengthSubranges()

        Assertions.assertEquals(
            case.expectedSubrangeBoundaries.size,
            subranges.size,
            "expected ${case.expectedSubrangeBoundaries.size} for subrange of range ${case.rangeStart}-${case.rangeEnd} but got ${subranges.size}"
        )

        case.expectedSubrangeBoundaries.zip(subranges).forEachIndexed { index, value ->
            val (expectedSubrangeBound, subrange) = value

            val message =
                "subrange no. $index for range ${subrange.start}-${subrange.end} should be ${expectedSubrangeBound.rangeStart}-${expectedSubrangeBound.rangeEnd}, got ${subrange.start}-${subrange.end}"

            Assertions.assertEquals(
                expectedSubrangeBound.rangeStart,
                subrange.start,
                message,
            )
            Assertions.assertEquals(
                expectedSubrangeBound.rangeEnd,
                subrange.end,
                message,
            )
        }
    }

    @ParameterizedTest
    @MethodSource("containsCases")
    fun `contains returns whether range contains given value`(case: ContainsTestCase) {
        val cut = Range(start = case.rangeStart, end = case.rangeEnd)

        val result = cut.contains(case.valueToCheck)

        Assertions.assertEquals(
            case.expected,
            result,
            "contains for range ${cut.start}-${cut.end} and value to check ${case.valueToCheck} should return ${case.expected}, got $result"
        )
    }
}
