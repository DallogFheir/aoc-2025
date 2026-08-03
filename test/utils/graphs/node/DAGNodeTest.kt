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

        neighbors.first().addNeighbor(DAGNode())

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

    @ParameterizedTest
    @MethodSource("aggregateCases")
    fun `aggregates values from neighbors correctly`(case: AggregateTestCase) {
        val cut = DAGNode()

        (1..case.neighborsToAddCount).forEach { _ -> cut.addNeighbor(DAGNode()) }

        val result = cut.aggregate({ neighbors -> neighbors.sumOf { 1 } })

        Assertions.assertEquals(case.neighborsToAddCount, result)
    }
}
