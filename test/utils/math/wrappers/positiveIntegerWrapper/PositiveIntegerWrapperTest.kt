package utils.math.wrappers.positiveIntegerWrapper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.wrappers.PositiveIntegerWrapper

class PositiveIntegerWrapperTest {
    companion object {
        @JvmStatic
        fun invalidConstructorCases() = listOf(
            InvalidConstructorTestCase(number = -1L),
            InvalidConstructorTestCase(number = 0L),
        )

        @JvmStatic
        fun factorizeCases() = listOf(
            FactorizeTestCase(
                number = 1L,
                expected = listOf(1L),
            ),
            FactorizeTestCase(
                number = 2L,
                expected = listOf(1L, 2L),
            ),
            FactorizeTestCase(
                number = 6L,
                expected = listOf(1L, 2L, 3L, 6L),
            ),
            FactorizeTestCase(
                number = 2147483647L,
                expected = listOf(1L, 2147483647L),
            ),
            FactorizeTestCase(
                number = 30000L,
                expected = listOf(
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
                ),
            ),
        )

        @JvmStatic
        fun divideDigitsIntoEqualGroupsCases() = listOf(
            DivideDigitsIntoEqualGroupsTestCase(
                number = 123L,
                groupSize = 1,
                expected = listOf(1L, 2L, 3L),
            ),
            DivideDigitsIntoEqualGroupsTestCase(
                number = 123L,
                groupSize = 3,
                expected = listOf(123L),
            ),
            DivideDigitsIntoEqualGroupsTestCase(
                number = 123456L,
                groupSize = 2,
                expected = listOf(12L, 34L, 56L),
            ),
            DivideDigitsIntoEqualGroupsTestCase(
                number = 123456L,
                groupSize = 3,
                expected = listOf(123L, 456L),
            ),
        )

        @JvmStatic
        fun invalidGroupSizeDivideDigitsIntoEqualGroupsCases() = listOf(
            InvalidGroupSizeDivideDigitsIntoEqualGroupsTestCase(groupSize = -1),
            InvalidGroupSizeDivideDigitsIntoEqualGroupsTestCase(groupSize = 0),
        )

        @JvmStatic
        fun invalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsCases() = listOf(
            InvalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsTestCase(
                number = 123L,
                groupSize = 2,
            ),
            InvalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsTestCase(
                number = 123456L,
                groupSize = 4,
            ),
        )

        @JvmStatic
        fun invalidToShiftedRightCases() = listOf(
            InvalidToShiftedRightTestCase(number = 1L),
            InvalidToShiftedRightTestCase(number = 10L),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `throws if initialized with non-positive integer`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            PositiveIntegerWrapper(case.number)
        }
    }

    @Test
    fun `withAppendedDigits returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(1L)

        val result = cut.withAppendedDigit(2)

        Assertions.assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @Test
    fun `toShiftedRight returns PositiveIntegerWrapper`() {
        val cut = PositiveIntegerWrapper(12L)

        val result = cut.toShiftedRight()

        Assertions.assertInstanceOf(PositiveIntegerWrapper::class.java, result)
    }

    @ParameterizedTest
    @MethodSource("factorizeCases")
    fun `factorizes correctly`(case: FactorizeTestCase) {
        val cut = PositiveIntegerWrapper(case.number)

        val result = cut.factorize()

        Assertions.assertEquals(
            case.expected,
            result,
            "factorize for ${case.number} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("divideDigitsIntoEqualGroupsCases")
    fun `divides digits into groups correctly`(case: DivideDigitsIntoEqualGroupsTestCase) {
        val cut = PositiveIntegerWrapper(case.number)

        val result = cut.divideDigitsIntoEqualGroups(case.groupSize)

        Assertions.assertEquals(
            case.expected,
            result,
            "divideDigitsIntoEqualGroups for ${case.number} and group size ${case.groupSize} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGroupSizeDivideDigitsIntoEqualGroupsCases")
    fun `throws if trying to divide into non-positive group size`(case: InvalidGroupSizeDivideDigitsIntoEqualGroupsTestCase) {
        val cut = PositiveIntegerWrapper(1L)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(case.groupSize)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsCases")
    fun `throws if trying to divide into group size that isn't a factor of digit count`(case: InvalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsTestCase) {
        val cut = PositiveIntegerWrapper(case.number)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.divideDigitsIntoEqualGroups(case.groupSize)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidToShiftedRightCases")
    fun `throws if trying to do a right shift that results in a non-positive number`(case: InvalidToShiftedRightTestCase) {
        val cut = PositiveIntegerWrapper(case.number)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.toShiftedRight()
        }
    }
}
