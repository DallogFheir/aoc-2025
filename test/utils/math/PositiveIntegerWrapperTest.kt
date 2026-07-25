package utils.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PositiveIntegerWrapperTest {
    companion object {
        @JvmStatic
        fun constructorInvalidCases() = listOf(-1, 0)

        @JvmStatic
        fun factorizeCases() = listOf(
            Pair(1, listOf(1)),
            Pair(2, listOf(1, 2)),
            Pair(6, listOf(1, 2, 3, 6)),
            Pair(2147483647, listOf(1, 2147483647)),
            Pair(
                30000,
                listOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    8,
                    10,
                    12,
                    15,
                    16,
                    20,
                    24,
                    25,
                    30,
                    40,
                    48,
                    50,
                    60,
                    75,
                    80,
                    100,
                    120,
                    125,
                    150,
                    200,
                    240,
                    250,
                    300,
                    375,
                    400,
                    500,
                    600,
                    625,
                    750,
                    1000,
                    1200,
                    1250,
                    1500,
                    1875,
                    2000,
                    2500,
                    3000,
                    3750,
                    5000,
                    6000,
                    7500,
                    10000,
                    15000,
                    30000,
                )
            )
        )

        @JvmStatic
        fun divideDigitsIntoEqualGroupsValidCases() = listOf(
            Triple(123, 1, listOf(1, 2, 3)),
            Triple(123, 3, listOf(123)),
            Triple(123456, 2, listOf(12, 34, 56)),
            Triple(123456, 3, listOf(123, 456))
        )

        @JvmStatic
        fun divideDigitsIntoEqualGroupsInvalidGroupSizeCases() = listOf(
            -1,
            0,
        )

        @JvmStatic
        fun divideDigitsIntoEqualGroupsGroupSizeNotFactorCases() = listOf(
            Pair(123, 2),
            Pair(123456, 4),
        )
    }

    @ParameterizedTest
    @MethodSource("constructorInvalidCases")
    fun `throws if initialized with non-positive integer`(number: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            PositiveIntegerWrapper(number)
        }
    }

    @Test
    fun `withAppendedDigits returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(1)

        val result = cut.withAppendedDigit(2)

        assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @Test
    fun `toShiftedRight returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(12)

        val result = cut.toShiftedRight()

        assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @ParameterizedTest
    @MethodSource("factorizeCases")
    fun `factorizes correctly`(case: Pair<Int, List<Int>>) {
        val (number, expected) = case

        val cut = PositiveIntegerWrapper(number)

        val result = cut.factorize()

        assertEquals(expected, result, "factorize for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("divideDigitsIntoEqualGroupsValidCases")
    fun `divides digits into groups correctly`(case: Triple<Int, Int, List<Int>>) {
        val (number, groupSize, expected) = case

        val cut = PositiveIntegerWrapper(number)

        val result = cut.divideDigitsIntoEqualGroups(groupSize)

        assertEquals(expected, result, "divideDigitsIntoEqualGroups for $number should return $expected, got $result")
    }

    @ParameterizedTest
    @MethodSource("divideDigitsIntoEqualGroupsInvalidGroupSizeCases")
    fun `throws if trying to divide into non-positive group size`(groupSize: Int) {
        val cut = PositiveIntegerWrapper(1)

        assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(groupSize)
        }
    }

    @ParameterizedTest
    @MethodSource("divideDigitsIntoEqualGroupsGroupSizeNotFactorCases")
    fun `throws if trying to divide into group size that isn't a factor of digit count`(case: Pair<Int, Int>) {
        val (number, groupSize) = case

        val cut = PositiveIntegerWrapper(number)

        assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(groupSize)
        }
    }
}
