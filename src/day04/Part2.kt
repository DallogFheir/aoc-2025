package day04

import utils.grid.Coordinate

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForGrid(dayNumber = dayNumber, fileName = fileName) { grid ->
            var total = 0L

            var removedPaperRolls: List<Coordinate?>

            do {
                removedPaperRolls = grid.flatMapWithCoordinate { coordinate, cell ->
                    if (cell != ROLL_OF_PAPER_SYMBOL) {
                        return@flatMapWithCoordinate null
                    }

                    val neighborCount =
                        grid.countNeighborsWithValue(coordinate = coordinate, value = ROLL_OF_PAPER_SYMBOL)

                    if (neighborCount > MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT) {
                        return@flatMapWithCoordinate null
                    }

                    coordinate
                }.filterNotNull()

                total += removedPaperRolls.size.toLong()

                removedPaperRolls.forEach { coordinate ->
                    grid.setAt(coordinate = coordinate, value = EMPTY_TILE_SYMBOL)
                }
            } while (removedPaperRolls.isNotEmpty())

            total
        }
    }
}
