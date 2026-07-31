package day7

import day7.aggregators.TimelineCountAggregator

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val timelineCounter = TimelineCountAggregator()

        return solveForRootNode(
            dayNumber = dayNumber,
            fileName = fileName,
            aggregateCallback = timelineCounter::aggregate,
        )
    }
}
