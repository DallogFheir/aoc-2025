package day12.present

import utils.math.euclidean.point.Point2d

private const val OCCUPIED_TILE_SYMBOL = '#'
private const val EMPTY_TILE_SYMBOL = '.'

class Present(private val occupiedPoints: Set<Point2d>) {
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
    }
}
