package day4

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForGrid(dayNumber = dayNumber, fileName = fileName) { grid ->
            var total = 0L

            var removedPaperRolls: List<Pair<Int, Int>?>

            do {
                removedPaperRolls = grid.flatMapWithCoordinate { x, y, cell ->
                    if (cell != ROLL_OF_PAPER_SYMBOL) {
                        return@flatMapWithCoordinate null
                    }

                    val neighborCount = grid.countNeighborsWithValue(x = x, y = y, value = ROLL_OF_PAPER_SYMBOL)

                    if (neighborCount > MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT) {
                        return@flatMapWithCoordinate null
                    }

                    x to y
                }.filterNotNull()

                total += removedPaperRolls.size.toLong()

                removedPaperRolls.forEach { (x, y) ->
                    grid.setAt(x = x, y = y, value = EMPTY_TILE_SYMBOL)
                }
            } while (removedPaperRolls.isNotEmpty())

            total
        }
    }
}
