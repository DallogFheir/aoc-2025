package utils.graphs.node

import utils.graphs.DAGNode

data class AddNeighborTestCase<T>(
    val neighborsToAdd: List<DAGNode<T>>,
)

data class AggregateTestCase(
    val neighborsToAdd: List<DAGNode<Int>>,
    val expected: Int,
)
