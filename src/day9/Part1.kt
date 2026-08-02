package day9

import utils.filereader.FileReader
import utils.math.euclidean.Point
import kotlin.math.abs

private const val COORDINATE_SEPARATOR = ","
private const val POINT_DIMENSIONALITY = 2


object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val points = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
            val coordinateStrings = line.split(COORDINATE_SEPARATOR)

            require(coordinateStrings.size == POINT_DIMENSIONALITY)

            val coordinates = coordinateStrings.map { it.toDouble() }

            Point(*coordinates.toDoubleArray())
        }

        val areas = points.flatMapIndexed { index, point ->
            val otherPoints = points.slice(index + 1..points.lastIndex)

            otherPoints.map {
                val width = abs(point.coordinates[0] - it.coordinates[0]) + 1
                val height = abs(point.coordinates[1] - it.coordinates[1]) + 1

                width * height
            }
        }

        return areas.max().toLong()
    }
}
