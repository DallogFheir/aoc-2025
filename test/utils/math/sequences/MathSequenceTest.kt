package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DummyMathSequence : MathSequence(firstElement = 0L) {
    override fun doGetNthNumber(n: Int): Long {
        return 0L
    }

    override fun doGetNFirstNumbersSum(n: Int): Long {
        return 0L
    }
}

data class MathSequenceTestCase(
    val n: Int
)

class MathSequenceTest {
    companion object {
        @JvmStatic
        fun invalidGetNthNumberCases() = listOf(
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
    @MethodSource("invalidGetNthNumberCases")
    fun `throws if nth number index is not positive`(case: MathSequenceTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNthNumber(case.n)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGetFirstNumbersSumCases")
    fun `throws if number of elements to sum is not positive`(case: MathSequenceTestCase) {
        val cut = DummyMathSequence()

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNFirstNumbersSum(case.n)
        }
    }
}
