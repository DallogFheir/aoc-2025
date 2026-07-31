package utils.graphs

open class DAGNode(val neighbors: MutableList<DAGNode> = mutableListOf()) {
    fun addNeighbor(neighbor: DAGNode) {
        ensureNotCyclic(neighbor)

        neighbors.add(neighbor)
    }

    private fun ensureNotCyclic(node: DAGNode) {
        ensureNotEqualToThis(node)

        node.neighbors.forEach {
            ensureNotCyclic(it)
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
