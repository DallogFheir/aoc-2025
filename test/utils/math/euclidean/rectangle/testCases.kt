package utils.math.euclidean.rectangle

import utils.math.euclidean.Rectangle
import utils.math.euclidean.point.Point2d

data class InvalidConstructorTestCase(
    val topLeftCorner: Point2d,
    val bottomRightCorner: Point2d,
)

data class DoesOverlapWithRectangleTestCase(
    val rectangle: Rectangle,
    val otherRectangle: Rectangle,
    val expected: Boolean,
)

data class SubtractTestCase(
    val rectangle: Rectangle,
    val subtrahend: Rectangle,
    val expected: Set<Rectangle>,
)
