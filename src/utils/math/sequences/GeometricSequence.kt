package utils.math.sequences

import kotlin.math.pow

class GeometricSequence(firstElement: Long, val ratio: Long) : MathSequence(firstElement = firstElement) {
    override fun doGetNthNumber(n: Int): Long {
        return firstElement * ratio.toDouble().pow(n - 1).toLong()
    }

    override fun doGetNFirstNumbersSum(n: Int): Long {
        if (ratio == 1L) {
            return firstElement * n
        }

        return firstElement * (1 - ratio.toDouble().pow(n).toLong()) / (1 - ratio)
    }
}