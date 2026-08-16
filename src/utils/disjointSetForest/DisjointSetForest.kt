package utils.disjointSetForest

class DisjointSetForest<T> {
    private val parents = mutableMapOf<T, T>()
    private val ranks = mutableMapOf<T, Int>()

    fun union(element1: T, element2: T) {
        val rootA = findRootFor(element1)
        val rootB = findRootFor(element2)

        if (rootA == rootB) {
            return
        }

        val rankA = ranks[rootA]!!
        val rankB = ranks[rootB]!!

        val higherRankRoot = if (rankA < rankB) rootB else rootA
        val lowerRankRoot = if (higherRankRoot == rootA) rootB else rootA

        parents[lowerRankRoot] = higherRankRoot

        if (rankA == rankB) {
            ranks[higherRankRoot] = ranks[higherRankRoot]!! + 1
        }
    }

    fun areConnected(element1: T, element2: T): Boolean {
        return findRootFor(element1) == findRootFor(element2)
    }

    fun makeSetFor(element: T) {
        if (element in parents) {
            return
        }

        parents[element] = element
        ranks[element] = 0
    }

    private fun findRootFor(element: T): T {
        makeSetFor(element)

        val parentA = parents[element]!!
        if (parentA != element) {
            parents[element] = findRootFor(parentA)
        }

        return parents[element]!!
    }

    fun getDisjointSets(): Set<Set<T>> {
        val rootToDisjointSet = mutableMapOf<T, MutableSet<T>>()

        parents.forEach { (element, _) ->
            val root = findRootFor(element)

            rootToDisjointSet.getOrPut(root) {
                mutableSetOf(root)
            }.add(element)
        }

        return rootToDisjointSet.values.toSet()
    }
}
