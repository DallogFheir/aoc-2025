package utils.math.sequences.mathSequence

import utils.math.sequences.MathSequence

class DummyMathSequence : MathSequence(firstElement = 0.0) {
    override fun doGetNthElement(n: Int): Double {
        return n.toDouble()
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        return (n * (n + 1) / 2.0)
    }

    override fun doGetPossibleFirstIndexOfElement(element: Double): Double {
        return element
    }
}
