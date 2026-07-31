package day7

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
