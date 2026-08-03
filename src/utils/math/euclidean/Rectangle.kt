package utils.math.euclidean

import utils.math.euclidean.point.Point2d
import kotlin.math.max
import kotlin.math.min

private data class Intersection(
    val top: Double,
    val right: Double,
    val bottom: Double,
    val left: Double,
)

data class Rectangle(val topLeftCorner: Point2d, val bottomRightCorner: Point2d) {
    init {
        require(topLeftCorner.isIntegerPoint() && bottomRightCorner.isIntegerPoint()) {
            "Rectangle corners must be have integer coordinates."
        }

        require(topLeftCorner.x <= bottomRightCorner.x && topLeftCorner.y >= bottomRightCorner.y) {
            "Top-left corner must be to the left and to the top of the bottom right corner."
        }
    }

    fun doesOverlapWithRectangle(other: Rectangle): Boolean {
        val intersections = getIntersections(other)

        return intersections.left <= intersections.right && intersections.bottom <= intersections.top
    }

    fun subtract(subtrahend: Rectangle): Set<Rectangle> {
        if (!doesOverlapWithRectangle(subtrahend)) {
            return setOf(this)
        }

        val intersections = getIntersections(subtrahend)

        val result = mutableSetOf<Rectangle>()

        addIntersectionOfCornersToSetIfValid(
            left = topLeftCorner.x,
            top = topLeftCorner.y,
            right = intersections.left,
            bottom = bottomRightCorner.y,
            resultSet = result,
        )

        addIntersectionOfCornersToSetIfValid(
            left = intersections.right,
            top = topLeftCorner.y,
            right = bottomRightCorner.x,
            bottom = bottomRightCorner.y,
            resultSet = result,
        )

        addIntersectionOfCornersToSetIfValid(
            left = intersections.left,
            top = topLeftCorner.y,
            right = intersections.right,
            bottom = intersections.top,
            resultSet = result,
        )

        addIntersectionOfCornersToSetIfValid(
            left = intersections.left,
            top = intersections.bottom,
            right = intersections.right,
            bottom = bottomRightCorner.y,
            resultSet = result,
        )

        return result.toSet()
    }

    private fun getIntersections(other: Rectangle): Intersection {
        val top = min(topLeftCorner.y, other.topLeftCorner.y)
        val right = min(bottomRightCorner.x, other.bottomRightCorner.x)
        val bottom = max(bottomRightCorner.y, other.bottomRightCorner.y)
        val left = max(topLeftCorner.x, other.topLeftCorner.x)

        return Intersection(
            top = top,
            right = right,
            bottom = bottom,
            left = left,
        )
    }

    private fun addIntersectionOfCornersToSetIfValid(
        left: Double,
        top: Double,
        right: Double,
        bottom: Double,
        resultSet: MutableSet<Rectangle>
    ) {
        if (left < right && bottom < top) {
            resultSet += Rectangle(
                topLeftCorner = Point2d(x = left, y = top),
                bottomRightCorner = Point2d(x = right, y = bottom),
            )
        }
    }
}
