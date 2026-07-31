package utils.math.euclidean.point

data class DistanceToTestCase(
    val coordinates: List<Double>,
    val otherCoordinates: List<Double>,
    val expected: Double,
)

data class InvalidDistanceToTestCase(
    val coordinates: List<Double>,
    val otherCoordinates: List<Double>,
)
