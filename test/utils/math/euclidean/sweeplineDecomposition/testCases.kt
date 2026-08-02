package utils.math.euclidean.sweeplineDecomposition

import utils.math.euclidean.Point
import utils.math.euclidean.Rectangle

data class InvalidConstructorTestCase(
    val coordinates: List<Point>
)

data class SweepTestCase(
    val coordinates: List<Point>,
    val expected: Set<Rectangle>,
)
