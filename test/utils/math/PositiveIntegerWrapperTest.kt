package utils.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PositiveIntegerWrapperTest {
    companion object {
        @JvmStatic
        fun constructorInvalidCases() = listOf(-1L, 0L)

        @JvmStatic
        fun factorizeCases() = listOf(
            Pair(1L, listOf(1L)),
            Pair(2L, listOf(1L, 2L)),
            Pair(6L, listOf(1L, 2L, 3L, 6L)),
            Pair(2147483647L, listOf(1L, 2147483647L)),
            Pair(
                30000L,
                listOf(
                    1L,
                    2L,
                    3L,
                    4L,
                    5L,
                    6L,
                    8L,
                    10L,
                    12L,
                    15L,
                    16L,
                    20L,
                    24L,
                    25L,
                    30L,
                    40L,
                    48L,
                    50L,
                    60L,
                    75L,
                    80L,
                    100L,
                    120L,
                    125L,
                    150L,
                    200L,
                    240L,
                    250L,
                    300L,
                    375L,
                    400L,
                    500L,
                    600L,
                    625L,
                    750L,
                    1000L,
                    1200L,
                    1250L,
                    1500L,
                    1875L,
                    2000L,
                    2500L,
                    3000L,
                    3750L,
                    5000L,
                    6000L,
                    7500L,
                    10000L,
                    15000L,
                    30000L,
                )
            )
        )

        @JvmStatic
        fun divideDigitsIntoEqualGroupsCases() = listOf(
            Triple(123L, 1, listOf(1L, 2L, 3L)),
            Triple(123L, 3, listOf(123L)),
            Triple(123456L, 2, listOf(12L, 34L, 56L)),
            Triple(123456L, 3, listOf(123L, 456L))
        )

        @JvmStatic
        fun invalidGroupSizeDivideDigitsIntoEqualGroupsCases() = listOf(
            -1,
            0,
        )

        @JvmStatic
        fun invalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsCases() = listOf(
            Pair(123L, 2),
            Pair(123456L, 4),
        )
    }

    @ParameterizedTest
    @MethodSource("constructorInvalidCases")
    fun `throws if initialized with non-positive integer`(number: Long) {
        assertThrows(IllegalArgumentException::class.java) {
            PositiveIntegerWrapper(number)
        }
    }

    @Test
    fun `withAppendedDigits returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(1L)

        val result = cut.withAppendedDigit(2)

        assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @Test
    fun `toShiftedRight returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(12L)

        val result = cut.toShiftedRight()

        assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @ParameterizedTest
    @MethodSource("factorizeCases")
    fun `factorizes correctly`(case: Pair<Long, List<Long>>) {
        val (number, expected) = case

        val cut = PositiveIntegerWrapper(number)

        val result = cut.factorize()

        assertEquals(expected, result, "factorize for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("divideDigitsIntoEqualGroupsCases")
    fun `divides digits into groups correctly`(case: Triple<Long, Int, List<Long>>) {
        val (number, groupSize, expected) = case

        val cut = PositiveIntegerWrapper(number)

        val result = cut.divideDigitsIntoEqualGroups(groupSize)

        assertEquals(expected, result, "divideDigitsIntoEqualGroups for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("invalidGroupSizeDivideDigitsIntoEqualGroupsCases")
    fun `throws if trying to divide into non-positive group size`(groupSize: Int) {
        val cut = PositiveIntegerWrapper(1L)

        assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(groupSize)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsCases")
    fun `throws if trying to divide into group size that isn't a factor of digit count`(case: Pair<Long, Int>) {
        val (number, groupSize) = case

        val cut = PositiveIntegerWrapper(number)

        assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(groupSize)
        }
    }
}
