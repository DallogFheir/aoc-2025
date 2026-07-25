package utils.math.sequences

abstract class MathSequence(val firstElement: Double) {
    fun getNthElement(n: Int): Double {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNthElement(n)
    }

    protected abstract fun doGetNthElement(n: Int): Double

    fun getNFirstElementsSum(n: Int): Double {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return doGetNFirstElementsSum(n)
    }

    protected abstract fun doGetNFirstElementsSum(n: Int): Double
}
