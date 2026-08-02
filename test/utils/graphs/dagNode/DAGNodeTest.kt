package utils.graphs.dagNode

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

    @Test
    fun `counts paths between 2 nodes correctly`() {
        val node1 = DAGNode()
        val node2 = DAGNode()
        val node3 = DAGNode()
        val node4 = DAGNode()
        val node5 = DAGNode()
        val nodes = listOf(node1, node2, node3, node4, node5)

        node1.addNeighbor(node2)
        node1.addNeighbor(node3)
        node2.addNeighbor(node3)
        node3.addNeighbor(node4)
        node1.addNeighbor(node5)

        val testCases = listOf(
            CountPathsTestCase(
                startNode = node1,
                endNode = node2,
                expected = 1L,
            ),
            CountPathsTestCase(
                startNode = node1,
                endNode = node3,
                expected = 2L,
            ),
            CountPathsTestCase(
                startNode = node1,
                endNode = node4,
                expected = 2L,
            ),
            CountPathsTestCase(
                startNode = node1,
                endNode = node5,
                expected = 1L,
            ),
            CountPathsTestCase(
                startNode = node2,
                endNode = node1,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node2,
                endNode = node3,
                expected = 1L,
            ),
            CountPathsTestCase(
                startNode = node2,
                endNode = node4,
                expected = 1L,
            ),
            CountPathsTestCase(
                startNode = node2,
                endNode = node5,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node3,
                endNode = node1,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node3,
                endNode = node2,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node3,
                endNode = node4,
                expected = 1L,
            ),
            CountPathsTestCase(
                startNode = node3,
                endNode = node5,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node4,
                endNode = node1,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node4,
                endNode = node2,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node4,
                endNode = node3,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node4,
                endNode = node5,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node5,
                endNode = node1,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node5,
                endNode = node2,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node5,
                endNode = node3,
                expected = 0L,
            ),
            CountPathsTestCase(
                startNode = node5,
                endNode = node4,
                expected = 0L,
            ),
        )

        testCases.forEach {
            val startNodeIndex = nodes.indexOf(it.startNode) + 1
            val endNodeIndex = nodes.indexOf(it.endNode) + 1

            val result = it.startNode.countPaths(it.endNode)

            Assertions.assertEquals(
                it.expected,
                result,
                "path count between node $startNodeIndex and $endNodeIndex should be ${it.expected}, got $result"
            )
        }
    }
}
