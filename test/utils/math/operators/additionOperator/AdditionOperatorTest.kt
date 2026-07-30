package utils.math.operators.additionOperator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.operators.AdditionOperator

class AdditionOperatorTest {
    companion object {
        @JvmStatic
        fun calculateCases() = listOf(
            CalculateTestCase(
                operands = listOf(),
                expected = 0,
            ),
            CalculateTestCase(
                operands = listOf(1),
                expected = 1,
            ),
            CalculateTestCase(
                operands = listOf(1, 2),
                expected = 3,
            ),
            CalculateTestCase(
                operands = listOf(1, 2, 3),
                expected = 6,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("calculateCases")
    fun `calculates correctly`(case: CalculateTestCase) {
        val cut = AdditionOperator()

        val result = cut.calculate(*case.operands.toLongArray())

        Assertions.assertEquals(case.expected, result)
    }
}
