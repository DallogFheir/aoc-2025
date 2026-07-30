package utils.grid.coordinate

data class InvalidConstructorTestCase(
    val x: Int,
    val y: Int,
)

data class IsValidTestCase(
    val x: Int,
    val y: Int,
    val expected: Boolean,
)
