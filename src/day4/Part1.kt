package day4

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForGrid(dayNumber = dayNumber, fileName = fileName) { grid ->
            grid.flatMapWithCoordinate { coordinate, cell ->
                if (cell != ROLL_OF_PAPER_SYMBOL) {
                    return@flatMapWithCoordinate false
                }

                val neighborCount = grid.countNeighborsWithValue(coordinate = coordinate, value = ROLL_OF_PAPER_SYMBOL)

                neighborCount <= MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT
            }.filter { it }.size.toLong()
        }
    }
}
