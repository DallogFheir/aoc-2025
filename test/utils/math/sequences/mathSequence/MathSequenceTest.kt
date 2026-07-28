package utils.math.sequences.mathSequence

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MathSequenceTest {
    companion object {
        @JvmStatic
        fun invalidGetNthElementCases() = listOf(
            InvalidGetNthElementTestCase(n = 0),
            InvalidGetNthElementTestCase(n = -1),
        )

        @JvmStatic
        fun invalidGetFirstNumbersSumCases() = listOf(
            InvalidGetNFirstNumbersSumTestCase(n = 0),
            InvalidGetNFirstNumbersSumTestCase(n = -1),
        )

        @JvmStatic
        fun invalidGetFirstIndexOfElementCases() = listOf(
            InvalidDoGetPossibleFirstIndexOfElementTestCase(element = -1.0),
            InvalidDoGetPossibleFirstIndexOfElementTestCase(element = 1.5),
        )

        @JvmStatic
        fun getSumBetweenFirstAndElementCases() = listOf(
            GetSumBetweenFirstAndElementTestCase(element = 1.0, expected = 1.0),
            GetSumBetweenFirstAndElementTestCase(element = 2.0, expected = 3.0),
            GetSumBetweenFirstAndElementTestCase(element = 3.0, expected = 6.0),
        )

        @JvmStatic
        fun invalidGetSumBetweenFirstAndElementCases() = listOf(
            InvalidGetSumBetweenFirstAndElementTestCase(element = 0.0),
            InvalidGetSumBetweenFirstAndElementTestCase(element = -1.0),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetNthElementCases")
    fun `throws if nth number index is not positive`(case: InvalidGetNthElementTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNthElement(case.n)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGetFirstNumbersSumCases")
    fun `throws if number of elements to sum is not positive`(case: InvalidGetNFirstNumbersSumTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNFirstElementsSum(case.n)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGetFirstIndexOfElementCases")
    fun `throws if subclass returns an invalid index`(case: InvalidDoGetPossibleFirstIndexOfElementTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getFirstIndexOfElement(case.element)
        }
    }

    @ParameterizedTest
    @MethodSource("getSumBetweenFirstAndElementCases")
    fun `calculates sum between first and given element correctly`(case: GetSumBetweenFirstAndElementTestCase) {
        val cut = DummyMathSequence()

        val result = cut.getSumBetweenFirstAndElement(case.element)

        assertEquals(
            case.expected,
            result,
            "getSumBetweenFirstAndElement for element ${case.element} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetSumBetweenFirstAndElementCases")
    fun `throws if trying to get sum between first and an invalid element`(case: InvalidGetSumBetweenFirstAndElementTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getSumBetweenFirstAndElement(case.element)
        }
    }
}
