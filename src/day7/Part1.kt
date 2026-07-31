package day7

import day7.aggregators.NodeCountAggregator

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val nodeCounter = NodeCountAggregator()

        return solveForRootNode(
            dayNumber = dayNumber,
            fileName = fileName,
            aggregateCallback = nodeCounter::aggregate,
        )
    }
}
