package day4

import utils.filereader.FileReader
import utils.grid.Grid

const val ROLL_OF_PAPER_SYMBOL = '@'
const val EMPTY_TILE_SYMBOL = '.'
const val MAXIMUM_ROLL_OF_PAPER_NEIGHBOR_COUNT = 3

fun solveForGrid(dayNumber: Int, fileName: String, callback: (grid: Grid<Char>) -> Long): Long {
    val gridString = FileReader(dayNumber = dayNumber, fileName = fileName).read()
    val grid = Grid.fromString(gridString)

    return callback(grid)
}
