package utils.math.sequences

abstract class MathSequence(val firstElement: Long) {
    fun getNthNumber(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNthNumber(n)
    }

    protected abstract fun doGetNthNumber(n: Int): Long

    fun getNFirstNumbersSum(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNFirstNumbersSum(n)
    }

    protected abstract fun doGetNFirstNumbersSum(n: Int): Long
}
