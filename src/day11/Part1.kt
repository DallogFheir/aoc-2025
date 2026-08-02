package day11

import utils.filereader.FileReader
import utils.graphs.DAGNode
import utils.graphs.UniqueDAGNode

private const val START_NODE = "you"
private const val END_NODE = "out"
private const val SOURCE_TARGETS_SEPARATOR = ": "
private const val LINE_PART_COUNT = 2
private const val TARGET_NODES_SEPARATOR = " "

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val graph = mutableMapOf<String, UniqueDAGNode<String>>()
        graph[START_NODE] = UniqueDAGNode(START_NODE)
        graph[END_NODE] = UniqueDAGNode(END_NODE)

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

        val startNode = graph[START_NODE]!!

        startNode.ensureNotCyclic()

        return countPaths(startNode, graph[END_NODE]!!).toLong()
    }

    private fun countPaths(
        node: DAGNode,
        target: DAGNode,
        cache: MutableMap<DAGNode, Int> = mutableMapOf()
    ): Int {
        if (node == target) {
            return 1
        }

        if (cache.contains(node)) {
            return cache[node]!!
        }

        val pathCount = node.neighbors.sumOf {
            countPaths(node = it, target = target, cache = cache)
        }

        cache[node] = pathCount

        return pathCount
    }
}
