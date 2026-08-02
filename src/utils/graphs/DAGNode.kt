package utils.graphs

open class DAGNode(val neighbors: MutableList<DAGNode> = mutableListOf()) {
    fun addNeighbor(neighbor: DAGNode, shouldEnsureNotCyclic: Boolean = true) {
        neighbors.add(neighbor)

        if (shouldEnsureNotCyclic) {
            ensureNotCyclic()
        }
    }

    fun ensureNotCyclic() {
        val finishedNodes = mutableSetOf<DAGNode>()

        neighbors.forEach {
            ensureNeighborNotCyclicWithFinishedNodesAndVisitingStack(node = it, finishedNodes = finishedNodes)
        }
    }

    private fun ensureNeighborNotCyclicWithFinishedNodesAndVisitingStack(
        node: DAGNode,
        finishedNodes: MutableSet<DAGNode>,
        visitingStack: Set<DAGNode> = setOf()
    ) {
        if (finishedNodes.contains(node)) {
            return
        }

        if (visitingStack.contains(node)) {
            throwGraphCyclic()
        }

        ensureNotEqualToThis(node)

        node.neighbors.forEach {
            ensureNeighborNotCyclicWithFinishedNodesAndVisitingStack(
                node = it,
                finishedNodes = finishedNodes,
                visitingStack = visitingStack + setOf(node),
            )
        }

        finishedNodes.add(node)
    }

    private fun ensureNotEqualToThis(node: DAGNode) {
        if (node == this) {
            throwGraphCyclic()
        }
    }

    private fun throwGraphCyclic() {
        throw IllegalStateException("Graph is cyclic")
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
