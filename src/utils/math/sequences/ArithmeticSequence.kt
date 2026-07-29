package utils.math.sequences

class ArithmeticSequence(firstElement: Double, val difference: Double) : MathSequence(firstElement = firstElement) {
    override fun doGetNthElement(n: Int): Double {
        return firstElement + difference * (n - 1)
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        val lastElement = getNthElement(n)

        return (firstElement + lastElement) * n / 2
    }

    override fun doGetPossibleFirstIndexOfElement(element: Double): Double {
        if (difference == 0.0) {
            if (element == firstElement) {
                return 1.0
            }

            throwNotElementOfSequence(element)
        }

        return (element - firstElement) / difference + 1
    }
}
