package day11

import kotlin.math.max

private const val START_NODE_ID = "svr"
private const val DAC_NODE_ID = "dac"
private const val FFT_NODE_ID = "fft"

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithStartNodeIdForGraphAndEndNode(
            dayNumber = dayNumber,
            fileName = fileName,
            startNodeId = START_NODE_ID
        ) { graph, endNode ->
            require(START_NODE_ID in graph)
            val startNode = graph[START_NODE_ID]!!

            require(DAC_NODE_ID in graph)
            val dacNode = graph[DAC_NODE_ID]!!

            require(FFT_NODE_ID in graph)
            val fftNode = graph[FFT_NODE_ID]!!

            val dacToFftPathCount = dacNode.countPaths(fftNode)
            val fftToDacPathCount = if (dacToFftPathCount != 0L) 0L else fftNode.countPaths(dacNode)
            val betweenProblematicNodesPathCount = max(dacToFftPathCount, fftToDacPathCount)

            require(dacToFftPathCount != fftToDacPathCount)

            val firstProblematicNodeOnPath = if (dacToFftPathCount != 0L) dacNode else fftNode
            val secondProblematicNodeOnPath = if (firstProblematicNodeOnPath == dacNode) fftNode else dacNode

            val fromStartNodeToFirstProblematicNodePathCount = startNode.countPaths(firstProblematicNodeOnPath)
            val fromSecondProblematicNodeToEndPathCount = secondProblematicNodeOnPath.countPaths(endNode)

            fromStartNodeToFirstProblematicNodePathCount * betweenProblematicNodesPathCount * fromSecondProblematicNodeToEndPathCount
        }
    }
}
