package day7

import utils.filereader.FileReader
import utils.graphs.DAGNode
import utils.grid.Coordinate
import utils.grid.Grid

private const val EMPTY_TILE_SYMBOL = '.'
private const val START_TILE_SYMBOL = 'S'
private const val SPLITTER_TILE_SYMBOL = '^'

private data class BeamStartCoordinateWithSourceSplitterNode(
    val beamStartCoordinate: Coordinate,
    val sourceSplitterNode: DAGNode,
)

fun solveForRootNode(
    dayNumber: Int,
    fileName: String,
    aggregateCallback: (neighbors: List<DAGNode>) -> Long
): Long {
    val inputString = FileReader(
        dayNumber = dayNumber,
        fileName = fileName
    ).read()
    val grid = Grid.fromString(inputString)

    val startCoordinate = grid.findCoordinateFor(START_TILE_SYMBOL)
    val belowStartCoordinate = Coordinate(x = startCoordinate.x, y = startCoordinate.y + 1)

    val startNode = DAGNode()

    val tachyonBeamQueue = mutableListOf(
        BeamStartCoordinateWithSourceSplitterNode(
            beamStartCoordinate = belowStartCoordinate,
            sourceSplitterNode = startNode
        )
    )
    val alreadyProcessedSplitterLocations = mutableMapOf<Coordinate, DAGNode>()

    while (tachyonBeamQueue.isNotEmpty()) {
        var (tachyonBeamCoordinate, node) = tachyonBeamQueue.removeFirst()

        while (grid.isInGrid(tachyonBeamCoordinate) && grid.getAt(tachyonBeamCoordinate) == EMPTY_TILE_SYMBOL) {
            tachyonBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x, y = tachyonBeamCoordinate.y + 1)
        }

        if (grid.isInGrid(tachyonBeamCoordinate)) {
            val tile = grid.getAt(tachyonBeamCoordinate)
            require(tile == SPLITTER_TILE_SYMBOL) {
                "Tile should be $SPLITTER_TILE_SYMBOL, got $tile"
            }

            val wasAlreadyProcessed = alreadyProcessedSplitterLocations.contains(tachyonBeamCoordinate)

            val splitterNode =
                if (wasAlreadyProcessed) alreadyProcessedSplitterLocations[tachyonBeamCoordinate]!! else DAGNode()
            node.addNeighbor(splitterNode)

            if (wasAlreadyProcessed) {
                continue
            }

            alreadyProcessedSplitterLocations[tachyonBeamCoordinate] = splitterNode

            val leftBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x - 1, y = tachyonBeamCoordinate.y)
            if (grid.isInGrid(leftBeamCoordinate)
            ) {
                tachyonBeamQueue.add(
                    BeamStartCoordinateWithSourceSplitterNode(
                        beamStartCoordinate = leftBeamCoordinate,
                        sourceSplitterNode = splitterNode
                    )
                )
            }

            val rightBeamCoordinate = Coordinate(x = tachyonBeamCoordinate.x + 1, y = tachyonBeamCoordinate.y)
            if (grid.isInGrid(rightBeamCoordinate)) {
                tachyonBeamQueue.add(
                    BeamStartCoordinateWithSourceSplitterNode(
                        beamStartCoordinate = rightBeamCoordinate,
                        sourceSplitterNode = splitterNode
                    )
                )
            }
        }
    }

    require(startNode.neighbors.size == 1) {
        "Start beam should hit 1 splitter"
    }

    val rootNode = startNode.neighbors.first()

    return rootNode.aggregate(aggregateCallback)
}
