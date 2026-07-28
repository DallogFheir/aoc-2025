package utils.math.sequences

import utils.math.wrappers.IntegerWrapper

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

    fun getFirstIndexOfElement(element: Double): Int {
        val index = doGetPossibleFirstIndexOfElement(element)

        if (index <= 0 || !IntegerWrapper.isInteger(index)) {
            throwNotElementOfSequence(element)
        }

        return index.toInt()
    }

    protected abstract fun doGetPossibleFirstIndexOfElement(element: Double): Double

    protected fun throwNotElementOfSequence(element: Double) {
        throw IllegalArgumentException("$element is not an element in sequence")
    }

    fun getSumBetweenFirstAndElement(element: Double): Double {
        val elementIndex = getFirstIndexOfElement(element)

        return getNFirstElementsSum(elementIndex)
    }
}
