package utils.math.operators.multiplicationOperator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.operators.MultiplicationOperator

class MultiplicationOperatorTest {
    companion object {
        @JvmStatic
        fun calculateCases() = listOf(
            CalculateTestCase(
                operands = listOf(),
                expected = 1,
            ),
            CalculateTestCase(
                operands = listOf(1),
                expected = 1,
            ),
            CalculateTestCase(
                operands = listOf(1, 2),
                expected = 2,
            ),
            CalculateTestCase(
                operands = listOf(1, 2, 3),
                expected = 6,
            ),
        )
    }

    @Test
    fun `stores neutral element`() {
        val expected = 1L

        val cut = MultiplicationOperator()

        val result = cut.neutralElement

        Assertions.assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculateCases")
    fun `calculates correctly`(case: CalculateTestCase) {
        val cut = MultiplicationOperator()

        val result = cut.calculate(*case.operands.toLongArray())

        Assertions.assertEquals(case.expected, result)
    }
}
