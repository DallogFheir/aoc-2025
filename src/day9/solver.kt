package day9

import utils.filereader.FileReader
import utils.math.euclidean.Rectangle
import utils.math.euclidean.point.Point2d

private const val COORDINATE_SEPARATOR = ","
private const val POINT_DIMENSIONALITY = 2

fun solveForPointsAndPossibleRectangles(
    dayNumber: Int,
    fileName: String,
    callback: (points: List<Point2d>, possibleRectangles: List<Rectangle>) -> Long
): Long {
    val points = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
        val coordinateStrings = line.split(COORDINATE_SEPARATOR)

        require(coordinateStrings.size == POINT_DIMENSIONALITY)

        val coordinates = coordinateStrings.map { it.toDouble() }

        Point2d(x = coordinates[0], y = coordinates[1])
    }

    val rectangles = points.flatMapIndexed { index, point ->
        val otherPoints = points.slice(index + 1..points.lastIndex)

        otherPoints.map { otherPoint ->
            Rectangle.fromOppositeCorners(
                point, otherPoint
            )
        }
    }

    return callback(points, rectangles)
}
