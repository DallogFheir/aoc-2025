package day12.present

import utils.math.euclidean.point.Point2d
import kotlin.math.max

private const val OCCUPIED_TILE_SYMBOL = '#'
private const val EMPTY_TILE_SYMBOL = '.'

class Present(occupiedPoints: Set<Point2d>) {
    init {
        require(occupiedPoints.isNotEmpty())
        require(occupiedPoints.all { it.isIntegerPoint() })
    }

    val occupiedPoints = normalizePoints(occupiedPoints)

    val totalArea = occupiedPoints.size

    private val maxCoordinate = max(occupiedPoints.maxOf { it.x }, occupiedPoints.maxOf { it.y }).toInt()

    override fun equals(other: Any?): Boolean {
        if (other !is Present) {
            return false
        }

        return occupiedPoints == other.occupiedPoints
    }

    override fun hashCode(): Int {
        return occupiedPoints.hashCode()
    }

    override fun toString(): String {
        return (0..maxCoordinate).joinToString(System.lineSeparator()) { x ->
            (0..maxCoordinate).joinToString("") { y ->
                (if (occupiedPoints.contains(
                        Point2d(
                            x = x.toDouble(),
                            y = y.toDouble(),
                        )
                    )
                ) OCCUPIED_TILE_SYMBOL else EMPTY_TILE_SYMBOL).toString()
            }
        }
    }

    fun getAllFlippedAndRotated(): Set<Present> {
        val rotatedPresents = getAllRotated(this)

        val flippedHorizontallyPoints = occupiedPoints.map { Point2d(x = -it.x, y = it.y) }.toSet()
        val flippedHorizontallyPresent = Present(occupiedPoints = flippedHorizontallyPoints)
        val flippedHorizontallyAndRotatedPresents = getAllRotated(flippedHorizontallyPresent)

        return rotatedPresents + flippedHorizontallyAndRotatedPresents
    }

    private fun toRotated90DegClockwise(): Present {
        val newPoints = occupiedPoints.map { Point2d(x = it.y, y = -it.x) }.toSet()

        return Present(occupiedPoints = newPoints)
    }

    companion object {
        fun fromString(string: String): Present {
            val presentLines = string.split(System.lineSeparator())

            require(presentLines.size > 1)

            val presentGrid = presentLines.slice(1..presentLines.lastIndex)

            val occupiedPoints = presentGrid.flatMapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, tile ->
                    when (tile) {
                        OCCUPIED_TILE_SYMBOL -> Point2d(x = columnIndex.toDouble(), y = rowIndex.toDouble())
                        EMPTY_TILE_SYMBOL -> null
                        else -> throw IllegalArgumentException("Unknown tile symbol: $tile")
                    }
                }
            }.filterNotNull()

            return Present(occupiedPoints = occupiedPoints.toSet())
        }

        private fun getAllRotated(present: Present): Set<Present> {
            return (0..<3).runningFold(initial = present) { previous, _ ->
                previous.toRotated90DegClockwise()
            }.toSet()
        }

        private fun normalizePoints(points: Set<Point2d>): Set<Point2d> {
            val minX = points.minOf { it.x }
            val minY = points.minOf { it.y }

            return points.map {
                Point2d(x = it.x - minX, y = it.y - minY)
            }.toSet()
        }
    }
}
