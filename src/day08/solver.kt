package day08

import utils.disjointSetForest.DisjointSetForest
import utils.filereader.FileReader
import utils.math.euclidean.point.Point3d

private const val COORDINATE_SEPARATOR = ","
private const val POINT_DIMENSIONALITY = 3

data class PointPairWithDistance(
    val point1: Point3d,
    val point2: Point3d,
    val distance: Double,
)

fun solveForDistancesAndCircuits(
    dayNumber: Int,
    fileName: String,
    callback: (distances: List<PointPairWithDistance>, circuits: DisjointSetForest<Point3d>) -> Long
): Long {
    val points = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
        val coordinateStrings = line.split(COORDINATE_SEPARATOR)

        require(coordinateStrings.size == POINT_DIMENSIONALITY)

        val coordinates = coordinateStrings.map { it.toDouble() }

        Point3d(x = coordinates[0], y = coordinates[1], z = coordinates[2])
    }
    val circuits = DisjointSetForest<Point3d>()
    points.forEach {
        circuits.makeSetFor(it)
    }

    val distances = points.flatMapIndexed { index, point ->
        val otherPoints = points.slice(index + 1..points.lastIndex)

        otherPoints.map {
            PointPairWithDistance(point1 = point, point2 = it, distance = point.distanceTo(it))
        }
    }

    val sortedDistances = distances.sortedBy { it.distance }

    return callback(sortedDistances, circuits)
}
