package utils.math.sequences

class ArithmeticSequence(firstElement: Double, val difference: Double) : MathSequence(firstElement = firstElement) {
    override fun doGetNthElement(n: Int): Double {
        return firstElement + difference * (n - 1)
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        val lastElement = getNthElement(n)

        return (firstElement + lastElement) * n / 2
    }

    override fun getIndexOfElement(element: Double): Int {
        val index = (element - firstElement) / difference + 1

        if (index % 1.0 != 0.0) {
            throw IllegalArgumentException("$element is not an element in sequence")
        }

        return index.toInt()
    }
}
