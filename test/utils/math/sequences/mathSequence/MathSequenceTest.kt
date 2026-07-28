package utils.math.sequences.mathSequence

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
            InvalidDoGetPossibleFirstIndexOfElementTestCase(element = InvalidElementForDoGetPossibleFirstIndexOfElement.ELEMENT_FOR_NON_POSITIVE_INDEX),
            InvalidDoGetPossibleFirstIndexOfElementTestCase(element = InvalidElementForDoGetPossibleFirstIndexOfElement.ELEMENT_FOR_NON_INTEGER_INDEX),
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
}
