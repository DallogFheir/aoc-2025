package utils.math.matrices.matrix

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.matrices.Matrix

class MatrixTest {
    companion object {
        @JvmStatic
        fun invalidConstructorCases() = listOf(
            InvalidConstructorTestCase(
                matrix = listOf(),
            ),
            InvalidConstructorTestCase(
                matrix = listOf(listOf()),
            ),
            InvalidConstructorTestCase(
                matrix = listOf(listOf(1), listOf(2, 3)),
            )
        )

        @JvmStatic
        fun toStringCases() = listOf(
            ToStringTestCase(
                matrix = listOf(listOf(1, 2, 3)),
                expected = "[[1, 2, 3]]",
            ),
            ToStringTestCase(
                matrix = listOf(listOf(1), listOf(2), listOf(3)),
                expected = "[[1], [2], [3]]",
            ),
            ToStringTestCase(
                matrix = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)),
                expected = "[[1, 2, 3], [4, 5, 6], [7, 8, 9]]",
            ),
        )

        @JvmStatic
        fun transposeCases() = listOf(
            TransposeTestCase(
                matrix = listOf(listOf(1), listOf(2), listOf(3)),
                expected = listOf(listOf(1, 2, 3)),
            ),
            TransposeTestCase(
                matrix = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)),
                expected = listOf(listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9)),
            )
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `throws if trying to initialize with invalid matrix`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Matrix(case.matrix)
        }
    }

    @ParameterizedTest
    @MethodSource("toStringCases")
    fun `converts to string correctly`(case: ToStringTestCase) {
        val cut = Matrix(case.matrix)

        val result = cut.toString()

        Assertions.assertEquals(case.expected, result)
    }

    @ParameterizedTest
    @MethodSource("transposeCases")
    fun `should transpose correctly`(case: TransposeTestCase) {
        val cut = Matrix(case.matrix)

        val result = cut.transpose().matrix

        Assertions.assertEquals(case.expected, result)
    }
}
