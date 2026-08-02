package utils.graphs.node

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.graphs.DAGNode

class DAGNodeTest {
    companion object {
        @JvmStatic
        fun aggregateCases() = listOf(
            AggregateTestCase(
                neighborsToAddCount = 0,
            ),
            AggregateTestCase(
                neighborsToAddCount = 1,
            ),
            AggregateTestCase(
                neighborsToAddCount = 3,
            ),
        )
    }

    @Test
    fun `adds neighbors correctly`() {
        val cut = DAGNode()

        val neighbors = List(3) { DAGNode() }

        neighbors.forEach {
            cut.addNeighbor(it)
        }

        Assertions.assertEquals(neighbors, cut.neighbors)
    }

    @Test
    fun `throws if trying to add a node to itself`() {
        val cut = DAGNode()

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.addNeighbor(cut)
        }
    }

    @Test
    fun `does not throw if trying to add a node to itself with check disabled`() {
        val cut = DAGNode()

        cut.addNeighbor(cut, shouldEnsureNotCyclic = false)
    }

    @Test
    fun `throws if trying to add a neighbor with a cycle`() {
        val node1 = DAGNode()
        val node2 = DAGNode()
        val node3 = DAGNode()

        node1.addNeighbor(node2)
        node2.addNeighbor(node3)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            node3.addNeighbor(node1)
        }
    }

    @Test
    fun `does not throw if trying to add a neighbor with a cycle with check disabled`() {
        createCyclicGraph()
    }

    @Test
    fun `ensures graph is not cyclic`() {
        val rootNode = createCyclicGraph()

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            rootNode.ensureNotCyclic()
        }
    }

    private fun createCyclicGraph(): DAGNode {
        val node1 = DAGNode()
        val node2 = DAGNode()
        val node3 = DAGNode()
        val node4 = DAGNode()
        val node5 = DAGNode()

        node1.addNeighbor(node2)
        node1.addNeighbor(node3)
        node1.addNeighbor(node4)
        node2.addNeighbor(node4)
        node3.addNeighbor(node4)
        node1.addNeighbor(node5)

        node5.addNeighbor(node1, shouldEnsureNotCyclic = false)

        return node1
    }

    @ParameterizedTest
    @MethodSource("aggregateCases")
    fun `aggregates values from neighbors correctly`(case: AggregateTestCase) {
        val cut = DAGNode()

        (1..case.neighborsToAddCount).forEach { cut.addNeighbor(DAGNode()) }

        val result = cut.aggregate({ neighbors -> neighbors.sumOf { 1 } })

        Assertions.assertEquals(case.neighborsToAddCount, result)
    }
}
