package utils.math.euclidean

import kotlin.math.max
import kotlin.math.min

private const val DIMENSIONALITY = 2

private data class VerticalEdge(
    val xCoordinate: Double,
    val startYCoordinate: Double,
    val endYCoordinate: Double,
) {
    fun intersectsAtY(yCoordinate: Double): Boolean {
        return yCoordinate in startYCoordinate..endYCoordinate
    }
}

class SweeplineDecomposition(coordinates: List<Point>) {
    private val loopCoordinates: List<Point>
    private val verticalEdges: List<VerticalEdge>

    init {
        require(coordinates.isNotEmpty())

        val tempLoopCoordinates = mutableListOf<Point>()

        coordinates.forEach {
            if (it.dimensionality != DIMENSIONALITY) {
                throw IllegalArgumentException("All coordinates should have dimensionality of $DIMENSIONALITY")
            }

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

    private fun areCardinallyCollinear(vararg points: Point): Boolean {
        if (points.size < 2) {
            return true
        }

        return (0..<points[0].dimensionality).any { index ->
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
            if (previousCoordinate.coordinates[0] == currentCoordinate.coordinates[0])
                return@mapNotNull VerticalEdge(
                    xCoordinate = previousCoordinate.coordinates[0],
                    startYCoordinate = min(previousCoordinate.coordinates[1], currentCoordinate.coordinates[1]),
                    endYCoordinate = max(previousCoordinate.coordinates[1], currentCoordinate.coordinates[1]),
                )

            null
        }.sortedBy { it.xCoordinate }
    }

    fun sweep(): Set<Rectangle> {
        val yCoordinates = loopCoordinates.map { it.coordinates[1] }.toSet().sorted()

        val yCoordinatePairs = yCoordinates.zip(yCoordinates.slice(1..yCoordinates.lastIndex))

        val slabs = yCoordinatePairs.flatMap { (previousYCoordinate, currentYCoordinate) ->
            val betweenPoint = (previousYCoordinate + currentYCoordinate) / 2.0

            val xCoordinates = verticalEdges.mapNotNull { edge ->
                if (edge.intersectsAtY(betweenPoint)) {
                    return@mapNotNull edge.xCoordinate
                }

                null
            }

            require(xCoordinates.size % 2 == 0)

            (0..<xCoordinates.size / 2).map {
                val startXCoordinate = xCoordinates[2 * it]
                val endXCoordinate = xCoordinates[2 * it + 1]

                Rectangle(
                    topLeftCorner = Point(startXCoordinate, currentYCoordinate),
                    bottomRightCorner = Point(endXCoordinate, previousYCoordinate),
                )
            }
        }

        return slabs.toSet()
    }
}
