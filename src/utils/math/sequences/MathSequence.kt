package utils.math.sequences

abstract class MathSequence(val firstElement: Long) {
    fun getNthElement(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNthElement(n)
    }

    protected abstract fun doGetNthElement(n: Int): Long

    fun getNFirstElementsSum(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNFirstElementsSum(n)
    }

    protected abstract fun doGetNFirstElementsSum(n: Int): Long
}
