package utils.math.operators

abstract class Operator {
    abstract val neutralElement: Long

    abstract fun calculate(vararg operands: Long): Long

    companion object {
        fun fromSymbol(symbol: String): Operator {
            return when (symbol) {
                "+" -> AdditionOperator()
                "*" -> MultiplicationOperator()
                else -> throw IllegalArgumentException("Unknown operator: $symbol")
            }
        }
    }
}
