package utils.math.euclidean

import kotlin.math.pow
import kotlin.math.sqrt

class Point(vararg val coordinates: Double) {
    init {
        require(coordinates.isNotEmpty()) {
            "Point must have at least 1 coordinate"
        }
    }

    override fun toString(): String {
        return "(" + coordinates.joinToString(separator = ", ") + ")"
    }

    fun distanceTo(other: Point): Double {
        ensureSameDimensionality(other)

        return sqrt(coordinates.zip(other.coordinates).sumOf { (a, b) ->
            (a - b).pow(2)
        })
    }

    private fun ensureSameDimensionality(other: Point) {
        if (coordinates.size != other.coordinates.size) {
            throw IllegalArgumentException("Points have different dimensionality")
        }
    }
}
