package day07.aggregators

import utils.graphs.DAGNode

private const val MAX_CHILDREN_COUNT = 2

class TimelineCountAggregator {
    private val nodeToChildrenCount = mutableMapOf<DAGNode, Long>()

    fun aggregate(neighbors: List<DAGNode>): Long {
        require(neighbors.size <= MAX_CHILDREN_COUNT)

        val childrenSubcount = neighbors.sumOf {
            if (it in nodeToChildrenCount) {
                return@sumOf nodeToChildrenCount[it]!!
            }

            val childrenCount = it.aggregate(::aggregate)

            nodeToChildrenCount[it] = childrenCount

            childrenCount
        }

        val leafChildrenCount = MAX_CHILDREN_COUNT - neighbors.size

        return childrenSubcount + leafChildrenCount
    }
}
