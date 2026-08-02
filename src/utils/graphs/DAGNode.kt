package utils.graphs

open class DAGNode(val neighbors: MutableList<DAGNode> = mutableListOf()) {
    fun addNeighbor(neighbor: DAGNode, shouldEnsureNotCyclic: Boolean = true) {
        if (shouldEnsureNotCyclic) {
            ensureNeighborNotCyclic(neighbor)
        }

        neighbors.add(neighbor)
    }

    fun ensureNotCyclic() {
        neighbors.forEach {
            ensureNeighborNotCyclic(it)
        }
    }

    private fun ensureNeighborNotCyclic(node: DAGNode) {
        ensureNotEqualToThis(node)

        node.neighbors.forEach {
            ensureNeighborNotCyclic(it)
        }
    }

    private fun ensureNotEqualToThis(node: DAGNode) {
        if (node == this) {
            throw IllegalArgumentException("Adding this neighbor would create a cycle")
        }
    }

    fun <U> aggregate(aggregator: (List<DAGNode>) -> U): U {
        return aggregator(neighbors)
    }
}
