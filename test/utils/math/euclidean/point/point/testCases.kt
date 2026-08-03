package utils.math.euclidean.point.point

data class ToStringTestCase(
    val coordinates: List<Double>,
    val expected: String,
)

data class DistanceToTestCase(
    val coordinates: List<Double>,
    val otherCoordinates: List<Double>,
    val expected: Double,
)

data class InvalidDistanceToTestCase(
    val coordinates: List<Double>,
    val otherCoordinates: List<Double>,
)

data class IsIntegerPointTestCase(
    val coordinates: List<Double>,
    val expected: Boolean,
)
