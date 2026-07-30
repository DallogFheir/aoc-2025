package utils.math.sequences.arithmeticSequence

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.sequences.ArithmeticSequence

class ArithmeticSequenceTest {
    companion object {
        @JvmStatic
        fun getNthElementCases() = listOf(
            GetNthElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GetNthElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 2,
                expected = 5.0,
            ),
            GetNthElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 5,
                expected = 14.0,
            ),
            GetNthElementTestCase(
                firstElement = 5.0,
                difference = 0.0,
                n = 10,
                expected = 5.0,
            ),
            GetNthElementTestCase(
                firstElement = 3.0,
                difference = -2.0,
                n = 4,
                expected = -3.0,
            ),
            GetNthElementTestCase(
                firstElement = -3.0,
                difference = 2.0,
                n = 4,
                expected = 3.0,
            ),
        )

        @JvmStatic
        fun getNFirstElementsSumCases() = listOf(
            GetNFirstElementsSumTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 1,
                expected = 2.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 2.0,
                difference = 3.0,
                n = 4,
                expected = 26.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 5.0,
                difference = 0.0,
                n = 4,
                expected = 20.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = 3.0,
                difference = -2.0,
                n = 5,
                expected = -5.0,
            ),
            GetNFirstElementsSumTestCase(
                firstElement = -3.0,
                difference = 2.0,
                n = 4,
                expected = 0.0,
            ),
        )

        @JvmStatic
        fun getIndexOfElementCases() = listOf(
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 8.0,
                expected = 3,
            ),
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 1538.0,
                expected = 513,
            ),
            GetIndexOfElementTestCase(
                firstElement = -3.0,
                difference = 2.0,
                element = 1.0,
                expected = 3,
            ),
            GetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 0.0,
                element = 2.0,
                expected = 1,
            ),
        )

        @JvmStatic
        fun invalidGetIndexOfElementCases() = listOf(
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = -10.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 1539.0,
            ),
            InvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 0.0,
                element = 3.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthElementCases")
    fun `gets nth element correctly`(case: GetNthElementTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNthElement(case.n)

        Assertions.assertEquals(
            case.expected,
            result,
            "getNthElement for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("getNFirstElementsSumCases")
    fun `gets sum of first n elements correctly`(case: GetNFirstElementsSumTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getNFirstElementsSum(case.n)

        Assertions.assertEquals(
            case.expected,
            result,
            "getNFirstElementsSum for first element ${case.firstElement}, difference ${case.difference} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("getIndexOfElementCases")
    fun `gets index of a valid element correctly`(case: GetIndexOfElementTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getFirstIndexOfElement(case.element)

        Assertions.assertEquals(
            case.expected,
            result,
            "getIndexOfElement for first element ${case.firstElement}, difference ${case.difference} and element ${case.element} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetIndexOfElementCases")
    fun `throws when trying to get index of invalid element`(case: InvalidGetIndexOfElementTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.getFirstIndexOfElement(case.element)
        }
    }
}
