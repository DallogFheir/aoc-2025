package utils.range.sameLengthRange

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.range.SameLengthRange

class SameLengthRangeTest {
    companion object {
        @JvmStatic
        fun invalidConstructorCases() = listOf(
            InvalidConstructorTestCase(rangeStart = 1L, rangeEnd = 10L),
            InvalidConstructorTestCase(rangeStart = 10L, rangeEnd = 10_000L),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `raises if initialized with invalid range ends`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            SameLengthRange(start = case.rangeStart, end = case.rangeEnd)
        }
    }
}
