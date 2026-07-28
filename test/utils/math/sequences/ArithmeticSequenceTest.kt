package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class ArithmeticSequenceTestCase(
    val firstElement: Double,
    val difference: Double,
    val n: Int,
    val expected: Double? = null,
)

data class ArithmeticSequenceGetIndexOfElementTestCase(
    val firstElement: Double,
    val difference: Double,
    val element: Double,
    val expected: Int,
)

data class ArithmeticSequenceInvalidGetIndexOfElementTestCase(
    val firstElement: Double,
    val difference: Double,
    val element: Double,
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

        @JvmStatic
        fun getIndexOfElementCases() = listOf(
            ArithmeticSequenceGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 8.0,
                expected = 3,
            ),
            ArithmeticSequenceGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 1538.0,
                expected = 513,
            ),
            ArithmeticSequenceGetIndexOfElementTestCase(
                firstElement = -3.0,
                difference = 2.0,
                element = 1.0,
                expected = 3,
            ),
            ArithmeticSequenceGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 0.0,
                element = 2.0,
                expected = 1,
            ),
        )

        @JvmStatic
        fun invalidGetIndexOfElementCases() = listOf(
            ArithmeticSequenceInvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = -10.0,
            ),
            ArithmeticSequenceInvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 3.0,
                element = 1539.0,
            ),
            ArithmeticSequenceInvalidGetIndexOfElementTestCase(
                firstElement = 2.0,
                difference = 0.0,
                element = 3.0,
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

    @ParameterizedTest
    @MethodSource("getIndexOfElementCases")
    fun `gets index of a valid element correctly`(case: ArithmeticSequenceGetIndexOfElementTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        val result = cut.getFirstIndexOfElement(case.element)

        assertEquals(
            case.expected,
            result,
            "getIndexOfElement for first element ${case.firstElement}, difference ${case.difference} and element ${case.element} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetIndexOfElementCases")
    fun `throws when trying to get index of invalid element`(case: ArithmeticSequenceInvalidGetIndexOfElementTestCase) {
        val cut = ArithmeticSequence(
            firstElement = case.firstElement,
            difference = case.difference,
        )

        assertThrows(IllegalArgumentException::class.java) {
            cut.getFirstIndexOfElement(case.element)
        }
    }
}
