package day7

import utils.filereader.FileReader
import utils.grid.Coordinate
import utils.grid.Grid

private const val EMPTY_TILE_SYMBOL = '.'
private const val START_TILE_SYMBOL = 'S'
private const val SPLITTER_TILE_SYMBOL = '^'

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val inputString = FileReader(
            dayNumber = dayNumber,
            fileName = fileName
        ).read()
        val grid = Grid.fromString(inputString)

        val startCoordinate = grid.findCoordinateFor(START_TILE_SYMBOL)
        val belowStartCoordinate = Coordinate(x = startCoordinate.x, y = startCoordinate.y + 1)

        val tachyonBeamQueue = mutableListOf(belowStartCoordinate)
        val alreadyProcessedSplitterLocations = mutableListOf<Coordinate>()
        val alreadyProcessedAfterSplitLocations = mutableSetOf<Coordinate>()

        var splitCount = 0L
        while (tachyonBeamQueue.isNotEmpty()) {
            var tachyonBeamCoordinate = tachyonBeamQueue.removeFirst()

            while (grid.isInGrid(tachyonBeamCoordinate) && grid.getAt(tachyonBeamCoordinate) == EMPTY_TILE_SYMBOL) {
                tachyonBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x, y = tachyonBeamCoordinate.y + 1)
            }

            if (grid.isInGrid(tachyonBeamCoordinate)) {
                val tile = grid.getAt(tachyonBeamCoordinate)
                require(tile == SPLITTER_TILE_SYMBOL) {
                    "Tile should be $SPLITTER_TILE_SYMBOL, got $tile"
                }

                if (alreadyProcessedSplitterLocations.contains(tachyonBeamCoordinate)) {
                    continue
                }

                alreadyProcessedSplitterLocations.add(tachyonBeamCoordinate)

                splitCount++

                val leftBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x - 1, y = tachyonBeamCoordinate.y)
                if (grid.isInGrid(leftBeamCoordinate) && !alreadyProcessedAfterSplitLocations.contains(
                        leftBeamCoordinate
                    )
                ) {
                    tachyonBeamQueue.add(leftBeamCoordinate)
                    alreadyProcessedAfterSplitLocations.add(leftBeamCoordinate)
                }

                val rightBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x + 1, y = tachyonBeamCoordinate.y)
                if (grid.isInGrid(rightBeamCoordinate) && !alreadyProcessedAfterSplitLocations.contains(
                        rightBeamCoordinate
                    )
                ) {
                    tachyonBeamQueue.add(rightBeamCoordinate)
                    alreadyProcessedAfterSplitLocations.add(rightBeamCoordinate)
                }
            }
        }

        return splitCount
    }
}
