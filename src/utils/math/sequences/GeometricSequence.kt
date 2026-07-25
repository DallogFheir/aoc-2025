package utils.math.sequences

import kotlin.math.pow

class GeometricSequence(val firstElement: Long, val ratio: Long) : MathSequence {
    override fun getNthNumber(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        return firstElement * ratio.toDouble().pow(n - 1).toLong()
    }

    override fun getNFirstNumbersSum(n: Int): Long {
        if (n <= 0) {
            throw IllegalArgumentException("n must be positive, got $n")
        }

        if (ratio == 1L) {
            return firstElement * n
        }

        return firstElement * (1 - ratio.toDouble().pow(n).toLong()) / (1 - ratio)
    }
}