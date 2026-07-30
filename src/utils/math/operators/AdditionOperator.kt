package utils.math.operators

class AdditionOperator : Operator() {
    override val neutralElement = 0L

    override fun calculate(vararg operands: Long): Long {
        return operands.sum()
    }
}
