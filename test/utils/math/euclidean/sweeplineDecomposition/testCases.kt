package utils.math.euclidean.sweeplineDecomposition

import utils.math.euclidean.Rectangle
import utils.math.euclidean.point.Point2d

data class InvalidConstructorTestCase(
    val coordinates: List<Point2d>
)

data class SweepTestCase(
    val coordinates: List<Point2d>,
    val expected: Set<Rectangle>,
)
