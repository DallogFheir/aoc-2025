package day2.range

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SameLengthRangeTest {
    companion object {
        @JvmStatic
        fun constructorInvalidCases() = listOf(
            Pair(1, 10),
            Pair(10, 10_000),
        )
    }

    @ParameterizedTest
    @MethodSource("constructorInvalidCases")
    fun `raises if initialized with invalid range ends`(case: Pair<Int, Int>) {
        val (start, end) = case

        assertThrows(IllegalArgumentException::class.java) {
            SameLengthRange(start = start, end = end)
        }
    }
}
