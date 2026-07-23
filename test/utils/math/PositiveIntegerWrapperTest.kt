package utils.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PositiveIntegerWrapperTest {
    companion object {
        @JvmStatic
        fun constructorCases() = listOf(-1, 0)

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
    }

    @ParameterizedTest
    @MethodSource("constructorCases")
    fun `throws if initialized with non-positive integer`(number: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            PositiveIntegerWrapper(number)
        }
    }

    @ParameterizedTest
    @MethodSource("factorizeCases")
    fun `factorizes correctly`(case: Pair<Int, List<Int>>) {
        val (number, expected) = case

        val cut = PositiveIntegerWrapper(number)

        val result = cut.factorize()

        assertEquals(expected, result, "factorize for $number should return $expected, got $result")
    }
}
