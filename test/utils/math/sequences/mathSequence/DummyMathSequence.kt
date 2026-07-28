package utils.math.sequences.mathSequence

import utils.math.sequences.MathSequence

class DummyMathSequence : MathSequence(firstElement = 0.0) {
    override fun doGetNthElement(n: Int): Double {
        return 0.0
    }

    override fun doGetNFirstElementsSum(n: Int): Double {
        return 0.0
    }

    override fun doGetPossibleFirstIndexOfElement(element: Double): Double {
        return when (element) {
            InvalidElementForDoGetPossibleFirstIndexOfElement.ELEMENT_FOR_NON_POSITIVE_INDEX -> -1.0
            InvalidElementForDoGetPossibleFirstIndexOfElement.ELEMENT_FOR_NON_INTEGER_INDEX -> 1.5
            else -> 1.0
        }
    }
}
