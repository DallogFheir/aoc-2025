package utils.math.sequences.geometricSequence

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.sequences.GeometricSequence

class GeometricSequenceTest {
    companion object {
        @JvmStatic
        fun getNthElementCases() = listOf(
            GetNthElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GetNthElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 2,
                expected = 6.0,
            ),
            GetNthElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 5,
                expected = 162.0,
            ),
            GetNthElementTestCase(
                firstElement = 5.0,
                ratio = 1.0,
                n = 10,
                expected = 5.0,
            ),
            GetNthElementTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 1,
                expected = 3.0,
            ),
            GetNthElementTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 4,
                expected = 0.0,
            ),
            GetNthElementTestCase(
                firstElement = 2.0,
                ratio = -2.0,
                n = 4,
                expected = -16.0,
            ),
            GetNthElementTestCase(
                firstElement = -3.0,
                ratio = 2.0,
                n = 4,
                expected = -24.0,
            ),
        )

        @JvmStatic
        fun getNFirstElementsSumCases() = listOf(
            GetNFirstElementsSumTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                n = 4,
                expected = 80.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 5.0,
                ratio = 1.0,
                n = 4,
                expected = 20.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 3.0,
                ratio = 0.0,
                n = 5,
                expected = 3.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 2.0,
                ratio = -2.0,
                n = 4,
                expected = -10.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = -3.0,
                ratio = 2.0,
                n = 4,
                expected = -45.0,
            ),
        )

        @JvmStatic
        fun getIndexOfElementCases() = listOf(
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                element = 18.0,
                expected = 3,
            ),
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                element = 1062882.0,
                expected = 13,
            ),
            GetIndexOfElementTestCase(
                firstElement = -3.0,
                ratio = -2.0,
                element = -12.0,
                expected = 3,
            ),
            GetIndexOfElementTestCase(
                firstElement = 0.0,
                ratio = 2.0,
                element = 0.0,
                expected = 1,
            ),
            GetIndexOfElementTestCase(
                firstElement = 1.0,
                ratio = 0.0,
                element = 0.0,
                expected = 2,
            ),
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 1.0,
                element = 2.0,
                expected = 1,
            ),
        )

        @JvmStatic
        fun invalidGetIndexOfElementCases() = listOf(
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                element = -10.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                element = 1062883.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 3.0,
                element = 0.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 0.0,
                ratio = 3.0,
                element = 1.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 0.0,
                element = 1.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = 1.0,
                element = 1.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = -2.0,
                ratio = 3.0,
                element = 6.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = -3.0,
                element = 6.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = -3.0,
                element = -18.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                ratio = -3.0,
                element = -19.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthElementCases")
    fun `gets nth element correctly`(case: GetNthElementTestCase) {
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
    fun `gets sum of first n elements correctly`(case: GetNFirstElementsSumTestCase) {
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

    @ParameterizedTest
    @MethodSource("getIndexOfElementCases")
    fun `gets index of a valid element correctly`(case: GetIndexOfElementTestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        val result = cut.getFirstIndexOfElement(case.element)

        assertEquals(
            case.expected,
            result,
            "getIndexOfElement for first element ${case.firstElement}, ratio ${case.ratio} and element ${case.element} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetIndexOfElementCases")
    fun `throws when trying to get index of invalid element`(case: InvalidGetIndexOfElementTestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        assertThrows(IllegalArgumentException::class.java) {
            cut.getFirstIndexOfElement(case.element)
        }
    }
}
