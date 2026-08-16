package day07.aggregators

import utils.graphs.DAGNode

class NodeCountAggregator {
    private val alreadyVisited = mutableSetOf<DAGNode>()

    fun aggregate(neighbors: List<DAGNode>): Long {
        return 1 + neighbors.sumOf {
            if (it in alreadyVisited) {
                return@sumOf 0L
            }

            alreadyVisited.add(it)

            it.aggregate(::aggregate)
        }
    }
}
