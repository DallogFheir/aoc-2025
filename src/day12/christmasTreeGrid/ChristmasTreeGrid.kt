package day12.christmasTreeGrid

import utils.math.euclidean.point.Point2d

private const val TREE_DEFINITION_SEPARATOR = ": "
private const val GRID_DIMENSIONS_SEPARATOR = "x"
private const val REQUIRED_PRESENT_COUNTS_SEPARATOR = " "

class ChristmasTreeGrid(
    val width: Int,
    val height: Int,
    val requiredPresentCounts: List<Int>,
    val occupiedTiles: Set<Point2d> = setOf()
) {
    val area = width * height

    companion object {
        fun fromString(string: String): ChristmasTreeGrid {
            val parts = string.split(TREE_DEFINITION_SEPARATOR, limit = 2)

            require(parts.size == 2)

            val (gridDimensions, requiredPresentCountsString) = parts

            val gridDimensionParts = gridDimensions.split(GRID_DIMENSIONS_SEPARATOR)
            require(gridDimensionParts.size == 2)
            val width = gridDimensionParts[0].toInt()
            val height = gridDimensionParts[1].toInt()

            val requiredPresentCountsStrings = requiredPresentCountsString.split(REQUIRED_PRESENT_COUNTS_SEPARATOR)
            val requiredPresentCounts = requiredPresentCountsStrings.map { it.toInt() }

            return ChristmasTreeGrid(width = width, height = height, requiredPresentCounts = requiredPresentCounts)
        }
    }
}
