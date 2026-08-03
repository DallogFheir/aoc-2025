package utils.math.euclidean

import utils.math.euclidean.point.Point2d
import kotlin.math.max
import kotlin.math.min

private data class VerticalEdge(
    val xCoordinate: Double,
    val startYCoordinate: Double,
    val endYCoordinate: Double,
) {
    fun intersectsAtY(yCoordinate: Double): Boolean {
        return yCoordinate in startYCoordinate..endYCoordinate
    }
}

class SweeplineDecomposition(coordinates: List<Point2d>) {
    private val loopCoordinates: List<Point2d>
    private val verticalEdges: List<VerticalEdge>

    init {
        require(coordinates.isNotEmpty())

        val tempLoopCoordinates = mutableListOf<Point2d>()

        coordinates.forEach {
            val secondToLastCoordinate = tempLoopCoordinates.getOrNull(tempLoopCoordinates.size - 2)
            val lastCoordinate = tempLoopCoordinates.lastOrNull()

            if (lastCoordinate != null && it == lastCoordinate) {
                return@forEach
            }

            if (lastCoordinate != null && !areCardinallyCollinear(lastCoordinate, it)) {
                throw IllegalArgumentException("Subsequent coordinates are not collinear")
            }

            if (lastCoordinate != null && secondToLastCoordinate != null && areCardinallyCollinear(
                    secondToLastCoordinate,
                    lastCoordinate,
                    it
                )
            ) {
                tempLoopCoordinates.removeLast()
            }

            tempLoopCoordinates.add(it)
        }

        val firstCoordinate = tempLoopCoordinates.first()
        val lastCoordinate = tempLoopCoordinates.last()

        if (!areCardinallyCollinear(firstCoordinate, lastCoordinate)) {
            throw IllegalArgumentException("Last coordinate is not collinear with first coordinate")
        }

        loopCoordinates = tempLoopCoordinates.toList()

        verticalEdges = buildVerticalEdges()
    }

    private fun areCardinallyCollinear(vararg points: Point2d): Boolean {
        return (0..<points.first().dimensionality).any { index ->
            points.zip(points.slice(1..points.lastIndex)).all { pointPair ->
                val (previousPoint, currentPoint) = pointPair

                previousPoint.coordinates[index] == currentPoint.coordinates[index]
            }
        }
    }

    private fun buildVerticalEdges(): List<VerticalEdge> {
        val pointPairs =
            loopCoordinates.zip(loopCoordinates.slice(1..loopCoordinates.lastIndex)) + listOf(loopCoordinates.last() to loopCoordinates.first())

        return pointPairs.mapNotNull { (previousCoordinate, currentCoordinate) ->
            if (previousCoordinate.x == currentCoordinate.x)
                return@mapNotNull VerticalEdge(
                    xCoordinate = previousCoordinate.x,
                    startYCoordinate = min(previousCoordinate.y, currentCoordinate.y),
                    endYCoordinate = max(previousCoordinate.y, currentCoordinate.y),
                )

            null
        }.sortedBy { it.xCoordinate }
    }

    fun sweep(): Set<Rectangle> {
        val yCoordinates = loopCoordinates.map { it.y }.toSet().sorted()

        val yCoordinatePairs = yCoordinates.zip(yCoordinates.slice(1..yCoordinates.lastIndex))

        val slabs = yCoordinatePairs.flatMap { (previousYCoordinate, currentYCoordinate) ->
            val betweenPoint = (previousYCoordinate + currentYCoordinate) / 2.0

            val xCoordinates = verticalEdges.mapNotNull { edge ->
                if (edge.intersectsAtY(betweenPoint)) {
                    return@mapNotNull edge.xCoordinate
                }

                null
            }

            (0..<xCoordinates.size / 2).map {
                val startXCoordinate = xCoordinates[2 * it]
                val endXCoordinate = xCoordinates[2 * it + 1]

                Rectangle(
                    topLeftCorner = Point2d(x = startXCoordinate, y = currentYCoordinate),
                    bottomRightCorner = Point2d(x = endXCoordinate, y = previousYCoordinate),
                )
            }
        }

        return slabs.toSet()
    }
}
