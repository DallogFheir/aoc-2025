package day7.aggregators

import utils.graphs.DAGNode

class NodeCountAggregator {
    private val alreadyVisited = mutableSetOf<DAGNode>()

    fun aggregate(neighbors: List<DAGNode>): Long {
        return 1 + neighbors.sumOf {
            if (alreadyVisited.contains(it)) {
                return@sumOf 0L
            }

            alreadyVisited.add(it)

            it.aggregate(::aggregate)
        }
    }
}
