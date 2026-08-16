package day11

private const val START_NODE_ID = "you"


object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithStartNodeIdForGraphAndEndNode(
            dayNumber = dayNumber,
            fileName = fileName,
            startNodeId = START_NODE_ID
        ) { graph, endNode ->
            require(START_NODE_ID in graph)

            graph[START_NODE_ID]!!.countPaths(endNode)
        }
    }
}
