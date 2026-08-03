package day07

import day07.aggregators.NodeCountAggregator

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
