package utils.math.matrices.matrix

data class InvalidConstructorTestCase(
    val matrix: List<List<Int>>,
)

data class ToStringTestCase(
    val matrix: List<List<Int>>,
    val expected: String,
)

data class TransposeTestCase(
    val matrix: List<List<Int>>,
    val expected: List<List<Int>>,
)
