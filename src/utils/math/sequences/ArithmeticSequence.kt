package utils.math.sequences

class ArithmeticSequence(firstElement: Double, val difference: Double) : MathSequence(firstElement = firstElement) {
    override fun doGetNthElement(n: Int): Double {
        return firstElement + difference * (n - 1)
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        val lastElement = getNthElement(n)

        return (firstElement + lastElement) * n / 2
    }
}
