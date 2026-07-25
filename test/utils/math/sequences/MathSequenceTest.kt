package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DummyMathSequence : MathSequence(firstElement = 0L) {
    override fun doGetNthElement(n: Int): Long {
        return 0L
    }

    override fun doGetNFirstElementsSum(n: Int): Long {
        return 0L
    }
}

data class MathSequenceTestCase(
    val n: Int
)

class MathSequenceTest {
    companion object {
        @JvmStatic
        fun invalidGetNthElementCases() = listOf(
            MathSequenceTestCase(n = 0),
            MathSequenceTestCase(n = -1),
        )

        @JvmStatic
        fun invalidGetFirstNumbersSumCases() = listOf(
            MathSequenceTestCase(n = 0),
            MathSequenceTestCase(n = -1),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetNthElementCases")
    fun `throws if nth number index is not positive`(case: MathSequenceTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNthElement(case.n)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGetFirstNumbersSumCases")
    fun `throws if number of elements to sum is not positive`(case: MathSequenceTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNFirstElementsSum(case.n)
        }
    }
}
