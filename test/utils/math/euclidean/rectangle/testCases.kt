package utils.math.euclidean.rectangle

import utils.math.euclidean.Rectangle
import utils.math.euclidean.point.Point2d

data class InvalidConstructorTestCase(
    val topLeftCorner: Point2d,
    val bottomRightCorner: Point2d,
)

data class FromOppositeCornersTestCase(
    val corner1: Point2d,
    val corner2: Point2d,
    val expected: Rectangle,
)

data class AreaTestCase(
    val rectangle: Rectangle,
    val expected: Double,
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
