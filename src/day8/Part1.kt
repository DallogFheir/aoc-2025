package day8

import utils.filereader.FileReader
import utils.math.euclidean.Point
import java.io.File

private const val COORDINATE_SEPARATOR = ","
private const val POINT_DIMENSIONALITY = 3

fun mergeOverlappingSets(input: MutableSet<MutableSet<Point>>): Set<Set<Point>> {
    val sets = input.map { it.toMutableSet() }.toMutableList()

    var changed: Boolean
    do {
        changed = false

        outer@ for (i in sets.indices) {
            for (j in i + 1 until sets.size) {
                if (sets[i].any { it in sets[j] }) {
                    sets[i].addAll(sets[j])
                    sets.removeAt(j)
                    changed = true
                    break@outer
                }
            }
        }
    } while (changed)

    return sets.toSet()
}

object Part1 {
    fun solve(dayNumber: Int, fileName: String, pairCount: Int): Long {
        val points = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
            val coordinateStrings = line.split(COORDINATE_SEPARATOR)

            require(coordinateStrings.size == POINT_DIMENSIONALITY)

            val coordinates = coordinateStrings.map { it.toDouble() }

            Point(*coordinates.toDoubleArray())
        }

        val distances = points.flatMapIndexed { index, point ->
            val otherPoints = points.slice(index + 1..points.lastIndex)

            otherPoints.map {
                point.distanceTo(it) to (point to it)
            }
        }

        val sortedDistances = distances.sortedBy { it.first }

        val circuits = mutableSetOf<MutableSet<Point>>()
        (0..<pairCount).forEach { index ->
            val (_, points) = sortedDistances[index]
            val (point1, point2) = points

            val existingCircuit = circuits.find { it.contains(point1) } ?: circuits.find { it.contains(point2) }

            if (existingCircuit != null) {
                existingCircuit.add(point1)
                existingCircuit.add(point2)
            } else {
                circuits.add(mutableSetOf(point1, point2))
            }
        }

        var finalSets = mergeOverlappingSets(circuits)

        val sortedCircuitSizes = finalSets.sortedBy { -it.size }.map { it.size }.slice(0..<3).toMutableList()

        while (sortedCircuitSizes.size < 3) {
            sortedCircuitSizes.add(1)
        }

        return sortedCircuitSizes.fold (1L) { total, size -> total * size.toLong() }
    }
}
