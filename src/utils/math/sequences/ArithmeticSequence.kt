package utils.math.sequences

class ArithmeticSequence(firstElement: Long, val difference: Long) : MathSequence(firstElement = firstElement) {
    override fun doGetNthElement(n: Int): Long {
        return firstElement + difference * (n - 1)
    }

    override fun doGetNFirstElementsSum(n: Int): Long {
        val lastElement = getNthElement(n)

        return (firstElement + lastElement) * n / 2
    }
}
