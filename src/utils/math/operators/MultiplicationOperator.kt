package utils.math.operators

class MultiplicationOperator : Operator() {
    override val neutralElement = 1L

    override fun calculate(vararg operands: Long): Long {
        return operands.fold(initial = 1L) { product, operand -> product * operand }
    }
}
