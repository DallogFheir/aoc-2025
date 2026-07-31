package utils.graphs

class DAGNode<T>(var value: T, val neighbors: MutableList<DAGNode<T>> = mutableListOf()) {
    fun addNeighbor(neighbor: DAGNode<T>) {
        ensureNotCyclic(neighbor)

        neighbors.add(neighbor)
    }

    private fun ensureNotCyclic(neighbor: DAGNode<T>) {
        lateinit var cycleChecker: (node: List<DAGNode<T>>) -> Unit

        cycleChecker = { neighbors ->
            neighbors.forEach {
                if (it == this) {
                    throw IllegalArgumentException("Adding this neighbor would create a cycle")
                }

                it.aggregate(cycleChecker)
            }
        }

        neighbor.aggregate(cycleChecker)
    }

    fun <U> aggregate(aggregator: (List<DAGNode<T>>) -> U): U {
        return aggregator(neighbors)
    }
}
