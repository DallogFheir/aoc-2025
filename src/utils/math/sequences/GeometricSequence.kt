package utils.math.sequences

import kotlin.math.abs
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sign

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

    override fun doGetPossibleFirstIndexOfElement(element: Double): Double {
        if (ratio == 0.0 || firstElement == 0.0) {
            if (element == firstElement) {
                return 1.0
            }

            if (element == 0.0) {
                return 2.0
            }

            throwNotElementOfSequence(element)
        }

        if (ratio == 1.0) {
            if (element == firstElement) {
                return 1.0
            }

            throwNotElementOfSequence(element)
        }

        if (element == 0.0) {
            throwNotElementOfSequence(element)
        }

        val possibleIndex = log(abs(element) / abs(firstElement), base = abs(ratio)) + 1

        if (!isIndexValidForElement(index = possibleIndex, element = element)) {
            throwNotElementOfSequence(element)
        }

        return possibleIndex
    }

    private fun isIndexValidForElement(index: Double, element: Double): Boolean {
        if (ratio > 0.0 || index % 2.0 == 1.0) {
            return sign(element) == sign(firstElement)
        }

        if (index % 2.0 == 0.0) {
            return sign(element) != sign(firstElement)
        }

        return false
    }
}
