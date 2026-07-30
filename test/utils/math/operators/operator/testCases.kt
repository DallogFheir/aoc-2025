package utils.math.operators.operator

import utils.math.operators.Operator

data class FromSymbolTestCase<T : Operator>(
    val symbol: String,
    val expectedClass: Class<T>,
)

data class InvalidFromSymbolTestCase(
    val symbol: String,
)
