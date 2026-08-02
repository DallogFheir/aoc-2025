package utils.graphs

open class DAGNode(val neighbors: MutableList<DAGNode> = mutableListOf()) {
    fun addNeighbor(neighbor: DAGNode, shouldEnsureNotCyclic: Boolean = true) {
        if (shouldEnsureNotCyclic) {
            ensureNeighborNotCyclicWithCache(neighbor)
        }

        neighbors.add(neighbor)
    }

    fun ensureNotCyclic() {
        val cache = mutableSetOf<DAGNode>()

        neighbors.forEach {
            ensureNeighborNotCyclicWithCache(node = it, cache = cache)
        }
    }

    private fun ensureNeighborNotCyclicWithCache(node: DAGNode, cache: MutableSet<DAGNode> = mutableSetOf()) {
        if (cache.contains(node)) {
            return
        }

        ensureNotEqualToThis(node)

        node.neighbors.forEach {
            ensureNeighborNotCyclicWithCache(node = it, cache = cache)
        }

        cache.add(node)
    }

    private fun ensureNotEqualToThis(node: DAGNode) {
        if (node == this) {
            throw IllegalArgumentException("Graph is cyclic")
        }
    }

    fun <U> aggregate(aggregator: (List<DAGNode>) -> U): U {
        return aggregator(neighbors)
    }

    fun countPaths(target: DAGNode): Long {
        return countPathsWithCache(target = target, cache = mutableMapOf())
    }

    private fun countPathsWithCache(
        target: DAGNode,
        cache: MutableMap<DAGNode, Long>,
    ): Long {
        if (this == target) {
            return 1L
        }

        if (cache.contains(this)) {
            return cache[this]!!
        }

        val pathCount = neighbors.sumOf {
            it.countPathsWithCache(target = target, cache = cache)
        }

        cache[this] = pathCount

        return pathCount
    }
}
