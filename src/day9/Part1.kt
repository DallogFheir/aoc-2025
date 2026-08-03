package day9

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForPointsAndPossibleRectangles(dayNumber = dayNumber, fileName = fileName) { _, rectangles ->
            val areas = rectangles.map { it.area }

            areas.max().toLong()
        }
    }
}
