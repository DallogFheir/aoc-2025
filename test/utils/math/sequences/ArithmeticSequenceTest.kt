package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class ArithmeticSequenceTestCase(
    val firstElement: Double,
    val difference: Double,
    val n: Int,
    val expected: Double? = null,
)

class ArithmeticSequenceTest {
    companion object {
        @JvmStatic
        fun getNthElementCases() = listOf(
            ArithmeticSequenceTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 1,
                expected = 2.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 2,
                expected = 5.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 5,
                expected = 14.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 5.0,
                difference = 0.0,
                n = 10,
                expected = 5.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 3.0,
                difference = -2.0,
                n = 4,
                expected = -3.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = -3.0,
                difference = 2.0,
                n = 4,
                expected = 3.0,
            ),
        )

        @JvmStatic
        fun getNFirstElementsSumCases() = listOf(
            ArithmeticSequenceTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 1,
                expected = 2.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 4,
                expected = 26.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 5.0,
                difference = 0.0,
                n = 4,
                expected = 20.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = 3.0,
                difference = -2.0,
                n = 5,
                expected = -5.0,
            ),
            ArithmeticSequenceTestCase(
                firstElement = -3.0,
                difference = 2.0,
                n = 4,
                expected = 0.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthElementCases")
    fun `gets nth element correctly`(case: ArithmeticSequenceTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNthElement(case.n)

        assertEquals(
            case.expected,
            result,
            "getNthElement for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("getNFirstElementsSumCases")
    fun `gets sum of first n elements correctly`(case: ArithmeticSequenceTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNFirstElementsSum(case.n)

        assertEquals(
            case.expected,
            result,
            "getNFirstElementsSum for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }
}
