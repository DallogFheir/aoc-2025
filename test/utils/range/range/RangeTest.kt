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
            FromStringTestCase(string = "1-2", expectedRangeStart = 1L, expectedRangeEnd = 2L),
            FromStringTestCase(string = "1-3000", expectedRangeStart = 1L, expectedRangeEnd = 3000L),
            FromStringTestCase(string = "1000-3000", expectedRangeStart = 1000L, expectedRangeEnd = 3000L),
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
                rangeStart = 1L,
                rangeEnd = 2L,
                expectedSubrangeBoundaries = listOf(SubrangeBoundary(rangeStart = 1L, rangeEnd = 2L))
            ),
            DivideIntoSameLengthSubrangesTestCase(
                rangeStart = 1L,
                rangeEnd = 10L,
                expectedSubrangeBoundaries = listOf(
                    SubrangeBoundary(rangeStart = 1L, rangeEnd = 9L),
                    SubrangeBoundary(rangeStart = 10L, rangeEnd = 10L)
                )
            ),
            DivideIntoSameLengthSubrangesTestCase(
                rangeStart = 150L,
                rangeEnd = 10_500L,
                expectedSubrangeBoundaries = listOf(
                    SubrangeBoundary(rangeStart = 150L, rangeEnd = 999L),
                    SubrangeBoundary(rangeStart = 1000L, rangeEnd = 9999L),
                    SubrangeBoundary(rangeStart = 10_000L, rangeEnd = 10_500L)
                )
            ),
        )

        @JvmStatic
        fun containsCases() = listOf(
            ContainsTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                valueToCheck = 0L,
                expected = false,
            ),
            ContainsTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                valueToCheck = 1L,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                valueToCheck = 2L,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                valueToCheck = 3L,
                expected = true,
            ),
            ContainsTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                valueToCheck = 4L,
                expected = false,
            )
        )

        @JvmStatic
        fun countInRangeCases() = listOf(
            CountInRangeTestCase(
                rangeStart = 1L,
                rangeEnd = 3L,
                expected = 3L,
            ),
            CountInRangeTestCase(
                rangeStart = 1L,
                rangeEnd = 10L,
                expected = 10L,
            ),
            CountInRangeTestCase(
                rangeStart = 50L,
                rangeEnd = 65L,
                expected = 16L,
            ),
            CountInRangeTestCase(
                rangeStart = 50L,
                rangeEnd = 10_741L,
                expected = 10_692L,
            ),
        )

        @JvmStatic
        fun countOverlappingWithRangeCases() = listOf(
            CountOverlappingWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 15L,
                range2End = 20L,
                expected = 0L,
            ),
            CountOverlappingWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 15L,
                expected = 6L,
            ),
            CountOverlappingWithRangeTestCase(
                range1Start = 5L,
                range1End = 15L,
                range2Start = 1L,
                range2End = 10L,
                expected = 6L,
            ),
            CountOverlappingWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 7L,
                expected = 3L,
            ),
        )

        @JvmStatic
        fun doesOverlapWithRangeCases() = listOf(
            DoesOverlapWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 15L,
                range2End = 20L,
                expected = false,
            ),
            DoesOverlapWithRangeTestCase(
                range1Start = 15L,
                range1End = 20L,
                range2Start = 1L,
                range2End = 10L,
                expected = false,
            ),
            DoesOverlapWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 15L,
                expected = true,
            ),
            DoesOverlapWithRangeTestCase(
                range1Start = 5L,
                range1End = 15L,
                range2Start = 1L,
                range2End = 10L,
                expected = true,
            ),
            DoesOverlapWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 7L,
                expected = true,
            ),
        )

        @JvmStatic
        fun mergeWithRangeCases() = listOf(
            MergeWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 15L,
                expectedRangeStart = 1L,
                expectedRangeEnd = 15L,
            ),
            MergeWithRangeTestCase(
                range1Start = 5L,
                range1End = 15L,
                range2Start = 1L,
                range2End = 10L,
                expectedRangeStart = 1L,
                expectedRangeEnd = 15L,
            ),
            MergeWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 5L,
                range2End = 7L,
                expectedRangeStart = 1L,
                expectedRangeEnd = 10L,
            ),
        )

        @JvmStatic
        fun invalidMergeWithRangeCases() = listOf(
            InvalidMergeWithRangeTestCase(
                range1Start = 1L,
                range1End = 10L,
                range2Start = 15L,
                range2End = 20L,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `throws if initialized with invalid range ends`(case: InvalidConstructorTestCase) {
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
    fun `throws if initialized from string with strings`(case: InvalidFromStringTestCase) {
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


    @ParameterizedTest
    @MethodSource("countInRangeCases")
    fun `counts numbers in range correctly`(case: CountInRangeTestCase) {
        val cut = Range(start = case.rangeStart, end = case.rangeEnd)

        val result = cut.countInRange()

        Assertions.assertEquals(
            case.expected,
            result,
            "countInRange for range ${cut.start}-${cut.end} should return ${case.expected}, got $result"
        )
    }


    @ParameterizedTest
    @MethodSource("countOverlappingWithRangeCases")
    fun `counts how many numbers overlap in 2 ranges correctly`(case: CountOverlappingWithRangeTestCase) {
        val cut = Range(start = case.range1Start, end = case.range1End)

        val other = Range(start = case.range2Start, end = case.range2End)

        val result = cut.countOverlappingWithRange(other)

        Assertions.assertEquals(
            case.expected,
            result,
            "countOverlappingWithRange for ranges ${cut.start}-${cut.end} and ${other.start}-${other.end} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("doesOverlapWithRangeCases")
    fun `returns whether 2 ranges overlap correctly`(case: DoesOverlapWithRangeTestCase) {
        val cut = Range(start = case.range1Start, end = case.range1End)

        val other = Range(start = case.range2Start, end = case.range2End)

        val result = cut.doesOverlapWithRange(other)

        Assertions.assertEquals(
            case.expected,
            result,
            "doesOverlapWithRange for ranges ${cut.start}-${cut.end} and ${other.start}-${other.end} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("mergeWithRangeCases")
    fun `merges 2 ranges correctly`(case: MergeWithRangeTestCase) {
        val cut = Range(start = case.range1Start, end = case.range1End)

        val other = Range(start = case.range2Start, end = case.range2End)

        val result = cut.mergeWithRange(other)

        Assertions.assertEquals(
            case.expectedRangeStart,
            result.start,
            "mergeWithRange for ranges ${cut.start}-${cut.end} and ${other.start}-${other.end} should return range  ${case.expectedRangeStart}-${case.expectedRangeEnd}, got ${
                result.start
            }-${result.end}"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidMergeWithRangeCases")
    fun `throws if trying to merge non-overlapping ranges`(case: InvalidMergeWithRangeTestCase) {
        val cut = Range(start = case.range1Start, end = case.range1End)

        val other = Range(start = case.range2Start, end = case.range2End)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.mergeWithRange(other)
        }
    }
}
