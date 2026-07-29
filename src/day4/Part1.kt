package day4

import utils.filereader.FileReader
import utils.grid.Grid

private const val ROLL_OF_PAPER_SYMBOL = '@'
private const val MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT = 3

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val gridString = FileReader(dayNumber = dayNumber, fileName = fileName).read()
        val grid = Grid.fromString(gridString)

        return grid.flatMapWithCoordinate { x, y, cell ->
            if (cell != ROLL_OF_PAPER_SYMBOL) {
                return@flatMapWithCoordinate false
            }

            val neighborCount = grid.countNeighborsWithValue(x = x, y = y, value = ROLL_OF_PAPER_SYMBOL)

            neighborCount <= MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT
        }.filter { it }.size.toLong()
    }
}
