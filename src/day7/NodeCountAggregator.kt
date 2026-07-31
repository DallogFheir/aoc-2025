package day7

import utils.graphs.DAGNode

class NodeCountAggregator<T> {
    private val alreadyVisited = mutableSetOf<DAGNode<T>>()

    fun aggregate(neighbors: List<DAGNode<T>>): Long {
        return 1 + neighbors.sumOf {
            if (alreadyVisited.contains(it)) {
                return@sumOf 0L
            }

            alreadyVisited.add(it)

            it.aggregate(::aggregate)
        }
    }
}
