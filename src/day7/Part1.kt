package day7

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
