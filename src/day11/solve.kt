package day11

import utils.filereader.FileReader
import utils.graphs.UniqueDAGNode

private const val END_NODE_ID = "out"
private const val SOURCE_TARGETS_SEPARATOR = ": "
private const val LINE_PART_COUNT = 2
private const val TARGET_NODES_SEPARATOR = " "

fun solveWithStartNodeIdForGraphAndEndNode(
    dayNumber: Int,
    fileName: String,
    startNodeId: String,
    callback: (graph: Map<String, UniqueDAGNode<String>>, endNode: UniqueDAGNode<String>) -> Long
): Long {
    val graph = mutableMapOf<String, UniqueDAGNode<String>>()

    val startNode = UniqueDAGNode(startNodeId)
    graph[startNodeId] = startNode

    FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
        val lineParts = line.split(SOURCE_TARGETS_SEPARATOR)

        require(lineParts.size == LINE_PART_COUNT)

        val source = lineParts[0]
        val targets = lineParts[1].split(TARGET_NODES_SEPARATOR)

        val sourceNode = graph.getOrPut(source) { UniqueDAGNode(source) }

        targets.forEach { target ->
            sourceNode.addNeighbor(graph.getOrPut(target) { UniqueDAGNode(target) }, shouldEnsureNotCyclic = false)
        }
    }

    startNode.ensureNotCyclic()

    require(graph.contains(END_NODE_ID))

    return callback(graph, graph[END_NODE_ID]!!)
}
