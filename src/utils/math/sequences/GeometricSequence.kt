package utils.math.sequences

import kotlin.math.pow

class GeometricSequence(firstElement: Double, val ratio: Double) : MathSequence(firstElement = firstElement) {
    override fun doGetNthElement(n: Int): Double {
        return firstElement * ratio.pow(n - 1)
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        if (ratio == 1.0) {
            return firstElement * n
        }

        return firstElement * (1 - ratio.pow(n)) / (1 - ratio)
    }

    override fun getIndexOfElement(element: Double): Int {
        TODO("Not yet implemented")
    }
}
