package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class GeometricSequenceTestCase(
    val firstElement: Long,
    val ratio: Long,
    val n: Int,
    val expected: Long? = null,
)

class GeometricSequenceTest {
    companion object {
        @JvmStatic
        fun getNthElementCases() = listOf(
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = 3,
                n = 1,
                expected = 2,
            ),
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = 3,
                n = 2,
                expected = 6,
            ),
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = 3,
                n = 5,
                expected = 162,
            ),
            GeometricSequenceTestCase(
                firstElement = 5,
                ratio = 1,
                n = 10,
                expected = 5,
            ),
            GeometricSequenceTestCase(
                firstElement = 3,
                ratio = 0,
                n = 1,
                expected = 3,
            ),
            GeometricSequenceTestCase(
                firstElement = 3,
                ratio = 0,
                n = 4,
                expected = 0,
            ),
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = -2,
                n = 4,
                expected = -16,
            ),
            GeometricSequenceTestCase(
                firstElement = -3,
                ratio = 2,
                n = 4,
                expected = -24,
            ),
        )

        @JvmStatic
        fun getNFirstElementsSumCases() = listOf(
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = 3,
                n = 1,
                expected = 2,
            ),
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = 3,
                n = 4,
                expected = 80,
            ),
            GeometricSequenceTestCase(
                firstElement = 5,
                ratio = 1,
                n = 4,
                expected = 20,
            ),
            GeometricSequenceTestCase(
                firstElement = 3,
                ratio = 0,
                n = 5,
                expected = 3,
            ),
            GeometricSequenceTestCase(
                firstElement = 2,
                ratio = -2,
                n = 4,
                expected = -10,
            ),
            GeometricSequenceTestCase(
                firstElement = -3,
                ratio = 2,
                n = 4,
                expected = -45,
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
