package utils.math.sequences

class ArithmeticSequence(firstElement: Long, val difference: Long) : MathSequence(firstElement = firstElement) {
    override fun doGetNthNumber(n: Int): Long {
        return firstElement + difference * (n - 1)
    }

    override fun doGetNFirstNumbersSum(n: Int): Long {
        val lastElement = getNthNumber(n)

        return (firstElement + lastElement) * n / 2
    }
}
