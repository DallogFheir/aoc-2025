package utils.math.operators.operator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.operators.AdditionOperator
import utils.math.operators.MultiplicationOperator
import utils.math.operators.Operator

class OperatorTest {
    companion object {
        @JvmStatic
        fun fromSymbolCases() = listOf(
            FromSymbolTestCase(
                symbol = "+",
                expectedClass = AdditionOperator::class.java
            ),
            FromSymbolTestCase(
                symbol = "*",
                expectedClass = MultiplicationOperator::class.java
            )
        )

        @JvmStatic
        fun invalidFromSymbolCases() = listOf(
            InvalidFromSymbolTestCase(
                symbol = "%"
            )
        )
    }

    @ParameterizedTest
    @MethodSource("fromSymbolCases")
    fun `returns object of correct class for given symbol`(case: FromSymbolTestCase<Operator>) {
        val result = Operator.fromSymbol(case.symbol)

        Assertions.assertInstanceOf(case.expectedClass, result)
    }

    @ParameterizedTest
    @MethodSource("invalidFromSymbolCases")
    fun `throws if trying to create operator from invalid symbol`(case: InvalidFromSymbolTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Operator.fromSymbol(case.symbol)
        }
    }
}
