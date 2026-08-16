package day12.christmasTree

private const val TREE_DEFINITION_SEPARATOR = ": "
private const val GRID_DIMENSIONS_SEPARATOR = "x"
private const val REQUIRED_PRESENT_COUNTS_SEPARATOR = " "

class ChristmasTree(
    val width: Int,
    val height: Int,
    val requiredPresentCounts: List<Int>,
) {
    val area = width * height

    fun doPresentsFit(
        presents: List<Present>,
    ): Boolean {
        require(presents.size == requiredPresentCounts.size)

        val threeByThreeRegionCount = (width / 3) * (height / 3)

        val totalPresentCount = requiredPresentCounts.sum()

        if (threeByThreeRegionCount >= totalPresentCount) {
            return true
        }

        val presentsTotalArea =
            presents.zip(requiredPresentCounts).sumOf { (present, count) -> present.totalArea * count }

        if (area < presentsTotalArea) {
            return false
        }

        throw IllegalStateException("Christmas tree present placement cannot be determined by simple check")
    }

    companion object {
        fun fromString(string: String): ChristmasTree {
            val parts = string.split(TREE_DEFINITION_SEPARATOR, limit = 2)

            require(parts.size == 2)

            val (gridDimensions, requiredPresentCountsString) = parts

            val gridDimensionParts = gridDimensions.split(GRID_DIMENSIONS_SEPARATOR)
            require(gridDimensionParts.size == 2)
            val width = gridDimensionParts[0].toInt()
            val height = gridDimensionParts[1].toInt()

            val requiredPresentCountsStrings = requiredPresentCountsString.split(REQUIRED_PRESENT_COUNTS_SEPARATOR)
            val requiredPresentCounts = requiredPresentCountsStrings.map { it.toInt() }

            return ChristmasTree(width = width, height = height, requiredPresentCounts = requiredPresentCounts)
        }
    }
}
