package utils.math.euclidean.point

import kotlin.collections.sumOf
import kotlin.math.pow
import kotlin.math.sqrt

open class Point(vararg val coordinates: Double) {
    val dimensionality = coordinates.size

    init {
        require(coordinates.isNotEmpty()) {
            "Point must have at least 1 coordinate"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Point) {
            return false
        }

        return coordinates.contentEquals(other.coordinates)
    }

    override fun hashCode(): Int {
        return coordinates.contentHashCode()
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
        if (dimensionality != other.dimensionality) {
            throw IllegalArgumentException("Points have different dimensionality")
        }
    }

    fun isIntegerPoint(): Boolean {
        return coordinates.all { it % 1.0 == 0.0 }
    }
}