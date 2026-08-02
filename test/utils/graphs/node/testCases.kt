package utils.graphs.node

import utils.graphs.DAGNode

data class AggregateTestCase(
    val neighborsToAddCount: Int,
)

data class CountPathsTestCase(
    val startNode: DAGNode,
    val endNode: DAGNode,
    val expected: Long,
)
