package day2.range

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class RangeTest {
    companion object {
        @JvmStatic
        fun constructorInvalidCases() = listOf(
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(3, 1),
        )

        @JvmStatic
        fun fromStringValidCases() = listOf(
            Triple("1-2", 1, 2),
            Triple("1-3000", 1, 3000),
            Triple("1000-3000", 1000, 3000),
        )

        @JvmStatic
        fun fromStringInvalidCases() = listOf(
            "1",
            "a-b",
            "1-2-3",
        )

        @JvmStatic
        fun divideIntoSameLengthSubrangesCases() = listOf(
            Triple(1, 2, listOf(Pair(1, 2))),
            Triple(1, 10, listOf(Pair(1, 9), Pair(10, 10))),
            Triple(150, 10_500, listOf(Pair(150, 999), Pair(1000, 9999), Pair(10_000, 10_500))),
        )
    }

    @ParameterizedTest
    @MethodSource("constructorInvalidCases")
    fun `raises if initialized with invalid range ends`(case: Pair<Long, Long>) {
        val (start, end) = case

        assertThrows(IllegalArgumentException::class.java) {
            Range(start = start, end = end)
        }
    }

    @ParameterizedTest
    @MethodSource("fromStringValidCases")
    fun `initializes from string correctly`(case: Triple<String, Long, Long>) {
        val (string, start, end) = case

        val cut = Range.fromString(string)

        assertEquals(
            cut.start,
            start,
            "fromString for $string should initialize range with $start-$end, got ${cut.start}-${cut.end}"
        )
        assertEquals(
            cut.end,
            end,
            "fromString for $string should initialize range with $start-$end, got ${cut.start}-${cut.end}"
        )
    }

    @ParameterizedTest
    @MethodSource("fromStringInvalidCases")
    fun `raises if initialized from string with strings`(case: String) {
        assertThrows(IllegalArgumentException::class.java) {
            Range.fromString(case)
        }
    }

    @ParameterizedTest
    @MethodSource("divideIntoSameLengthSubrangesCases")
    fun `divides into same length subranges correctly`(case: Triple<Long, Long, List<Pair<Long, Long>>>) {
        val (start, end, expectedSubrangeBounds) = case

        val cut = Range(start = start, end = end)

        val subranges = cut.divideIntoSameLengthSubranges()

        assertEquals(
            expectedSubrangeBounds.size,
            subranges.size,
            "expected ${expectedSubrangeBounds.size} for subrange of range $start-$end but got ${subranges.size}"
        )

        expectedSubrangeBounds.zip(subranges).forEachIndexed { index, value ->
            val (expectedSubrangeBound, subrange) = value
            val (expectedStart, expectedEnd) = expectedSubrangeBound

            assertEquals(
                expectedStart,
                subrange.start,
                "subrange no. $index for range $start-$end should be $expectedStart-$expectedEnd, got ${subrange.start}-${subrange.end}",
            )
            assertEquals(
                expectedEnd,
                subrange.end,
                "subrange no. $index for range $start-$end should be $expectedStart-$expectedEnd, got ${subrange.start}-${subrange.end}",
            )
        }
    }
}
