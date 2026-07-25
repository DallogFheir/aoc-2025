package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class ArithmeticSequenceTestCase(
    val firstElement: Long,
    val difference: Long,
    val n: Int,
    val expected: Long? = null,
)

class ArithmeticSequenceTest {
    companion object {
        @JvmStatic
        fun getNthNumberCases() = listOf(
            ArithmeticSequenceTestCase(
                firstElement = 2,
                difference = 3,
                n = 1,
                expected = 2,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2,
                difference = 3,
                n = 2,
                expected = 5,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2,
                difference = 3,
                n = 5,
                expected = 14,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 5,
                difference = 0,
                n = 10,
                expected = 5,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 3,
                difference = -2,
                n = 4,
                expected = -3,
            ),
            ArithmeticSequenceTestCase(
                firstElement = -3,
                difference = 2,
                n = 4,
                expected = 3,
            ),
        )

        @JvmStatic
        fun getNFirstNumbersSumCases() = listOf(
            ArithmeticSequenceTestCase(
                firstElement = 2,
                difference = 3,
                n = 1,
                expected = 2,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2,
                difference = 3,
                n = 4,
                expected = 26,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 5,
                difference = 0,
                n = 4,
                expected = 20,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 3,
                difference = -2,
                n = 5,
                expected = -5,
            ),
            ArithmeticSequenceTestCase(
                firstElement = -3,
                difference = 2,
                n = 4,
                expected = 0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthNumberCases")
    fun `gets nth number correctly`(case: ArithmeticSequenceTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNthNumber(case.n)

        assertEquals(
            case.expected,
            result,
            "getNthNumber for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("getNFirstNumbersSumCases")
    fun `gets sum of first n numbers correctly`(case: ArithmeticSequenceTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNFirstNumbersSum(case.n)

        assertEquals(
            case.expected,
            result,
            "getNFirstNumbersSum for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }
}
