package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class GeometricSequenceTestCase(
    val firstElement: Double,
    val ratio: Double,
    val n: Int,
    val expected: Double? = null,
)

class GeometricSequenceTest {
    companion object {
        @JvmStatic
        fun getNthElementCases() = listOf(
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 2,
                expected = 6.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 5,
                expected = 162.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 5.0,
                ratio = 1.0,
                n = 10,
                expected = 5.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 1,
                expected = 3.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 4,
                expected = 0.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = -2.0,
                n = 4,
                expected = -16.0,
            ),
            GeometricSequenceTestCase(
                firstElement = -3.0,
                ratio = 2.0,
                n = 4,
                expected = -24.0,
            ),
        )

        @JvmStatic
        fun getNFirstElementsSumCases() = listOf(
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 4,
                expected = 80.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 5.0,
                ratio = 1.0,
                n = 4,
                expected = 20.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 5,
                expected = 3.0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2.0,
                ratio = -2.0,
                n = 4,
                expected = -10.0,
            ),
            GeometricSequenceTestCase(
                firstElement = -3.0,
                ratio = 2.0,
                n = 4,
                expected = -45.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthElementCases")
    fun `gets nth element correctly`(case: GeometricSequenceTestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        val result = cut.getNthElement(case.n)

        assertEquals(
            case.expected,
            result,
            "getNthElement for first element ${case.firstElement}, ratio ${case.ratio} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("getNFirstElementsSumCases")
    fun `gets sum of first n elements correctly`(case: GeometricSequenceTestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        val result = cut.getNFirstElementsSum(case.n)

        assertEquals(
            case.expected,
            result,
            "getNFirstElementsSum for first element ${case.firstElement}, ratio ${case.ratio} and n ${case.n} should return ${case.expected}, got $result",
        )
    }
}
