package utils.math.sequences

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

data class TestCase(
    val firstElement: Long,
    val ratio: Long,
    val n: Int,
    val expected: Long? = null,
)

class GeometricSequenceTest {
    companion object {
        @JvmStatic
        fun getNthNumberCases() = listOf(
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 1,
                expected = 2,
            ),
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 2,
                expected = 6,
            ),
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 5,
                expected = 162,
            ),
            TestCase(
                firstElement = 5,
                ratio = 1,
                n = 10,
                expected = 5,
            ),
            TestCase(
                firstElement = 3,
                ratio = 0,
                n = 1,
                expected = 3,
            ),
            TestCase(
                firstElement = 3,
                ratio = 0,
                n = 4,
                expected = 0,
            ),
            TestCase(
                firstElement = 2,
                ratio = -2,
                n = 4,
                expected = -16,
            ),
            TestCase(
                firstElement = -3,
                ratio = 2,
                n = 4,
                expected = -24,
            ),
        )

        @JvmStatic
        fun invalidGetNthNumberCases() = listOf(
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 0,
            ),
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = -1,
            ),
        )

        @JvmStatic
        fun getNFirstNumbersSumCases() = listOf(
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 1,
                expected = 2,
            ),
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 4,
                expected = 80,
            ),
            TestCase(
                firstElement = 5,
                ratio = 1,
                n = 4,
                expected = 20,
            ),
            TestCase(
                firstElement = 3,
                ratio = 0,
                n = 5,
                expected = 3,
            ),
            TestCase(
                firstElement = 2,
                ratio = -2,
                n = 4,
                expected = -10,
            ),
            TestCase(
                firstElement = -3,
                ratio = 2,
                n = 4,
                expected = -45,
            ),
        )

        @JvmStatic
        fun invalidGetFirstNumbersSumCases() = listOf(
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = 0,
            ),
            TestCase(
                firstElement = 2,
                ratio = 3,
                n = -1,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("getNthNumberCases")
    fun `gets nth number correctly`(case: TestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        val result = cut.getNthNumber(case.n)

        assertEquals(
            case.expected,
            result,
            "getNthNumber for first element ${case.firstElement}, ratio ${case.ratio} and n ${case.n} should return ${case.expected}, got $result",
        )
    }

    @ParameterizedTest
    @MethodSource("invalidGetNthNumberCases")
    fun `throws if nth number index is not positive`(case: TestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNthNumber(case.n)
        }
    }

    @ParameterizedTest
    @MethodSource("getNFirstNumbersSumCases")
    fun `gets sum of first n numbers correctly`(case: TestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        val result = cut.getNFirstNumbersSum(case.n)

        assertEquals(
            case.expected,
            result,
            "getNFirstNumbersSum for first element ${case.firstElement}, ratio ${case.ratio} and n ${case.n} should return ${case.expected}, got $result",
        )
    }


    @ParameterizedTest
    @MethodSource("invalidGetFirstNumbersSumCases")
    fun `throws if number of elements to sum is not positive`(case: TestCase) {
        val cut = GeometricSequence(
            firstElement = case.firstElement,
            ratio = case.ratio,
        )

        assertThrows(IllegalArgumentException::class.java) {
            cut.getNFirstNumbersSum(case.n)
        }
    }
}
